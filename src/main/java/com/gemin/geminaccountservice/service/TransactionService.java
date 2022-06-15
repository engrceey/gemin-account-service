package com.gemin.geminaccountservice.service;

import com.gemin.geminaccountservice.dto.request.DepositAccountRequestDto;
import com.gemin.geminaccountservice.dto.response.DepositResponseDto;
import com.gemin.geminaccountservice.dto.response.PaginatedResponse;
import com.gemin.geminaccountservice.entity.Transaction;

public interface TransactionService {
    DepositResponseDto depositFunds(DepositAccountRequestDto depositRequestDto);
    PaginatedResponse<Transaction> getTransactions(int start, int limit);
}
