/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studyprojects.atm;

import java.util.ArrayList;

/**
 *
 * @author Andrey Karev
 */
public class Account {
    
    /**
     * The name of the account.
     */
    private String name;
    
    /**
     * The current balance of the account.
     */
    private double balance;
    
    /**
     * The account ID number.
     */
    private String uuid;
    
    /**
     * The user object that owns this account.
     */
    private User holder;
    
    /**
     * The list of transactions for this account.
     */
    private ArrayList<Transaction> transactions;
    
    /**
     * Create a new account
     * @param name the account's name
     * @param holder the account's holder
     * @param theBank the Bank object that issues the account
     */
    public Account(String name, User holder, Bank theBank) {
        
        // set the account name and holder
        this.name = name;
        this.holder = holder;
        
        // get new account UUID
        this.uuid = theBank.getNewAccountUUID();
        
        // init transactions
        this.transactions = new ArrayList<>();
        
    }

    /**
     * Return the account's UUID
     * @return the uuid
     */
    public String getUUID() {
        return this.uuid;
    }
    
    /**
     * Get summary line for the account
     * @return the string summary
     */
    public String getSummaryLine() {
        
        // get the account's balance
        double balance = this.getBalance();
        
        // format the summary line, depending on the whether
        // balance is negative
        if (balance >= 0) {
            return String.format("%s : $%.02f  : %s", this.uuid, balance, this.name);
        } else {
            return String.format("%s : $(%.02f ) : %s", this.uuid, balance, this.name);
        }
        
    }
    
    /**
     * Get balance of the account by adding amounts
     * of the transactions
     * @return the balance value
     */
    public double getBalance() {
        
        double balance = 0;
        for (Transaction t: this.transactions) {
            balance += t.getAmount();
        }
        
        return balance;
        
    }
    
    /**
     * Print the transaction history of the account
     */
    public void printTransHistory() {
        
        System.out.printf("\nTransaction history for account %s\n", this.uuid);
        for (int t = this.transactions.size() - 1; t >= 0; t--) {
            System.out.printf(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * Add transaction to the list
     * @param amount    amount of the transaction
     * @param memo      memo of the transaction
     */
    void addTransaction(double amount, String memo) {
        Transaction newTransaction = new Transaction(amount, this, memo);
        this.transactions.add(newTransaction);
    }
    
}
