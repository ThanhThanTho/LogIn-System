/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogInSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Xuan Thanh
 */
public class UserManagement {

    Scanner sc = new Scanner(System.in);
    GettingData getData = new GettingData();

    //display the menu
    public void displayMenu() {
        System.out.println("====== USER MANAGEMENT SYSTEM ======");
        System.out.println("1. Create new account");
        System.out.println("2. Log in system");
        System.out.println("3. Exit");
        System.out.print(">Choose: ");
    }

    //create data file
    public void createFile(String fileName) {
        File file = new File(fileName);
        //check if file existed
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
            }
        }
    }

    //get data from the file
    public ArrayList<Account> getFileData(String fileName) {
        ArrayList<Account> accountList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bfr = new BufferedReader(isr);
            String line = bfr.readLine();
            //loop to access every line of data in the file
            while (line != null) {
                String[] array = line.split(";");
                //check if there are enugh data
                if (array.length == 2) {
                    accountList.add(new Account(array[0], array[1]));
                }
                line = bfr.readLine();
            }
            fis.close();
            isr.close();
            bfr.close();
        } catch (IOException e) {
            System.out.println("There aren't any account data. Please add some.");
        }
        return accountList;
    }

    //check if account name is existed
    public boolean isExisted(ArrayList<Account> accountList, String name) {
        //loop to access every account in the account list
        for (int i = 0; i < accountList.size(); i++) {
            //check if account name is equal with any other in the list
            if (accountList.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    //add new account to the account list
    public void addAccount(ArrayList<Account> accountList) {
        String name = "";
        //loop to let user re-entered if account name is existed
        do {
            name = getData.getName();
            if (isExisted(accountList, name)) {
                System.out.println("Account name already existed. Please choose another name!");
            }
            //check if account name is existed
        } while (isExisted(accountList, name));
        String pass = getData.getPass();
        accountList.add(new Account(name, pass));
    }

    //write data in the account list to the file
    public void writeDataToFile(ArrayList<Account> accountList) {
        try {
            FileWriter fw = new FileWriter("user.dat");
            //loop to access every account in the account list
            for (int i = 0; i < accountList.size(); i++) {
                fw.write(accountList.get(i).getName() + ";" + accountList.get(i).getPass() + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //handle the information user input
    public void logInHandling(ArrayList<Account> accountList, Account acc) {
        boolean check = false;
        //loop to access every account in the account list
        for (int i = 0; i < accountList.size(); i++) {
            //check if the users's account is match with any account in the list
            if (accountList.get(i).getName().equals(acc.getName())
                    && accountList.get(i).getPass().equals(acc.getPass())) {
                check = true;
                System.out.println("Log in successful!");
                break;
            }
        }
        if (check == false) {
            System.out.println("Invalid user name or password");
        }
    }

    //create new account 
    public void createAccount(ArrayList<Account> accountList) {
        createFile("user.dat");
        accountList = getFileData("user.dat");
        addAccount(accountList);
        writeDataToFile(accountList);
    }

    //log in system
    public void logIn(ArrayList<Account> accountList) {
        String name = getData.getName();
        String pass = getData.getPass();
        accountList.clear();
        accountList = getFileData("user.dat");
        logInHandling(accountList, new Account(name, pass));
    }
}
