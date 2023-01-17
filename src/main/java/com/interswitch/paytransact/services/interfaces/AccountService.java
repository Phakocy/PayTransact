package com.interswitch.paytransact.services.interfaces;

import com.interswitch.paytransact.dtos.AccountDto;
import com.interswitch.paytransact.entities.Account;

public interface AccountService {

    Account getAccountByUserEmail(AccountDto accountDto);

    Account getAccountDetailsByCardNumber(Long cardNumber);

    Account getAccountDetailsByAccountNumber(Long accountNumber);

    void createNewAccount(AccountDto email);
}
