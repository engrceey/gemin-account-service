package com.gemin.geminaccountservice.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@Builder
public class DepositFundsRequestDto {
    @NotNull
    private Long accountNumber;

    @NotNull
    private BigDecimal initialCredit;
}
