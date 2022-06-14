package com.gemin.geminaccountservice.dto.request;


import com.gemin.geminaccountservice.constants.enums.AccountType;
import com.gemin.geminaccountservice.entity.User;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateAccountRequestDto {
    private long accountNumber;
    private AccountType accountType;
    private BigDecimal accountBalance;
    private boolean isActivated;
    private User user;

}
