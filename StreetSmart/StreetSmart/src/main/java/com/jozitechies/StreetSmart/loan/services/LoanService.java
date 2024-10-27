package com.jozitechies.StreetSmart.loan.services;

import com.jozitechies.StreetSmart.loan.Loan;
import com.jozitechies.StreetSmart.loan.MicroLoan;
import com.jozitechies.StreetSmart.loan.CapitalLoan;

import java.util.ArrayList;
import java.util.List;

public class LoanService {
    private List<Loan> loans = new ArrayList<>();

    public Loan applyForLoan(String loanType, Double amount, Double interestRate, int termMonths, String borrowerId) {
        Loan loan = switch (loanType) {
            case "MicroLoan" -> new MicroLoan(amount, interestRate, termMonths, borrowerId);
            case "CapitalLoan" -> new CapitalLoan(amount, interestRate, termMonths, borrowerId);
            default -> throw new IllegalArgumentException("Invalid loan type");
        };

        loans.add(loan);
        System.out.println("Loan application successful for " + borrowerId);
        return loan;
    }

    public void repayLoan(Long loanId, Double paymentAmount) {
        // Locate loan by ID and apply payment, update balance, etc.
    }

    public List<Loan> getBorrowerLoans(String borrowerId) {
        List<Loan> borrowerLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getBorrowerId().equals(borrowerId)) {
                borrowerLoans.add(loan);
            }
        }
        return borrowerLoans;
    }

    // Additional methods for approving loans, checking repayment schedules, etc.
}
