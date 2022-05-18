package com.ionut;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

class BankAccount {

    private double balance;
    private String accountNumber;
    private Lock lock;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.lock = new ReentrantLock();
    }

    //    public synchronized void deposit(double amount) {
    //        balance += amount;
    //    }
    //
    //    public synchronized void withdraw(double amount) {
    //        balance -= amount;
    //    }

    //    public void deposit(double amount) {
    //        synchronized (this) {
    //            balance += amount;
    //        }
    //    }
    //
    //    public void withdraw(double amount) {
    //        synchronized (this) {
    //            balance -= amount;
    //        }
    //    }

    public void deposit(double amount) {
        var status = false;
        try {
            if (lock.tryLock(1000, MILLISECONDS)) {
                try {
                    balance += amount;
                    status = true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Could not get the lock");
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("Transaction status = " + status);
    }

    public void withdraw(double amount) {
        var status = false;
        try {
            if (lock.tryLock(1000, MILLISECONDS)) {
                try {
                    balance -= amount;
                    status = true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Could not get the lock");
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("Transaction status = " + status);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void printAccountNumber() {
        System.out.println("Account number = " + accountNumber);
    }
}