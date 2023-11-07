package com.api.bank.BankDataLayer.Repository;

import com.api.bank.BankDataLayer.DTO.AccountSecurityData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountSecurityDataRepository extends JpaRepository<AccountSecurityData, Integer> {
}
