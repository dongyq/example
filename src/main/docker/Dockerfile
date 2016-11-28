FROM java:8

MAINTAINER huaxiujun@aliyun.com

ENV RD_BEIJING_EXAMPLE 1.0

ADD example-1.0.0.jar app.jar

#EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]

#docker build -t example:1.0 .
#docker run -d --name fmc --log-opt max-size=50m --log-opt max-file=20 -v /data/logs:/data/logs --net=host -v /etc/localtime:/etc/localtime:ro -e 'TZ=Asia/Shanghai' example:1.0