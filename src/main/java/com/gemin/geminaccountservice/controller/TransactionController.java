package com.gemin.geminaccountservice.controller;


import com.gemin.geminaccountservice.dto.request.DepositAccountRequestDto;
import com.gemin.geminaccountservice.dto.response.ApiResponse;
import com.gemin.geminaccountservice.dto.response.DepositResponseDto;
import com.gemin.geminaccountservice.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class TransactionController {

    private final TransactionService transactionService;

    @ApiOperation(
            value = "Deposit funds",
            response = DepositResponseDto.class
    )
    @PostMapping(path = "/deposit-funds")
    public ResponseEntity<ApiResponse<DepositResponseDto>> depositFund(@RequestBody @Valid final DepositAccountRequestDto depositRequestDto) {
        log.info("initiate endpoint to deposit fund [{}] ::", depositRequestDto.getReceiver());

        DepositResponseDto response = transactionService.depositFunds(depositRequestDto);

        return ResponseEntity.ok(ApiResponse.<DepositResponseDto>builder()
                .isSuccessful(true)
                .statusMessage("funds deposited successfully")
                .data(response)
                .build()
        );
    }

//    @GetMapping(path = "transactions", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ApiResponse<PaginatedResponse<Transaction>>> getAllTransaction(
//            @RequestParam(required = false, defaultValue = "0") int start,
//            @RequestParam(required = false, defaultValue = "5") int limit
//    ) {
//
//        log.info("Transaction getAllTransaction - fetching all transactions");
//        PaginatedResponse<Transaction> response = transactionService.getTransactions(start, limit);
//
//        return ResponseEntity.ok().body(ApiResponse.<PaginatedResponse<Transaction>>builder()
//                .isSuccessful(true)
//                .statusMessage("Fetched Transaction")
//                .data(response)
//                .build()
//        );
//    }


}
