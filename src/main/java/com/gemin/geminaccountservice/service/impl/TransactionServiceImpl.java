package com.gemin.geminaccountservice.service.impl;

import com.gemin.geminaccountservice.constants.AppConstant;
import com.gemin.geminaccountservice.constants.enums.TransactionStatus;
import com.gemin.geminaccountservice.constants.enums.TransactionType;
import com.gemin.geminaccountservice.dto.request.DepositAccountRequestDto;
import com.gemin.geminaccountservice.dto.response.DepositResponseDto;
import com.gemin.geminaccountservice.dto.response.PaginatedResponse;
import com.gemin.geminaccountservice.entity.Account;
import com.gemin.geminaccountservice.entity.Transaction;
import com.gemin.geminaccountservice.exceptions.ResourceNotFoundException;
import com.gemin.geminaccountservice.repository.AccountRepository;
import com.gemin.geminaccountservice.repository.TransactionRepository;
import com.gemin.geminaccountservice.repository.UserRepository;
import com.gemin.geminaccountservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;


    @Override
    @Transactional
    public DepositResponseDto depositFunds(DepositAccountRequestDto depositRequestDto) {

        Account account = accountRepository.getAccountByAccountNumber(depositRequestDto.getReceiverAccountNumber())
                .orElseThrow(
                        () -> {
                            throw new ResourceNotFoundException("Account not available");
                        }
                );

        BigDecimal newBalance = account.getAccountBalance().add(depositRequestDto.getAmount());

        log.info("Funding account of :: [{}] :: with :: [{}] ::", account.getAccountNumber(), newBalance);
        account.setAccountBalance(newBalance);
        accountRepository.save(account);
        recordTransaction(DepositResponseDto
                .builder()
                .depositAccountNumber(depositRequestDto.getReceiverAccountNumber())
                .isTransactionSuccessful(true)
                .amount(depositRequestDto.getAmount())
                .receiverName(depositRequestDto.getReceiver())
                .statusCode(AppConstant.Status.SUCCESSFUL.getCode())
                .build(),account);

        return DepositResponseDto
                .builder()
                .depositAccountNumber(depositRequestDto.getReceiverAccountNumber())
                .isTransactionSuccessful(true)
                .receiverName(depositRequestDto.getReceiver())
                .statusCode(AppConstant.Status.SUCCESSFUL.getCode())
                .build();

    }

    @Override
    public PaginatedResponse<Transaction> getTransactions(int start, int limit) {
        return null;
    }

    private void recordTransaction(DepositResponseDto depositResponseDto, Account account){
        String transactionId = UUID.randomUUID().toString();
        Transaction transaction = Transaction.builder()
                .transactionId(transactionId)
                .account(account)
                .transactionStatus(TransactionStatus.SUCCESS)
                .transactionType(TransactionType.DEPOSIT)
                .receiverAccountNumber(depositResponseDto.getDepositAccountNumber())
                .receiver(depositResponseDto.getReceiverName())
                .sender(depositResponseDto.getReceiverName())
                .amount(depositResponseDto.getAmount())
                .build();

        transactionRepository.save(transaction);
    }
}
