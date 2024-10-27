package com.jozitechies.StreetSmart.loan;

public class MicroLoan extends Loan {
    private static final Double MAX_AMOUNT = 5000.0;  // Example constraint for micro-loans

    public MicroLoan(Double amount, Double interestRate, int termMonths, String borrowerId) {
        if (amount > MAX_AMOUNT) {
            throw new IllegalArgumentException("MicroLoan amount exceeds maximum limit");
        }
        // Set attributes here
    }

    // Additional MicroLoan-specific methods
}
