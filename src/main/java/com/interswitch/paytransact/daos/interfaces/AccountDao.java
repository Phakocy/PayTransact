package com.interswitch.paytransact.daos.interfaces;

import com.interswitch.paytransact.entities.Account;

public interface AccountDao extends BaseDao {

    void create(Account account);

    Account getAccountByUserId(Integer id);

    Account getAccountByAccountNumber(Long accountNumber);

    Account getAccountByCardNumber(Long cardNumber);

    boolean existsAccountByUserId(Integer id);
}
