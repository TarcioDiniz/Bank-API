package com.api.bank.BankApiLayer.Repository;

import com.api.bank.BankApiLayer.model.AccountSecurityData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountSecurityDataRepository extends JpaRepository<AccountSecurityData, Integer> {
    AccountSecurityData findByCpf(int cpf); // Método para buscar por CPF

    void deleteByCpf(int cpf); // Método para excluir por CPF
}
