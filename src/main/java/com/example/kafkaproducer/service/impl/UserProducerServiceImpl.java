package com.example.kafkaproducer.service.impl;

import com.example.kafkaproducer.dto.UserDto;
import com.example.kafkaproducer.dto.mapper.UserMapper;
import com.example.kafkaproducer.entity.User;
import com.example.kafkaproducer.service.UserProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProducerServiceImpl implements UserProducerService {

    @Autowired
    private KafkaTemplate<Long, User> kafkaTemplate;
    private final UserMapper userMapper;

    @Override
    public User sendData(User user) {
        System.out.println("Producing users: " + user);
        kafkaTemplate.send("users", user);
        return user;
    }
}
