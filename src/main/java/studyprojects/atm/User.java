/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studyprojects.atm;

import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Andrey Karev
 */
public class User {
    
    /**
     * The first name of the user.
     */
    private String firstName;
    
    /**
     * The last name of the user.
     */
    private String lastName;
    
    /**
     * The ID number of the user.
     */
    private String uuid;
    
    /**
     * The MD5 hash of the user's pin number.
     */
    private byte pinHash[];
    
    /**
     * The list of accounts for this user.
     */
    private ArrayList<Account> accounts;
    
    /**
     * Create a new user
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param pin           the user's account pin number
     * @param theBank   the Bank object that the user is a customer of 
     */
    public User(String firstName, String lastName, String pin, Bank theBank) {
        
        // set user's name
        this.firstName = firstName;
        this.lastName = lastName;
        
        // store the pin's MD5 hash, rather than the original value, for
        // security reasons
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("error, caught NoSuchAlgorithmException");
            System.exit(1);
        }
        
        // get a new, unique universal ID for the user
        this.uuid = theBank.getNewUserUUID();
        
        // create an empty list of accounts
        this.accounts = new ArrayList<>();
        
        // print log message
        System.out.printf("New user %s %s with ID %s created.\n", this.firstName,
                this.lastName, this.uuid);
    }
    
    /**
     * Add an account for user.
     * @param anAcct    the account to add
     */
    void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    /**
     * Return the user's UUID.
     * @return the uuid
     */
    public String getUUID() {
        return this.uuid;
    }
    
    /**
     * Return the user's first name.
     * @return the first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Check whether a given pin matches the true user pin.
     * @param aPin  the pin to check
     * @return          true if pin is valid, else false
     */
    public boolean validatePin(String aPin) {
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()),
                    this.pinHash);
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("error, caught NoSuchAlgorithmException");
            System.exit(1);
        }
        
        return false;
        
    }
    
    /**
     * Print the summary for the accounts of this user
     */
    public void printAccountsSummary() {
        
        System.out.printf("\n\n%s's accounts summary\n", this.firstName);
        for (int a = 0; a < this.accounts.size(); a++) {
            System.out.printf("%d) %s\n", a+1,
                    this.accounts.get(a).getSummaryLine());
            
        }
        
    }

    /**
     * Get number of accounts for the user
     * @return the number of accounts
     */
    public int numAccounts() {
        return this.accounts.size();
    }
    
    /**
     * Print transaction history for a particular account
     * @param acctIdx   the index of the account to use
     */
    public void printAcctTransHistory(int acctIdx) {
        
        this.accounts.get(acctIdx).printTransHistory();
        
    }
    
    /**
     * Get balance of chosen account
     * @param acctIdx   index of the account
     * @return balance of the account
     */
    public double getAcctBalance(int acctIdx) {
        return this.accounts.get(acctIdx).getBalance();
    }
    
    /**
     * Get UUID of chosen account
     * @param acctIdx index of the account
     * @return balance of the account
     */
    public String getAcctUUID(int acctIdx) {
        return this.accounts.get(acctIdx).getUUID();
    }

    /**
     * Add transaction to chosen account's transactions list
     * @param acctIdx     index of the account
     * @param amount    amount of the transaction
     * @param memo      memo of the transaction
     */
    public void addAccountTransaction(int acctIdx, double amount, String memo) {
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }
    
}
