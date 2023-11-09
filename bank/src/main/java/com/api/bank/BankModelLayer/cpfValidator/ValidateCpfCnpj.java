package com.api.bank.BankModelLayer.cpfValidator;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ValidateCpfCnpj {
    private boolean validated;
    private String cpfCnpj;

    public ValidateCpfCnpj(String cpfCnpj) {
        this.validated = false;
        this.cpfCnpj = cpfCnpj;
    }

    public boolean validate() {
        if (cpfCnpj == null || cpfCnpj.isEmpty()) {
            return false;
        }

        int charCount = cpfCnpj.length();

        if (charCount == 11) {
            String newCpfCnpj = calculateDigits(cpfCnpj.substring(0, 9), 10);
            newCpfCnpj = calculateDigits(newCpfCnpj, 11);

            if (newCpfCnpj.equals(cpfCnpj)) {
                validated = true;
                return true;
            }
        } else if (charCount == 14) {
            String newCpfCnpj = calculateDigits(cpfCnpj.substring(0, 12), 5);
            newCpfCnpj = calculateDigits(newCpfCnpj, 6);

            if (newCpfCnpj.equals(cpfCnpj)) {
                validated = true;
                return true;
            }
        }
        return false;
    }

    public String formatted() {
        if (!validated) {
            if (cpfCnpj == null || !validate()) {
                throw new IllegalArgumentException("Provide a valid CPF/CNPJ for formatting.");
            }
        }

        int charCount = cpfCnpj.length();

        if (charCount == 11) {
            String cpf = cpfCnpj;

            return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
        } else if (charCount == 14) {
            String cnpj = cpfCnpj;

            return cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12);
        }
        return "";
    }

    private String calculateDigits(String sliceCpfCnpj, int initialMultiplier) {
        if (sliceCpfCnpj == null || sliceCpfCnpj.isEmpty()) {
            return "";
        }

        int sum = 0;
        int multiplier = initialMultiplier;
        for (int key = 0; key < sliceCpfCnpj.length(); key++) {
            sum += Character.getNumericValue(sliceCpfCnpj.charAt(key)) * multiplier;

            multiplier--;
        }

        int remainder = 11 - (sum % 11);
        remainder = (remainder >= 10) ? 0 : remainder;

        return sliceCpfCnpj + remainder;
    }


    public String getCpfCnpj() {
        validated = false;
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = extractNumbers(cpfCnpj);
    }

    private String extractNumbers(String cpfCnpj) {
        if (cpfCnpj == null) {
            return "";
        }

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(cpfCnpj);
        StringBuilder numbers = new StringBuilder();

        while (matcher.find()) {
            numbers.append(matcher.group());
        }

        return numbers.toString();
    }
}

