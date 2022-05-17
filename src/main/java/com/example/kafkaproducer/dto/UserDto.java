package com.example.kafkaproducer.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private int age;
}
