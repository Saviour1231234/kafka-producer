package com.example.kafkaproducer.controller;

import com.example.kafkaproducer.dto.UserDto;
import com.example.kafkaproducer.dto.mapper.UserMapper;
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
    private final KafkaTemplate<Long, UserDto> kafkaTemplate;
//    private final UserMapper userMapper;

//    @PostMapping("/generate")
//    public User generate(@RequestParam Long id,
//                           @RequestParam String firstName,
//                            @RequestParam String lastName,
//                           @RequestParam Integer age) {
//        var userDto = producerService.sendData(new User(id, firstName, lastName, age));
//        ListenableFuture<SendResult<Long, User>> future = kafkaTemplate.send("users",id, userDto);
//        future.addCallback(System.out::println,System.err::println);
//        kafkaTemplate.flush();
//        return userDto;
//    }
    @PostMapping("/generate/dto")
    public User generateUserFromDto(@RequestBody UserDto userDto){

        var dataByDTO = producerService.sendDataByDTO(userDto);
        ListenableFuture<SendResult<Long, UserDto>> future = kafkaTemplate.send("users",userDto);
        future.addCallback(System.out::println,System.err::println);
        kafkaTemplate.flush();

        return dataByDTO;
    }

}