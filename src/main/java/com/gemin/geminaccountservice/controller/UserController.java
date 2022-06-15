package com.gemin.geminaccountservice.controller;

import com.gemin.geminaccountservice.dto.request.RegisterUserRequestDto;
import com.gemin.geminaccountservice.dto.response.ApiResponse;
import com.gemin.geminaccountservice.dto.response.DepositResponseDto;
import com.gemin.geminaccountservice.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @ApiOperation(
            value = "Register User",
            response = DepositResponseDto.class
    )
    @PostMapping(path="/register")
    public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody @Valid final RegisterUserRequestDto registrationRequestDto) {
        log.info("controller register: register user :: [{}] ::", registrationRequestDto.getEmail());
        String response = userService.registerUser(registrationRequestDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/gemin/user/register").toUriString());
        return ResponseEntity.created(uri).body(ApiResponse.<String>builder()
                .statusMessage("success")
                .isSuccessful(true)
                .data(response)
                .build()
        );
    }


}
