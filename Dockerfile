FROM maven:3.5-jdk-8-alpine as build 
RUN mkdir app
WORKDIR /app
COPY . /app 
# Copies files from build directory to docker container
RUN mvn clean install

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/spring-jpa-postgresql-spring-boot-0.0.1-SNAPSHOT.jar /app
# Copies files from 'build' container to runtime docker container
EXPOSE 8080
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar spring-jpa-postgresql-spring-boot-0.0.1-SNAPSHOT.jar --logging.file=./spring-boot-app.log"]
