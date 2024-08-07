/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studyprojects.atm;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Andrey Karev
 */
public class Bank {
    
    /**
     * The name of the bank.
     */
    private String name;
    
    /**
     * The list of users for this bank.
     */
    private ArrayList<User> users;
    
    /**
     * The list of accounts for this bank.
     */
    private ArrayList<Account> accounts;
    
    /**
     * Create a new Bank object with empty lists of users and accounts
     * @param name the Bank's name
     */
    public Bank(String name) {
        
        this.name =name;
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();
        
    }
    
    /**
     * Get name of a bank
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get a new universal unique ID for a user.
     * @return  the uuid
     */
    public String getNewUserUUID() {
        
        // inits
        String uuid;
        Random rng = new Random();
        int len = 12;
        boolean nonUnique;
        
        // continue looping while we get a unique ID
        do {
            
            // generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }
            
            nonUnique = false;
            // check to make shure it's unique
            for (User u : this.users) {
                if (uuid.compareTo(u.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
            
        } while(nonUnique);
        
        return uuid;
        
    }

    /**
     * Get a new universal unique ID for an account.
     * @return  the uuid
     */
    String getNewAccountUUID() {
    
        // inits
        String uuid;
        Random rng = new Random();
        int len = 12;
        boolean nonUnique;
        
        // continue looping while we get a unique ID
        do {
            
            // generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }
            
            nonUnique = false;
            // check to make shure it's unique
            for (Account a : this.accounts) {
                if (uuid.compareTo(a.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
            
        } while(nonUnique);
        
        return uuid;
        
    }
    
    /**
     * Add an account for bank.
     * @param anAcct    the account to add
     */
    void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }
    
    /**
     * Create a new user of the bank.
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param pin           the user's pin
     * @return the new User object
     */
    public User addUser(String firstName, String lastName, String pin) {
        
        // create a new User object and add it to our list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);
        
        // create a savings account for the user
        Account newAccount = new Account("Savings", newUser, this);
        
        // add to holder and bank lists
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);
        
        return newUser;
    }
    
    /**
     * Get the User object associated with a particular userID and pin,
     * if they valid
     * @param userID the UUID of the user to login
     * @param pin       the pin of the user to login
     * @return             the User object if the login is successful,
     *                           else null
     */
    public User userLogin(String userID, String pin) {
        
        // search throw list of users
        for (User u : this.users) {
            
            // check userID and pin is correct
            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }
        }
        
        // if we haven't found the user or pin is incorrect
        return null;
        
    }
    
}
