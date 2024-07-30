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
    
}
