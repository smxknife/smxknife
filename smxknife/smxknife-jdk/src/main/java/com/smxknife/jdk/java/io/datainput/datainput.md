# DataInput

* 从二进制流中读取字节并创建为任何java原始数据类型
* 提供了一个转换为UTF8编码的字符串方法
* 读取所需字节数之前到达文件结尾，将会抛出EOFException
* 不会抛出IOException 异常而会抛出EOFException异常，特别的，如果输入流被关闭了一个IOException异常将会抛出