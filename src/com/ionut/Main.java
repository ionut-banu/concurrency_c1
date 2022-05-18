package com.ionut;

public class Main {

    public static void main(String[] args) {
        var bankAccount = new BankAccount("12345-678", 1000.00);

        var trThread1 = new Thread(() -> {
            bankAccount.deposit(300.00);
            bankAccount.withdraw(50.00);
        });

        var trThread2 = new Thread(() -> {
            bankAccount.deposit(203.75);
            bankAccount.withdraw(100.00);
        });

        trThread1.start();
        trThread2.start();
    }
}
