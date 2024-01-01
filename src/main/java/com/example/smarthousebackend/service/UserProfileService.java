package com.example.smarthousebackend.service;

import com.example.smarthousebackend.dto.PasswordEditRequest;
import com.example.smarthousebackend.dto.UserDto;
import com.example.smarthousebackend.entity.User;
import com.example.smarthousebackend.exceptions.PasswordMismatchException;
import com.example.smarthousebackend.exceptions.UserNotFoundException;

public interface UserProfileService {
    UserDto getCurrentUserDetails(User user);

    UserDto getUserDetailsById(Integer id) throws UserNotFoundException;

    UserDto updateUserProfile(UserDto userDto, User user);

    void deleteUserProfile(User user);

    void editPassword(PasswordEditRequest request, User user) throws PasswordMismatchException;

    UserDto getUserDetailsByEmail(String email);
}
