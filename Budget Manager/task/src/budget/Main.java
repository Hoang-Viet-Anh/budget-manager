package budget;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        String choice, purchase;
        double amount = 0;
        Manager budgetManager = new Manager();
        //decimalformat to output number with 2 number after decimal .##
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        double formattedNumber;

        //Main loop of program
        while (true) {
            System.out.println("Choose your action:");
            System.out.println("1) Add income");
            System.out.println("2) Add purchase");
            System.out.println("3) Show list of purchases");
            System.out.println("4) Balance");
            System.out.println("0) Exit");
            choice = scanner.nextLine();

            switch (choice) {
                //Add income to balance
                case "1":
                    System.out.println("\nEnter amount:");
                    amount = scanner1.nextDouble();
                    budgetManager.addBalance(amount);
                    System.out.println("Income was added!\n");
                    break;
                //Add purchase to list and change balance
                case "2":
                    System.out.println("\nEnter purchase name:");
                    purchase = scanner.nextLine();
                    System.out.println("Enter its price:");
                    amount = scanner1.nextDouble();
                    purchase = purchase.concat(" $" + amount);
                    budgetManager.addPurchase(purchase);
                    System.out.println("Purchase was added!\n");
                    break;
                //Show list of purchases and total of it
                case "3":
                    budgetManager.showListOfPurchase();
                    break;
                //Show balance
                case "4":
                    formattedNumber = Double.parseDouble(decimalFormat.format(budgetManager.getBalance()));
                    System.out.println("\nBalance: $" + formattedNumber + "\n");
                    break;
                //Exit
                case "0":
                    System.out.println("\nBye!");
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}
