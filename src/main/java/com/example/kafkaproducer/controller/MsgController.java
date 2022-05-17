package com.example.kafkaproducer.controller;

import com.example.kafkaproducer.dto.UserDto;
import com.example.kafkaproducer.entity.User;
import com.example.kafkaproducer.service.UserProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("kafka-api/v1")
@RequiredArgsConstructor
public class MsgController {

    private final UserProducerService producerService;
    private final KafkaTemplate<Long, User> kafkaTemplate;

    @PostMapping("/generate")
    public User generate(@RequestParam Long id,
                           @RequestParam String firstName,
                            @RequestParam String lastName,
                           @RequestParam Integer age) {
        var userDto = producerService.sendData(new User(id, firstName, lastName, age));
        ListenableFuture<SendResult<Long, User>> future = kafkaTemplate.send("users",id, userDto);
        future.addCallback(System.out::println,System.err::println);
        kafkaTemplate.flush();
        return userDto;
    }
}