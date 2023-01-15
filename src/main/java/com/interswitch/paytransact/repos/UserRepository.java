package com.interswitch.paytransact.repos;

import com.interswitch.paytransact.entities.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Id> {
    Optional<User> findUserById(Long id);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
