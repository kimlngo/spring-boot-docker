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

* GET localhost:8080/greeting?name=Thomas
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
Dockerization is implemented in two stages using multi-stage.

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
We don't need to run ```mvn clean package``` separately because this step is already incorporated in to stage 1 of the docker file.