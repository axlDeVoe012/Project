package com.jozitechies.StreetSmart.loan;

import java.util.Date;

public abstract class Loan {
    private Long loanId;
    private Double amount;
    private Double interestRate;
    private int termMonths;
    private Date startDate;
    private String borrowerId;

    // Constructors, getters, setters, and common loan methods
    public Double calculateMonthlyRepayment() {
        double monthlyInterestRate = interestRate / 12 / 100;
        return amount * monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -termMonths));
    }

    public String getBorrowerId() {
        return null;
    }

}
