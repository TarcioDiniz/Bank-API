package com.api.bank.v1.core.repository;

import com.api.bank.v1.core.data.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmailAndPassword(String email, String password);

    Optional<Account> findById(Long id);

    Optional<Account> findByPixKeys(String pixKey);

}
