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
}
