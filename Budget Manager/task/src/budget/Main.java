package budget;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        String choice;
        Manager budgetManager = new Manager();

        //Main loop of program
        while (true) {
            budgetManager.printMenu();
            choice = scanner.nextLine();

            switch (choice) {
                //Add income to balance
                case "1":
                    budgetManager.addIncome(scanner1);
                    break;
                //Add purchase to list and change balance
                case "2":
                    budgetManager.addPurchasesMenu(scanner, scanner1);
                    break;
                //Show list of purchases and total of it
                case "3":
                    budgetManager.showListOfPurchasesMenu(scanner);
                    break;
                //Show balance
                case "4":
                    budgetManager.showBalance();
                    break;
                //Save balance and list of purchases
                case "5":
                    budgetManager.showSaveMenu();
                    break;
                //Load from file purchases.txt balance and list of purchases
                case "6":
                    budgetManager.showLoadMenu();
                    break;
                //Sort purchases
                case "7":
                    budgetManager.showAnalyzeMenu(scanner);
                    break;
                //Exit
                case "0":
                    budgetManager.showExitMenu();
                default:
                    break;
            }
        }
    }
}
