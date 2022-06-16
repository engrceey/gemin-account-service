package com.gemin.geminaccountservice.service;

import com.gemin.geminaccountservice.dto.request.RegisterUserRequestDto;
import com.gemin.geminaccountservice.exceptions.ResourceCreationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    private RegisterUserRequestDto registerUserRequestDto;
    @BeforeEach
    void setUp() {
        registerUserRequestDto = RegisterUserRequestDto.builder().build();
    }

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
                .email("test1-email@gmail.com")
                .build();

        //WHEN
        Exception exception = assertThrows(ResourceCreationException.class, () -> {
            userService.registerUser(registerUserRequestDto);
        });

        String expectedMessage = "User already exist";
        String actualMessage = exception.getMessage();

        //THEN
        assertTrue(actualMessage.contains(expectedMessage));
    }


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
                .email("test2-email@gmail.com")
                .build();

        //WHEN
        String response = userService.registerUser(registerUserRequestDto);

        //THEN
        assertEquals("Registration with transaction successful", response);
    }
}