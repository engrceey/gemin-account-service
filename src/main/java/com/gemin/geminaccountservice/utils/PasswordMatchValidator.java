package com.gemin.geminaccountservice.utils;

import com.gemin.geminaccountservice.dto.request.RegisterUserRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, RegisterUserRequestDto>{

    @Override
    public void initialize(PasswordMatch p) {

    }

    @Override
    public boolean isValid(RegisterUserRequestDto userRegistrationRequestDto, ConstraintValidatorContext constraintValidatorContext) {
        String plainPassword = userRegistrationRequestDto.getPassword();
        String repeatPassword = userRegistrationRequestDto.getConfirmPassword();

        return plainPassword != null && plainPassword.equals(repeatPassword);
    }

}