package com.gemin.geminaccountservice.service;

import com.gemin.geminaccountservice.constants.enums.RegisterStatus;
import com.gemin.geminaccountservice.dto.request.RegisterUserRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


class UserServiceTest {

    @Autowired
    private UserService userService;

    private RegisterUserRequestDto registerUserRequestDto;

    @BeforeEach
    void setUp() {
        registerUserRequestDto = RegisterUserRequestDto.builder().build();
    }


    @Disabled
    @Test
    @DisplayName("Register user with zero initial credit")
    void registerUserWithZeroInitialCredit() {
        //GIVEN ::
        registerUserRequestDto = RegisterUserRequestDto
                .builder()
                .initialCredit(BigDecimal.valueOf(0.0))
                .firstName("redhat")
                .lastName("java")
                .password("1234567")
                .confirmPassword("1234567")
                .phoneNumber("08012345678")
                .email("gemini@gmail.com")
                .build();
//
        //WHEN
        String response = userService.registerUser(registerUserRequestDto);

        //THEN
        assertEquals(RegisterStatus.SUCCESS.getRegistrationStatus(), response);


    }


    @Disabled
    @Test
    @DisplayName("Register user with initial credit")
    void registerUserWithInitialCredit() {
//GIVEN ::
        registerUserRequestDto = RegisterUserRequestDto
                .builder()
                .initialCredit(BigDecimal.valueOf(10.0))
                .firstName("redhat")
                .lastName("java")
                .password("1234567")
                .confirmPassword("1234567")
                .phoneNumber("08012345678")
                .email("gemini@gmail.com")
                .build();


        //WHEN
        String response = userService.registerUser(registerUserRequestDto);

        //THEN
        assertEquals("Registration with transaction successful", response);


    }
}