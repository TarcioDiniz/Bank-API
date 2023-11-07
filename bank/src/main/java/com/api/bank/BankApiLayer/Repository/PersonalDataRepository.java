package com.api.bank.BankApiLayer.Repository;

import com.api.bank.BankApiLayer.model.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDataRepository extends JpaRepository<PersonalData, Integer> {
    PersonalData findByCpf(int cpf); // Método para buscar por CPF

    void deleteByCpf(int cpf); // Método para excluir por CPF
}
