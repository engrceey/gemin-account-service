package com.gemin.geminaccountservice.service.impl;

import com.gemin.geminaccountservice.constants.enums.RegisterStatus;
import com.gemin.geminaccountservice.dto.request.DepositAccountRequestDto;
import com.gemin.geminaccountservice.dto.request.RegisterUserRequestDto;
import com.gemin.geminaccountservice.entity.Account;
import com.gemin.geminaccountservice.entity.User;
import com.gemin.geminaccountservice.exceptions.ResourceCreationException;
import com.gemin.geminaccountservice.exceptions.ResourceNotFoundException;
import com.gemin.geminaccountservice.repository.AccountRepository;
import com.gemin.geminaccountservice.repository.UserRepository;
import com.gemin.geminaccountservice.service.TransactionService;
import com.gemin.geminaccountservice.service.UserService;
import com.gemin.geminaccountservice.utils.AppUtil;
import com.gemin.geminaccountservice.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    @Override
    @Transactional
    public String registerUser(RegisterUserRequestDto registerUserRequestDto) {
        log.info("register user service");
        if (doesUserAlreadyExist(registerUserRequestDto.getEmail()) &&
                 registerUserRequestDto.getInitialCredit().compareTo(BigDecimal.ZERO) == 0) {
            throw new ResourceCreationException("User already exist");
        } else if (doesUserAlreadyExist(registerUserRequestDto.getEmail()) &&
                registerUserRequestDto.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {

            log.info("Here");
            User user = getUserByEmail(registerUserRequestDto.getEmail());
            Account account = accountRepository.getReferenceById(user.getId());
            log.info("here two");
             transactionService.depositFunds(
                    DepositAccountRequestDto.builder()
                            .amount(registerUserRequestDto.getInitialCredit())
                            .sender("self")
                            .receiverAccountNumber(account.getAccountNumber())
                            .receiver("self")
                            .build());
             return RegisterStatus.SUCCESS.getRegistrationStatus();
        }

        User newUser = new User();
        ModelMapperUtils.map(registerUserRequestDto,newUser);
        userRepository.save(newUser);

        Account newAccount = new Account();
        long newAccountNumber = getNewAccountNumber();
        newAccount.setAccountNumber(newAccountNumber);
        newAccount.setUser(newUser);
        accountRepository.save(newAccount);

        return RegisterStatus.SUCCESS.getRegistrationStatus();

    }


    private boolean doesAccountAlreadyExit(long accountNumber) {
        return accountRepository.getAccountByAccountNumber(accountNumber).isPresent();
    }

    private boolean doesUserAlreadyExist(String email) {
        return userRepository.getUserByEmail(email).isPresent();
    }

    private User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email).orElseThrow(
                () -> {
                    throw new ResourceNotFoundException("user not found");
                }
        );
    }

    private long getNewAccountNumber() {
        long newAccountNumber = AppUtil.generateAccountNumber();
        while (doesAccountAlreadyExit(newAccountNumber)) newAccountNumber = AppUtil.generateAccountNumber();
        return newAccountNumber;

    }
}
