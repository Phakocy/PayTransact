package com.interswitch.paytransact.services.interfaces;

import com.interswitch.paytransact.dtos.AccountDto;
import com.interswitch.paytransact.dtos.PaymentDto;
import com.interswitch.paytransact.entities.Transaction;
import com.interswitch.paytransact.exceptions.NotFoundException;

import java.util.List;

public interface TransactionService {
    void processTransaction(PaymentDto paymentDto);

    List<Transaction> getTransactionListByAccount(AccountDto accountDto) throws NotFoundException;
}
