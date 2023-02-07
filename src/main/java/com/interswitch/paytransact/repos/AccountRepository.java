package com.interswitch.paytransact.repos;

import com.interswitch.paytransact.entities.Account;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Id> {

}
