package com.kimlngo.spring_boot_docker.controller;

import com.kimlngo.spring_boot_docker.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerMessageController {

    @GetMapping("/")
    public ResponseEntity<Message> getMessage() {
        return new ResponseEntity<>(new Message("Hello from Spring Boot and Docker"), HttpStatus.OK);
    }

    @GetMapping("/greeting")
    public ResponseEntity<Message> greets(@RequestParam("name") String name) {
        return new ResponseEntity<>(new Message("Hello " + name + "! Hope you have a great day!"), HttpStatus.OK);
    }
}
