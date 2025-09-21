# Dockerize a Spring Boot Application
This is a Spring Boot application running on Spring Boot 3.5.6 and it is dockerized.

# Functionality
This application when run will response to two endpoints with two different responses.

* GET localhost:8080 &rarr; return a default Hello message.
```
{
    "message": "Hello from Spring Boot and Docker",
    "date": "2025-09-21 16:29:53 -0400"
}
```

* GET localhost:8080/greeting?name=Thomas &rarr; return a Hello message with the name
```
{
    "message": "Hello Thomas! Hope you have a great day!",
    "date": "2025-09-21 16:45:16 -0400"
}
```

# Implementation
## Spring Boot Application
This is a simple Spring Boot application with one controller - [DockerMessageController](src/main/java/com/kimlngo/spring_boot_docker/controller/DockerMessageController.java). This controller exposes two GET mappings for the two endpoints.

## Dockerization
Dockerization is implemented in two stages using multi-stage [Dockerfile](Dockerfile) .

```declarative
FROM maven:3.9.5 AS build

ENV HOME=/usr/app

RUN mkdir -p $HOME

WORKDIR $HOME

ADD . $HOME

RUN --mount=type=cache,target=/root/.m2 ./mvnw -f $HOME/pom.xml clean package
-----------------------------------------------------------------------------
FROM alpine/java:21-jdk

ARG JAR_FILE=/usr/app/target/*.jar

COPY --from=build $JAR_FILE /app/runner.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/runner.jar"]
```
* Stage 1: package the application by relying on maven:3.9.5
* Stage 2: take the jar file created in Stage 1 and move it to the container, expose the port 8080 and execute the java command

# Commands
The application can be simply run by two Docker commands:
```declarative
docker build -t dockermessage:v1 .
```

```declarative
docker run -d --rm --name dockerhost -p 8080:8080 dockermessage:v1
```

# Note:
We don't need to run ```mvn clean package``` separately because this step is already incorporated to become stage 1 of the docker file.