package com.interswitch.paytransact.controllers;

import com.interswitch.paytransact.dtos.AccountDto;
import com.interswitch.paytransact.dtos.PaymentDto;
import com.interswitch.paytransact.entities.Transaction;
import com.interswitch.paytransact.entities.commons.ApiResponse;
import com.interswitch.paytransact.services.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("transfer")
    ResponseEntity<ApiResponse> processTransaction(@RequestBody PaymentDto paymentDto) {
        transactionService.processTransaction(paymentDto);
        return new ResponseEntity<>(new ApiResponse("you have successfully transferred " + paymentDto.getAmount() + " to " + paymentDto.getAccountNumber()), HttpStatus.OK);
    }

    @GetMapping("transactions")
    ResponseEntity<ApiResponse> getTransactions(@RequestBody AccountDto accountDto) {
        List<Transaction> transactionList = transactionService.getTransactionListByAccount(accountDto);
        return new ResponseEntity<>(new ApiResponse(transactionList, "fetched list of transactions successfully"), HttpStatus.OK);
    }
}
