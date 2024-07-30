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
    
}
