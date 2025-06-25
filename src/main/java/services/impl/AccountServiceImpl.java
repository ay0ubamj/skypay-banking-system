package services.impl;

import entities.Transaction;
import enums.TransactionType;
import lombok.Getter;
import services.AccountService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static enums.TransactionType.DEPOSIT;
import static enums.TransactionType.WITHDRAW;

@Getter
public class AccountServiceImpl implements AccountService {
    private int balance = 0;
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/YYYY");

    @Override
    public void deposit(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        this.balance += amount;
        transactions.add(
                Transaction.builder()
                        .type(DEPOSIT)
                        .amount(amount)
                        .date(getCurrentDate())
                        .currentBalance(this.balance)
                        .build()
        );
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.balance -= amount;
        transactions.add(
                Transaction.builder()
                        .type(WITHDRAW)
                        .amount(amount)
                        .date(getCurrentDate())
                        .currentBalance(this.balance)
                        .build()
        );
    }

    @Override
    public void printStatement() {
        System.out.println("Date || Amount || Balance");

        List<Transaction> transactions = new ArrayList<>(this.transactions);
        Collections.reverse(transactions);

        for (Transaction transaction : transactions) {
            System.out.println(transaction.toString());
        }
    }

    private String getCurrentDate() {
        return LocalDate.now().format(DATE_FORMATTER);
    }
}
