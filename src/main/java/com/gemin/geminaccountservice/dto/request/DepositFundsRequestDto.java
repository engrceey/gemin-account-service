package com.gemin.geminaccountservice.dto.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class DepositFundsRequestDto {
    private String accountNumber;
    private BigDecimal initialCredit;
}
