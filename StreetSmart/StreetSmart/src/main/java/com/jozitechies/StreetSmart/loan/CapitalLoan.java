package com.jozitechies.StreetSmart.loan;

public class CapitalLoan extends Loan {
    private static final Double MIN_AMOUNT = 5000.0;

    public CapitalLoan(Double amount, Double interestRate, int termMonths, String borrowerId) {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException("CapitalLoan amount is below minimum limit");
        }
        // Set attributes here
    }

    // Additional CapitalLoan-specific methods
}
