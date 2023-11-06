package com.api.bank.BankModelLayer.Infrastructure.Investment;

import com.api.bank.BankModelLayer.Application.TypeOfInvestment;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Investment {
    TypeOfInvestment typeOfInvestment;
    List<Date> period;
    BigDecimal value;
}
