/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studyprojects.atm;

import java.util.Date;

/**
 *
 * @author Andrey Karev
 */
public class Transaction {
    
    /**
     * The amount of this transaction.
     */
    private double amount;
    
    /**
     * The time and date of this transaction.
     */
    private Date timestamp;
    
    /**
     * A memo for this transaction.
     */
    private String memo;
    
    /**
     * The account in which transaction was performed.
     */
    private Account inAccount;
    
    /**
     * Create a new transaction.
     * @param amount    the amount transacted
     * @param inAccount the account the transaction belong to
     */
    public Transaction(double amount, Account inAccount) {
        
        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";
        
    }
    
    /**
     * Create a new transaction.
     * @param amount    the amount transacted
     * @param inAccount the account the transaction belong to
     * @param memo      the memo for the transaction
     */
    public Transaction(double amount, Account inAccount, String memo) {
        
        this(amount, inAccount);
        this.memo = memo;
        
    }
    
    /**
     * Get the amount of the transaction
     * @return the amount
     */
    public double getAmount() {
        return this.amount;
    }
    
    /**
     * Get summary line of the transaction to look at
     * @return string summary line
     */
    public String getSummaryLine() {
        
        if (this.amount >= 0) {
            return String.format("%s : $%.02f  : %s\n", this.timestamp.toString(), this.amount, this.memo);
        } else {
            return String.format("%s : $(%.02f)  : %s\n", this.timestamp.toString(), this.amount, this.memo);
        }
        
    }
}
