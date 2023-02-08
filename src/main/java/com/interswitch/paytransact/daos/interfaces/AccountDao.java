package com.interswitch.paytransact.daos.interfaces;

import com.interswitch.paytransact.entities.Account;

public interface AccountDao extends BaseDao {

    Integer create(Account account);

    void update(Account account);

    Account getAccountById(Integer accountId);

    Account getAccountByUserId(Integer userId);

    Account getAccountByAccountNumber(Long accountNumber);

    Account getAccountByCardNumber(Long cardNumber);

    boolean existsAccountByUserId(Integer userid);
}
