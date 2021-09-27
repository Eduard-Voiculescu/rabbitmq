package com.demo.producerservice.rest;

import com.demo.producerservice.model.User;
import com.demo.producerservice.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/")
public class ProducerController {

    private final RabbitMQSender rabbitMQSender;

    @Value("${app.message}")
    private String message;

    public ProducerController(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @PostMapping(value = "user")
    public String publishUserDetails(@RequestBody User user) {
        rabbitMQSender.send(user);
        return message;
    }

}
