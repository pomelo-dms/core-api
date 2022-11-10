# 基础镜像
FROM openjdk
# 作者信息
LABEL maintainer="ikangjia.cn@outlook.com"
# 开放端口
EXPOSE 9011
# 将 /target 目录下的 ams-0.0.1-SNAPSHOT.jar 复制到 docker 中，并重命名为 ams.jar
ADD target/pomelo-0.0.1-SNAPSHOT.jar pomelo.jar
# 容器执行命令
ENTRYPOINT ["nohup", "java", "-jar", "pomelo.jar", " &"]