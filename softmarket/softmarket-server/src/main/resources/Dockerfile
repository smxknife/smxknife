# Project : softmarket
# Author  : smxknife
# server  : 47.94.154.12
# port    : 10001

# 指定镜像
FROM smxknife/centos-jdk9:1.0.1
# 指定容器的工作目录
# WORKDIR /home
# 复制主机目录的文件（前一个）到容器目录（后一个）
ADD softmarket-server-1.0-SNAPSHOT.jar app.jar
# 执行命令
RUN bash -c 'touch /app.jar'
# 暴露端口
EXPOSE 10001
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]




