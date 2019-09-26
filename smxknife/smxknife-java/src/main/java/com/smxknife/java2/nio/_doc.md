# NIO开发步骤
## Server创建过程

#### Step1：打开ServerSocketChannel

```java
ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
```

#### Step2：绑定监听端口，设置为非阻塞模式
```java
serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
serverSocketChannel.configureBlocking(false);
```

#### Step3：创建Reactor线程，创建多路复用器并启动线程
```java
Selector selector = Selector.open();
new Thread(new ReactorTask()).start();
```

#### Step4：将ServerSocketChannel注册到多路复用器Selector上，监听ACCEPT事件
```java
serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, ioHandler);
```

这里的1024为backlog

#### Step5：多路复用器在线程run方法的无限循环体内轮询准备就绪的key
```java
int num = selector.selector();
Set selectedKeys = selector.selectedKeys();
Iterator it = selectedKeys.iterator();
while(it.hasNext()){
     SelectionKey key = (SelectionKey)it.next();
     //....deal with i/o event ...
}

```

#### Step6：多路复用器监听到有新的客户端接入，处理新的接入请求，完成TCP三次握手，建立物理链接。将新接入的客户端连接注册到Reactor线程的多路复用器上，监听读操作，读取客户端发送的网络消息
```java
if (key.isAcceptable()) {
    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
    SocketChannel sc = ssc.accept();
    sc.configureBlocking(false);
    // add new connection to the selector
    sc.register(selector, SelectionKey.OP_READ);
}
```

#### Step7：异步读取客户端消息到缓冲区
```java
if (key.isReadable()) {
    SocketChannel sc = (SocketChannel) key.channel();
    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    int readBytes = sc.read(readBuffer);
    if (readBytes > 0) {
        readBuffer.flip();
        byte[] bytes = new byte[readBuffer.remaining()];
        readBuffer.get(bytes);
        String body = new String(bytes, "UTF-8");
        System.out.println("TimeServer receive order: " + body);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        doWrite(sc, currentTime);
    }
}
```

## Client创建过程
#### Step1: 打开SocketChannel

```java
SocketChannel clientChannel = SocketChannel.open();
```

#### Step2：设置SocketChannel

```java
clientChannel.configBlocking(false); // 设置为非阻塞模式
Socket socket = clientSocketChannel.socket();
socket.setReuseAddress(true); // 设置重用地址
socket.setReceiveBufferSize(512); // 设置接收字节大小
```

#### Step3：连接服务器（异步）

```java
boolean connected = clientSocketChannel.connect(new InetSocketAddress(host, port));
```

#### Step4：判断是否连接成功，如果连接成功，直接在多路复用器中注册为读状态；如果没有连接成功（异步连接，返回false，说明客户端已经发送sync包，服务端还没有返回ack包，物理链路还没有建立）

```java
if(connected) {
    clientSocketChannel.register(selector, SelectionKey.OP_READ, ioHandler);
} else {
    clientSocketChannel.register(selector, SelectionKey.OP_CONNECT, ioHandler);
}
```

#### Step5：向Reactor线程的多路复用器注册OP_CONNECT状态，监听服务端TCP ACK应答

```java
clientChannel.register(select, SelectionKey.OP_CONNECT,ioHandler);
```

就是上面Step4中的else部分

#### Step6：创建Reactor线程，创建多路复用器并启动线程

```java
Selector selector = Selector.open();
new Thread(new ReactorTask()).start();
```

#### Step7：多路复用器在run方法无限循环体内轮询准备就绪的Key

```java
int num = selector.select();
Set selectedKeys = selector.selectedKeys();
Iterator it = selectedKeys.iterator();
while(it.hasNext()) {
    SelectionKey key = (SelectionKey)it.next();
    // TODO 根据key处理事件
}
```

#### Step8：接收connect事件处理

```java
if(key.isConnectable()) {
    // TODO 处理connect事件
}
```

#### Step9：判断连接成功，注册到多路复用器上读状态

这一步需要紧跟上一步，需要使可连接
```java
SocketChannel sc = (SocketChannel) key.channel();
if(key.isConnectable()) {
    // TODO 处理connect事件
    if(sc.finishConnect()) {
        sc.register(selector, SelectionKey.OP_READ, ioHandler);
    }
}
```

#### Step10：异步读取客户端请求消息到缓冲区
```java
if (key.isReadable()) {
    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    int readBytes = sc.read(byteBuffer);
}
```