package com.gemin.geminaccountservice.service;

import com.gemin.geminaccountservice.dto.request.RegisterUserRequestDto;

public interface UserService {
    String registerUser(RegisterUserRequestDto registerUserRequestDto);
}
