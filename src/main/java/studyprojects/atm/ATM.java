/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package studyprojects.atm;

import java.util.Scanner;

/**
 *
 * @author Andrey Karev
 */
public class ATM {

    public static void main(String[] args) {
        
        // init Scanner
        Scanner sc = new Scanner(System.in);
        
        // init Bank
        Bank theBank = new Bank("Increadible bank");
        
        // create a user, witch also creates a Savings account
        User aUser = theBank.addUser("Andrew", "Karev", "6677");
        
        // add a checking account for our user
        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);
        
        User curUser;
        while(true) {
            
            // stay in the login prompt until the successful login
            curUser = ATM.mainMenuPrompt(theBank, sc);
            
            // stay in main menu until the user quits
            ATM.printUserMenu(curUser, sc);
            
        }
        
    }
    
    /**
     * Print the ATM's login menu.
     * @param theBank   the current Bank object
     * @param sc            the Scanner object
     * @return                  authUser
     */
    public static User mainMenuPrompt(Bank theBank, Scanner sc) {
         
        // inits
        String userID;
        String pin;
        User authUser;
        
        // prompt the user for a ID/pin combo until the correct one
        // is reached
        do {
            
            System.out.printf("\n\nWelcome to %s!\n\n", theBank.getName());
            System.out.print("Enter user ID: ");
            userID = sc.nextLine();
            System.out.println();
            System.out.print("Enter pin: ");
            pin = sc.nextLine();
            System.out.println();
            
            // try to get the User object corresponding to the ID and pin combo
            authUser = theBank.userLogin(userID, pin);
            
            if (authUser == null) {
                System.out.println("User ID or pin was incorrect. Try again.");
            }
            
        }while(authUser == null);
        
        return authUser;
        
    }
    
    
    public static void printUserMenu(User curUser, Scanner sc) {
        
        // print a summary of the user's accounts
        curUser.printAccountsSummary();
        
        // init
        int choice;
        
        // user menu
        do {
            System.out.printf("Welcome, %s, what would you like to do?\n",
                    curUser.getFirstName());
            System.out.println("    1) Show account transaction history");
            System.out.println("    2) Withdraw");
            System.out.println("    3) Deposit");
            System.out.println("    4) Transfer");
            System.out.println("    5) Quit");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invlid choice. Please, choose 1-5.");
            }
        }while(choice < 1 || choice > 5);
        
        // process the choice
        switch (choice) {
            
            case 1: ATM.showTransHistory(curUser, sc);
            break;
            
            case 2: ATM.withdrawFunds(curUser, sc);
            break;
            
            case 3: ATM.depositFunds(curUser, sc);
            break;
            
            case 4: ATM.transferFunds(curUser, sc);
            break;
            
       }
        
        // redisplay this menu until the user wants to quit
        if (choice != 5) {
            ATM.printUserMenu(curUser, sc);
        }
        
    }
    
    /**
     * Show the transaction history for an account
     * @param theUser   the logged-in User object
     * @param sc            the Scanner object
     */
    public static void showTransHistory(User theUser, Scanner sc) {
            
            int theAcct;
            
            // get account whose transaction history to look at
            do {
                System.out.printf("Enter the number (1-%d) of the account\n"
                        + " whose transactions you want to see: ",
                        theUser.numAccounts());
                theAcct = sc.nextInt() - 1;
                if (theAcct < 0 || theAcct >= theUser.numAccounts()) {
                    System.out.println("Invalid account. Try again.");
                }
            }while(theAcct < 0 || theAcct >= theUser.numAccounts());
            
            // print the transaction history
            theUser.printAcctTransHistory(theAcct);
            
        }

    /**
     * Transferring funds between accounts
     * @param theUser   the logged-in User object
     * @param sc            the Scanner object
     */
    public static void transferFunds(User theUser, Scanner sc) {
    
        // inits
        int fromAcct;
        int toAcct;
        double amount;
        double acctBalance;
        
        // get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account\n"
                        + " to transfer from: ", theUser.numAccounts());
            fromAcct = sc.nextInt() - 1;
            if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account. Try again.");
            }
        }while(fromAcct < 0 || fromAcct >= theUser.numAccounts());
        
        acctBalance = theUser.getAcctBalance(fromAcct);
        
        // get the account to transfer to
        do {
            System.out.printf("Enter the number (1-%d) of the account\n"
                        + " to transfer to: ", theUser.numAccounts());
            toAcct = sc.nextInt() - 1;
            if (toAcct < 0 || toAcct >= theUser.numAccounts() || toAcct == fromAcct) {
                System.out.println("Invalid account. Try again.");
            }
        }while(fromAcct < 0 || fromAcct >= theUser.numAccounts() || toAcct == fromAcct);
        
        // get the amount to transfer
        do {
            System.out.printf("Enter the amount to transfer (max: %.02f): ", acctBalance);
            amount = sc.nextInt();
            if (amount < 0 || amount > acctBalance) {
                System.out.println("Invalid amount value. Try again.");
            }
        } while(amount < 0 || amount > acctBalance);
        
        // finally, do the transfer
        theUser.addAccountTransaction(fromAcct, -1*amount,
                String.format("Transfer to account %s", theUser.getAcctUUID(toAcct)));
        theUser.addAccountTransaction(toAcct, amount,
                String.format("Transfer from account %s", theUser.getAcctUUID(fromAcct)));
        
    }
    
    /**
     * Process a fund withdraw from an account
     * @param theUser   the logged-in User object
     * @param sc            the Scanner object
     */
    public static void withdrawFunds(User theUser, Scanner sc) {
        
        // inits
        int fromAcct;
        double amount;
        double acctBalance;
        String memo;
        
        // get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account\n"
                        + " to transfer from: ", theUser.numAccounts());
            fromAcct = sc.nextInt() - 1;
            if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account. Try again.");
            }
        }while(fromAcct < 0 || fromAcct >= theUser.numAccounts());
        
        acctBalance = theUser.getAcctBalance(fromAcct);
        
        // get the amount to transfer
        do {
            System.out.printf("Enter the amount to transfer (max: %.02f): ", acctBalance);
            amount = sc.nextInt();
            if (amount < 0 || amount > acctBalance) {
                System.out.println("Invalid amount value. Try again.");
            }
        } while(amount < 0 || amount > acctBalance);
        
        // gobble up rest of previous input
        sc.nextLine();
        
        // get a memo
        System.out.println("Enter a memo: ");
        memo = sc.nextLine();
        
        // do the withdraw
        theUser.addAccountTransaction(fromAcct, -1*amount, memo);
        
    }
    
    /**
     * Process a fund deposit to an account
     * @param theUser   the logged-in User object
     * @param sc            the Scanner object
     */
    public static void depositFunds(User theUser, Scanner sc) {
        
        // inits
        int toAcct;
        double amount;
        double acctBalance;
        String memo;
        
        // get the account to transfer from
        do {
            System.out.printf("Enter the number (1-%d) of the account\n"
                        + " to deposit: ", theUser.numAccounts());
            toAcct = sc.nextInt() - 1;
            if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account. Try again.");
            }
        }while(toAcct < 0 || toAcct >= theUser.numAccounts());
        
        acctBalance = theUser.getAcctBalance(toAcct);
        
        // get the amount to transfer
        do {
            System.out.printf("Enter the amount to transfer: ", acctBalance);
            amount = sc.nextInt();
            if (amount < 0) {
                System.out.println("Invalid amount value. Try again.");
            }
        } while(amount < 0);
        
        // gobble up rest of previous input
        sc.nextLine();
        
        // get a memo
        System.out.println("Enter a memo: ");
        memo = sc.nextLine();
        
        // do the withdraw
        theUser.addAccountTransaction(toAcct, amount, memo);
        
    }
}
