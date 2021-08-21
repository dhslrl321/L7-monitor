FROM openjdk:17-ea-11-jdk-slim

WORKDIR /server

ARG DEBIAN_FRONTEND=noninteractive
ENV TZ=Asia/Seoul
RUN apt-get install -y tzdata

COPY ./build/libs/L7-Monitor-0.0.1-SNAPSHOT.jar l7-monitor.jar

ARG HOST_IP

ENV HOST_IP=${HOST_IP}
ENV DB_ENV="--spring.datasource.url=jdbc:mysql://bori-db-container/bob_l7?characterEncoding=utf8&serverTimezone=Asia/Seoul"

ENTRYPOINT ["java", "-jar", "l7-monitor.jar", "$DB_ENV", "--boribob.host-ip=$HOST_IP"]
