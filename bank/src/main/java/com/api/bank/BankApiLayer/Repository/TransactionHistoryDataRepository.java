package com.api.bank.BankApiLayer.Repository;

import com.api.bank.BankApiLayer.Entity.Data.TransactionHistoryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionHistoryDataRepository extends JpaRepository<TransactionHistoryData, Integer> {
    TransactionHistoryData findByCpf(String cpf); // Método para buscar por CPF

    void deleteByCpf(String cpf); // Método para excluir por CPF
}
