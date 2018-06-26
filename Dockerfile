FROM alpine/git as clone 
WORKDIR /app
RUN git clone https://github.com/jamesjmtaylor/weg-api.git

FROM maven:3.5-jdk-8-alpine as build 
WORKDIR /app
COPY --from=clone /app/weg-api /app 
RUN mvn install

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/spring-jpa-postgresql-spring-boot-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar spring-jpa-postgresql-spring-boot-0.0.1-SNAPSHOT.jar"]
