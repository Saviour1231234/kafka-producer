package com.example.kafkaproducer.dto.mapper;

import com.example.kafkaproducer.dto.UserDto;
import com.example.kafkaproducer.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto entityToDto(User user);

    User fromDtoToUser(UserDto userDto);
}
