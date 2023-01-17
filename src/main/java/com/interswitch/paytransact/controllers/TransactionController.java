package com.interswitch.paytransact.controllers;

import com.interswitch.paytransact.dtos.PaymentDto;
import com.interswitch.paytransact.entities.commons.ApiResponse;
import com.interswitch.paytransact.services.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionServiceImpl transactionService;

    @PostMapping("/transfer")
    ResponseEntity<ApiResponse> processTransaction(@RequestBody PaymentDto paymentDto) {
        transactionService.processTransaction(paymentDto);
        return new ResponseEntity<>(new ApiResponse("you have successfully transferred " + paymentDto.getAmount() + " to " + paymentDto.getAccountNumber()), HttpStatus.OK);
    }
}
