# 使用手册

### 快速部署

> * 将jar包和Dockerfile上传到服务器同一个目录下
> * docker build -t smxknife/softmarket .
> * 执行container，docker run -p 80:10001 --name softmarket --network softmarket -d smxknife/softmarket

### 脚本部署

> * 将jar和Dockerfile上传
> * 运行脚本update.sh