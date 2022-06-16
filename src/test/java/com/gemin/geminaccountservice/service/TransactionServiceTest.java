package com.gemin.geminaccountservice.service;

import com.gemin.geminaccountservice.dto.request.DepositAccountRequestDto;
import com.gemin.geminaccountservice.dto.response.DepositResponseDto;
import com.gemin.geminaccountservice.dto.response.PaginatedResponse;
import com.gemin.geminaccountservice.entity.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;
    private DepositAccountRequestDto depositAccountRequestDto;
    private DepositResponseDto depositResponseDto;


    @BeforeEach
    void setUp() {
        depositAccountRequestDto = DepositAccountRequestDto.builder().build();
        depositResponseDto = DepositResponseDto.builder().build();
    }

    @Disabled
    @Test
    @DisplayName("Deposit Fund Service Test")
    void depositFunds() {
        //GIVEN ::
        depositAccountRequestDto = DepositAccountRequestDto
                .builder()
                .receiver("mr gemini")
                .sender("mr gemini")
                .amount(BigDecimal.valueOf(100.0))
                .receiverAccountNumber(123456700L)
                .build();

        //WHEN
        depositResponseDto = transactionService.depositFunds(depositAccountRequestDto);

        //THEN
        assertEquals("mr gemini", depositResponseDto.getReceiverName());
        assertEquals(BigDecimal.valueOf(100.0), depositResponseDto.getAmount());
        assertThat(depositResponseDto.isTransactionSuccessful()).isTrue();
    }


    @Disabled
    @Test
    @DisplayName("Get Transaction Empty DB")
    void getTransactionsEmptyDB() {
        //WHEN
        PaginatedResponse<Transaction> transactions = transactionService.getTransactions(0, 5);

        //THEN
        assertThat(transactions.getContent().isEmpty()).isTrue();
    }


    @Disabled
    @Test
    @DisplayName("Get Transaction")
    void getTransactions() {

        //WHEN
        depositFunds();
        depositFunds();
        depositFunds();
        PaginatedResponse<Transaction> transactions = transactionService.getTransactions(0, 5);

        //THEN
        assertThat(!transactions.getContent().isEmpty()).isTrue();
    }
}