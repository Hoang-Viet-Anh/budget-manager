package budget;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        String choice, purchase;
        double amount = 0;
        Manager budgetManager = new Manager();

        //Main loop of program
        while (true) {
            System.out.println("Choose your action:");
            System.out.println("1) Add income");
            System.out.println("2) Add purchase");
            System.out.println("3) Show list of purchases");
            System.out.println("4) Balance");
            System.out.println("5) Save");
            System.out.println("6) Load");
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
                    while (true) {
                        System.out.println("\nChoose the type of purchase");
                        System.out.println("1) Food");
                        System.out.println("2) Clothes");
                        System.out.println("3) Entertainment");
                        System.out.println("4) Other");
                        System.out.println("5) Back");
                        choice = scanner.nextLine();

                        if (choice.equals("5")) {
                            System.out.println();
                            break;
                        }

                        System.out.println("\nEnter purchase name:");
                        purchase = scanner.nextLine();
                        System.out.println("Enter its price:");
                        amount = scanner1.nextDouble();
                        purchase = purchase.concat(" $" + String.format("%.2f", amount));
                        budgetManager.addPurchase(purchase, TypeOfPurchase.values()[Integer.parseInt(choice) - 1]);
                        System.out.println("Purchase was added!");
                    }
                    break;
                //Show list of purchases and total of it
                case "3":
                    while (true) {
                        System.out.println("\nChoose the type of purchase");
                        System.out.println("1) Food");
                        System.out.println("2) Clothes");
                        System.out.println("3) Entertainment");
                        System.out.println("4) Other");
                        System.out.println("5) All");
                        System.out.println("6) Back\n");
                        choice = scanner.nextLine();

                        if (choice.equals("6")) {
                            System.out.println();
                            break;
                        }

                        budgetManager.showListOfPurchase(TypeOfPurchase.values()[Integer.parseInt(choice) - 1]);
                    }
                    break;
                //Show balance
                case "4":
                    System.out.printf("\nBalance: $%.2f\n\n",budgetManager.getBalance());
                    break;
                //Save balance and list of purchases
                case "5":
                    try {
                        budgetManager.savePurchases();
                    } catch (IOException ignored){}
                    System.out.println("\nPurchases were saved!\n");
                    break;
                //Load from file purchases.txt balance and list of purchases
                case "6":
                    try {
                       budgetManager.loadPurchases();
                    } catch (IOException ignored){}
                    System.out.println("\nPurchases were loaded!\n");
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
