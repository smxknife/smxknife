# 手册

### cassandra安装

采用docker安装，形成一个简单的cassandra集群

#### 创建网络

为了容器之间的通信，采用同一个网络的方法

```bash
docker network create cluster
```

#### 获取cassandra镜像

采用官方镜像

```bash
docker pull cassandra
```

#### 思考

cassandra数据存储在哪个位置？默认？no，我是程序员，我要有掌控权，自定义
（暂定存储在/..../toolbox/workstations/cassandra）

#### 创建容器

基础命令

```bash
docker run --name node1 -d cassandra
```

为了实现通信加入网络

```bash
docker run --name node1 --network cluster -d cassandra
```

实现集群

创建三个cassandra容器，两个子节点一个主机节点

```bash
docker run --name node1 --network cluster -d cassandra
docker run --name node2 --network cluster -d cassandra
# docker inspect --format='{{ .NetworkSettings.IPAddress }}' node1 node2
# docker run --name master --network cluster -d -e CASSANDRA_SEEDS="<ip1>,<ip2>" cassandra
```

为了安全起见可以设置一个集群名称，不采用默认的设置

```bash
docker run --name node1 --network cluster -d -e CASSANDRA_CLUSTER_NAME="cluster" cassandra
docker run --name node2 --network cluster -d -e CASSANDRA_CLUSTER_NAME="cluster" cassandra
docker run --name node3 --network cluster -d -e CASSANDRA_CLUSTER_NAME="cluster" cassandra
docker run --name node4 --network cluster -d -e CASSANDRA_CLUSTER_NAME="cluster" cassandra
```
指定位置

```bash
docker run --name node1 -v /xxxxx/toolbox/workstations/cassandra/node1:/var/lib/cassandra --network cluster -d -e CASSANDRA_CLUSTER_NAME="cluster" cassandra
docker run --name node2 -v /xxxxx/toolbox/workstations/cassandra/node2:/var/lib/cassandra --network cluster -d -e CASSANDRA_CLUSTER_NAME="cluster" cassandra
```

暴露端口

```bash
docker run --name node1 -p 9042:9042 -v /Users/ShaoYun/toolbox/workstations/cassandra/node1:/var/lib/cassandra --network cluster -d -e CASSANDRA_CLUSTER_NAME="cluster" cassandra
docker run --name node2 -p 9043:9042 -v /Users/ShaoYun/toolbox/workstations/cassandra/node2:/var/lib/cassandra --network cluster -d -e CASSANDRA_CLUSTER_NAME="cluster" cassandra
docker run --name node3 -v /Users/ShaoYun/toolbox/workstations/cassandra/node3:/var/lib/cassandra --network cluster -d -e CASSANDRA_CLUSTER_NAME="cluster" -e CASSANDRA_SEEDS="172.18.0.2,172.18.0.3" cassandra
docker run --name node4 -v /Users/ShaoYun/toolbox/workstations/cassandra/node4:/var/lib/cassandra --network cluster -d -e CASSANDRA_CLUSTER_NAME="cluster" -e CASSANDRA_SEEDS="172.18.0.2,172.18.0.3" cassandra
```

```sybase
docker run --name node1 -p 19042:9042 --network cluster -v /Users/ShaoYun/toolbox/workstations/cassandra/node1:/var/lib/cassandra -e HEAP_NEWSIZE=1M -e MAX_HEAP_SIZE=256M -d cassandra
```

### 进入cassandra内部

docker exec -it node1 bash

退出可以直接exit