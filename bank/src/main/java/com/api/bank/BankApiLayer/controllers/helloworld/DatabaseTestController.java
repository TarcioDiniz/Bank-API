package com.api.bank.BankApiLayer.controllers.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@RequestMapping("/test")
public class DatabaseTestController {

    @Autowired
    private JdbcTemplate jdbcTemplate; // Injeta o JdbcTemplate para executar consultas SQL.

    @GetMapping("/database-connection")
    public String checkDatabaseConnection() {
        try {
            // Tente executar uma consulta simples para verificar a conexão com o banco.
            jdbcTemplate.queryForList("SELECT 1");
            return "Conexão com o banco de dados estabelecida com sucesso!";
        } catch (Exception e) {
            return "Erro ao estabelecer conexão com o banco de dados: " + e.getMessage();
        }
    }
}
