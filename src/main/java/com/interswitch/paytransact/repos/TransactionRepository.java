package com.interswitch.paytransact.repos;

import com.interswitch.paytransact.entities.Transaction;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Id> {
    Optional<Transaction> findTransactionById(Integer id);

    Optional<List<Transaction>> findTransactionsByAccountId(Integer id);
}
