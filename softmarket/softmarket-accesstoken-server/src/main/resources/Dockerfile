# Project : softmarket-accesstoken-server
# Author  : smxknife
# server  : 47.94.154.12
# port    : 8080

# 指定镜像
FROM smxknife/centos-jdk1.8:1.0.0
# 指定容器的工作目录
# WORKDIR /home
# 复制主机目录的文件（前一个）到容器目录（后一个）
ADD softmarket-accesstoken-server-1.0-SNAPSHOT.jar token-app.jar
# 执行命令
RUN bash -c 'touch /token-app.jar'
# 暴露端口
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod", "-jar","/token-app.jar"]




