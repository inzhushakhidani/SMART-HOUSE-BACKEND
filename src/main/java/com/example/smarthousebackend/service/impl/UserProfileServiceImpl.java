package com.example.smarthousebackend.service.impl;

import com.example.smarthousebackend.dto.PasswordEditRequest;
import com.example.smarthousebackend.dto.UserDto;
import com.example.smarthousebackend.entity.User;
import com.example.smarthousebackend.exceptions.PasswordMismatchException;
import com.example.smarthousebackend.exceptions.UserNotFoundException;
import com.example.smarthousebackend.mapper.UserMapper;
import com.example.smarthousebackend.repository.UserRepository;
import com.example.smarthousebackend.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper mapper;


    @Override
    public UserDto getCurrentUserDetails(User user) {
        return mapper.toDto(user);

    }
    @Override
    public UserDto getUserDetailsById(Integer id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return mapper.toDto(user);
    }

    @Override
    @SneakyThrows
    public UserDto getUserDetailsByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return mapper.toDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUserProfile(UserDto userDto, User user) {
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(user);
        return mapper.toDto(user);
    }

    @Override
    @Transactional
    public void deleteUserProfile(User user) {
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void editPassword(PasswordEditRequest request, User user) throws PasswordMismatchException {
        if(!request.getRePassword().equals(request.getNewPassword())) throw new PasswordMismatchException("Password mismatch");
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
