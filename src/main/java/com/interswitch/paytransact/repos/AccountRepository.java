package com.interswitch.paytransact.repos;

import com.interswitch.paytransact.entities.Account;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Id> {
    Account getAccountById(Long id);

    Optional<Account> getAccountByAccountNumber(Long accountNumber);

    Optional<Account> getAccountByCardNumber(Long cardNumber);

    boolean existsAccountByUser_Id(Long id);
}
