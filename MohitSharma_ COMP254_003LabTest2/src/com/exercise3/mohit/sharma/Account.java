package com.exercise3.mohit.sharma;

public class Account {
    private int accountNumber;
    private String customerName;
    private double accountBalance;

    public Account(int number, String name, double balance) {
        this.accountNumber = number;
        this.customerName = name;
        this.accountBalance = balance;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public String toString() {
        return accountNumber + " - " + customerName + ": $" + accountBalance;
    }
}
