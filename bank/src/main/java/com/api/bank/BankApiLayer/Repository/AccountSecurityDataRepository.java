package com.api.bank.BankApiLayer.Repository;

import com.api.bank.BankApiLayer.Entity.Data.AccountSecurityData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountSecurityDataRepository extends JpaRepository<AccountSecurityData, Integer> {
    AccountSecurityData findByCpf(String cpf); // Método para buscar por CPF

    void deleteByCpf(String cpf); // Método para excluir por CPF
}
