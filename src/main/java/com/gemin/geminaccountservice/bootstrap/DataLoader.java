package com.gemin.geminaccountservice.bootstrap;

import com.gemin.geminaccountservice.dto.request.RegisterUserRequestDto;
import com.gemin.geminaccountservice.entity.Account;
import com.gemin.geminaccountservice.entity.User;
import com.gemin.geminaccountservice.repository.AccountRepository;
import com.gemin.geminaccountservice.repository.UserRepository;
import com.gemin.geminaccountservice.service.TransactionService;
import com.gemin.geminaccountservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataLoader {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    @PostConstruct
    @Transactional
    @Order(1)
    private void init() {
        log.info("DataLoader init- :: ");
        Optional<User> optionalUser1 = userRepository.getUserByEmail("test1-email@gmail.com");
        Optional<User> optionalUser2 = userRepository.getUserByEmail("test2-email@gmail.com");
        Optional<Account> optionalAccount = accountRepository.getAccountByAccountNumber(123456700);

        if (optionalUser1.isEmpty()) {

            log.info("Loading data to the DB ----->>");
            RegisterUserRequestDto requestDto = RegisterUserRequestDto.builder()
                    .firstName("zurum")
                    .lastName("ogbonda")
                    .password("1234567")
                    .confirmPassword("1234567")
                    .phoneNumber("08012345678")
                    .email("test1-email@gmail.com")
                    .build();
            userService.registerUser(requestDto);
        }

        if (optionalUser2.isEmpty()) {
            RegisterUserRequestDto requestDto = RegisterUserRequestDto.builder()
                    .firstName("zurum")
                    .lastName("ogbonda")
                    .password("1234567")
                    .confirmPassword("1234567")
                    .phoneNumber("08012345678")
                    .email("test2-email@gmail.com")
                    .build();
            userService.registerUser(requestDto);

            User user = userRepository.getUserByEmail("test2-email@gmail.com").get();
//            accountRepository.save(Account.builder()
//                    .accountBalance(BigDecimal.valueOf(0.0))
//                    .accountNumber(123456700L)
//                    .accountCurrency(AccountCurrency.EUR)
//                    .accountType(AccountType.CURRENT)
//                    .isActivated(true)
//                    .user(user)
//                    .build());

        }


//        if (optionalAccount.isEmpty()) {
//
//            accountRepository.save(Account.builder()
//                    .accountBalance(BigDecimal.valueOf(0.0))
//                    .accountNumber(123456700L)
//                    .accountCurrency(AccountCurrency.EUR)
//                    .accountType(AccountType.CURRENT)
//                    .isActivated(true)
//                    .id("1234567")
//                    .build());
//            DepositAccountRequestDto depositAccountRequestDto = DepositAccountRequestDto
//                    .builder()
//                    .receiver("mr gemini")
//                    .sender("mr gemini")
//                    .amount(BigDecimal.valueOf(100.0))
//                    .receiverAccountNumber(123456700L)
//                    .build();
//            transactionService.depositFunds(depositAccountRequestDto);
//
//            log.info("Doonneee!!!");
//        }
    }
}
