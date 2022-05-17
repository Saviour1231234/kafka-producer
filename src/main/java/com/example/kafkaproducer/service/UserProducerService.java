package com.example.kafkaproducer.service;

import com.example.kafkaproducer.dto.UserDto;
import com.example.kafkaproducer.entity.User;

public interface UserProducerService {

    User sendData(User user);
}
