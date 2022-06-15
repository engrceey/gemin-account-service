package com.gemin.geminaccountservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DepositResponseDto {
    private String statusCode;
    private BigDecimal amount;
    private long depositAccountNumber;
    private String receiverName;
    private boolean isTransactionSuccessful;
}
