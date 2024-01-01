package com.example.smarthousebackend.mapper;

import com.example.smarthousebackend.dto.UserDto;
import com.example.smarthousebackend.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto>{
    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
