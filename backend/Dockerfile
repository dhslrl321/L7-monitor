FROM openjdk:17-ea-11-jdk-slim

WORKDIR /server

ARG DEBIAN_FRONTEND=noninteractive
ENV TZ=Asia/Seoul
RUN apt-get install -y tzdata

COPY ./build/libs/L7-Monitor-0.0.1-SNAPSHOT.jar l7-monitor.jar

ENTRYPOINT ["java", "-jar", "l7-monitor.jar"]
