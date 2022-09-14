/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogInSystem;

import java.util.ArrayList;

/**
 *
 * @author Xuan Thanh
 */
public class Main {

    public static void main(String[] args) {
        GettingData getData = new GettingData();
        UserManagement function = new UserManagement();
        ArrayList<Account> accountList = new ArrayList<>();
        while (true) {
            //step 1: display the menu
            function.displayMenu();
            //step 2: let user to choose function
            int choice = getData.getChoice();
            //step 3: perform function base on user's choice
            switch (choice) {
                case 1: {
                    //create new account
                    function.createAccount(accountList);
                    break;
                }
                case 2: {
                    //log in system
                    function.logIn(accountList);
                    break;
                }
                case 3: {
                    //exit the program
                    System.exit(0);
                }
            }
        }
    }
}
