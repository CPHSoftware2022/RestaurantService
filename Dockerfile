FROM openjdk:8 as builder
WORKDIR /opt/app
COPY /.mvn .mvn
COPY /mvnw ./
COPY pom.xml ./
RUN apt-get update &&\
    apt-get install dos2unix -y && \
    apt-get clean
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline
COPY /src ./src
RUN ./mvnw clean install

FROM openjdk:8-jdk-alpine
EXPOSE 8100
ADD target/restaurant-microservice.jar restaurant-microservice.jar
ENTRYPOINT ["java","-jar","restaurant-microservice.jar"]

#restaurant-microservice.jar
