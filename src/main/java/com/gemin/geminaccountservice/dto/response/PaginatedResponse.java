package com.gemin.geminaccountservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PaginatedResponse<T> implements Serializable {

    private List<T> content;
    private long totalElements;
}