package com.interswitch.paytransact.services.interfaces;

import com.interswitch.paytransact.dtos.AccountDto;
import com.interswitch.paytransact.entities.Account;

public interface AccountService {

    Account getAccount(AccountDto accountDto);

    void createNewAccount(AccountDto email);
}
