package com.gemin.geminaccountservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ApiResponse<T> {

    private String statusMessage;
    private Boolean isSuccessful;
    private T data;

}