package com.interswitch.paytransact.services.interfaces;

import com.interswitch.paytransact.dtos.AccountDto;
import com.interswitch.paytransact.dtos.PaymentDto;
import com.interswitch.paytransact.entities.Transaction;
import com.interswitch.paytransact.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    void processTransaction(PaymentDto paymentDto);

    Optional<List<Transaction>> getTransactionListByAccount(AccountDto accountDto) throws NotFoundException;
}
