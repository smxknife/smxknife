# 参考手册

### 简介

softmarket为微信学习开发项目，相关的技术选型以springboot为基础框架，采用docker实现分布式部署，数据库采用mysql

### 部署环境准备

##### docker安装

略...

##### network创建

创建network是为了实现容器之间的互联，传统的`--link`的形式被docker废弃掉，采用新的形式就是network，同一个network下容器之间是可以相互通信的

命令如下：

`docker network create softmarket`

##### mysql容器启动

* 首先下载mysql镜像
* 运行mysql容器，命令如下：

    `docker run -d --name softmarket-mysql --network softmarket -e MYSQL_ROOT_PASSWORD=root mysql `
    
* 新建数据库
    
    * 首先先进入mysql容器
    
        `docker exec -it softmarket-mysql bash`
        
    * 登录mysql
    
        `mysql -uroot -p`
        
    * 创建数据库
    
        `create database softmarket default character set utf8 collate utf8_general_ci`
        

    
    