package com.interswitch.paytransact.controllers;

import com.interswitch.paytransact.dtos.AccountDto;
import com.interswitch.paytransact.entities.commons.ApiResponse;
import com.interswitch.paytransact.services.interfaces.AccountService;
import com.interswitch.paytransact.services.interfaces.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private HistoryService historyService;

    @GetMapping("")
    ResponseEntity<ApiResponse> getAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(new ApiResponse(accountService.getAccountByUserEmail(accountDto), "account details fetched successfully"), HttpStatus.OK);
    }

    @PostMapping("/create")
    ResponseEntity<ApiResponse> createAccount(@RequestBody AccountDto accountDto) {
        accountService.createNewAccount(accountDto);
        return new ResponseEntity<>(new ApiResponse("account created successfully"), HttpStatus.OK);
    }

//    @GetMapping("/history")
//    ResponseEntity<ApiResponse> getAccountHistory(@RequestBody AccountDto accountDto) {
//        List<History> historyList = historyService.getAccountHistory(accountDto);
//        return new ResponseEntity<>(new ApiResponse(historyList, "fetched list of account's history"), HttpStatus.OK);
//    }
}
