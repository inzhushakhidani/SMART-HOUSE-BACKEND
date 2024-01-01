package com.example.smarthousebackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

    @NotNull(message = "First name is required")
    private String firstname;

    @NotNull(message = "Last name is required")
    private String lastname;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Phone Number is required")
    private String phoneNumber;
}
