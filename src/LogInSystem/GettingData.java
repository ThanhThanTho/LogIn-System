/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogInSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Xuan Thanh
 */
public class GettingData {

    Scanner sc = new Scanner(System.in);

    //get valid account name
    public String getName() {
        //loop till user input valid account name
        while (true) {
            System.out.print("Enter user name: ");
            String name = sc.nextLine();
            //check if name is empty
            if (name.isEmpty()) {
                System.out.println("User name must not empty. Please try again!");
                //check if name is less than 5 letters and contain space
            } else if (name.length() < 5 | name.contains(" ")) {
                System.out.println("User name must be at least 5 letters, and no space."
                );
            } else {
                return name;
            }
        }
    }

    //get valid password
    public String getPass() {
        //loop till user input valid password
        while (true) {
            System.out.print("Enter password: ");
            String name = sc.nextLine();
            //check if password is empty 
            if (name.isEmpty()) {
                System.out.println("Password must not empty. Please try again!");
                //check if password is less than 6 letters or contain space
            } else if (name.length() < 6 | name.contains(" ")) {
                System.out.println("Password must be at least 6 letters, and no space."
                );
            } else {
                return name;
            }
        }
    }

    //get user's function choice
    public int getChoice() {
        //loop till user input valid choice
        while (true) {
            String input = sc.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                //check if choice is out of range
                if (choice <= 0 | choice > 3) {
                    throw new InputMismatchException();
                }
                return choice;
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a number!");
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please try again!");
            }
        }
    }

}
