package budget;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

//Class Manager to make action with budget
public class Manager {
    private double balance;
    private Map<String, TypeOfPurchase> purchaseList;
    private double total;

    public Manager() {
        balance = 0;
        total = 0;
        purchaseList = new LinkedHashMap<>();
    }
    //Getters and setters
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPurchaseList(Map<String, TypeOfPurchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public Map<String, TypeOfPurchase> getPurchaseList() {
        return purchaseList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    //Top up balance
    public void addBalance(double amount) {
        balance += amount;
    }
    //Add purchase to list and change balance
    public void addPurchase(String purchase, TypeOfPurchase type) {
        StringBuilder stringBuilder = new StringBuilder(purchase);
        String amount = stringBuilder.reverse().delete(stringBuilder.indexOf("$"), stringBuilder.length())
                                     .reverse().toString();
        purchaseList.put(purchase, type);
        double price = Double.parseDouble(amount);
        total += price;
        if (balance <= price) {
            balance = 0;
        } else {
            balance -= price;
        }
    }
    //Show list of purchases and calculate total of purchases
    public void showListOfPurchase(TypeOfPurchase type) {
        double typeSum = 0;
        if (purchaseList.size() > 0) {
            System.out.println("\n" + type.toString() + ":");
            for (Map.Entry<String, TypeOfPurchase> entry :
                    purchaseList.entrySet()) {
                if (type == TypeOfPurchase.ALL) {
                    System.out.println(entry.getKey());
                } else {
                    if (entry.getValue() == type) {
                        typeSum += Double.parseDouble(entry.getKey().substring(entry.getKey().indexOf("$") + 1));
                        System.out.println(entry.getKey());
                    }
                }
            }
            System.out.printf("Total sum: $%.2f\n\n", (type == TypeOfPurchase.ALL ? total : typeSum));
        } else {
            System.out.println("\nThe purchase list is empty");
        }
    }
    //Save current balance and list of purchases
    public void savePurchases() throws IOException {
        PrintWriter printWriter = new PrintWriter("purchases.txt");
        printWriter.println(balance);
        for (Map.Entry<String, TypeOfPurchase> entry :
             purchaseList.entrySet()) {
            printWriter.println(entry.getKey() + " " + entry.getValue());
        }
        printWriter.close();
    }
    //Load balance and list of purchases from file purchases.txt
    public void loadPurchases() throws IOException {
        File file = new File("purchases.txt");
        Scanner scanner = new Scanner(file);
        String purchase, type;
        StringBuilder stringBuilder, stringBuilder1;
        double savedBalance = 0;

        if (scanner.hasNext()) {
            savedBalance = Double.parseDouble(scanner.nextLine());
        }

        while (scanner.hasNext()) {
            purchase = scanner.nextLine();
            stringBuilder = new StringBuilder(purchase);
            stringBuilder.reverse();
            stringBuilder1 = new StringBuilder(stringBuilder);
            type = stringBuilder1.delete(stringBuilder.indexOf(" "), stringBuilder1.length())
                                 .reverse().toString().toUpperCase(Locale.ROOT);
            purchase = stringBuilder.delete(0, stringBuilder.indexOf(" ")).reverse().toString();
            addPurchase(purchase, TypeOfPurchase.valueOf(type));
        }
        balance = savedBalance;
        scanner.close();
    }
    //Choice menu for 'add purchase' and 'show list of purchases'

    public void printChoiceMenu(boolean bool) {
        System.out.println("\nChoose the type of purchase");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        if (bool) {
            System.out.println("5) All");
        }
        System.out.println("6) Back\n");
    }

    //Menu by choice
    public void printMenu() {
        System.out.println("Choose your action:");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("5) Save");
        System.out.println("6) Load");
        System.out.println("7) Analyze (Sort)");
        System.out.println("0) Exit");
    }

    public void addIncome(Scanner scanner) {
        System.out.println("\nEnter amount:");
        addBalance(scanner.nextDouble());
        System.out.println("Income was added!\n");
    }

    public void addPurchasesMenu(Scanner scanner, Scanner scanner1) {
        String choice, purchase;
        double amount;
        while (true) {
            printChoiceMenu(false);
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
            addPurchase(purchase, TypeOfPurchase.values()[Integer.parseInt(choice) - 1]);
            System.out.println("Purchase was added!");
        }
    }

    public void showListOfPurchasesMenu(Scanner scanner) {
        String choice;
        while (true) {
            printChoiceMenu(true);
            choice = scanner.nextLine();

            if (choice.equals("6")) {
                System.out.println();
                break;
            }

            showListOfPurchase(TypeOfPurchase.values()[Integer.parseInt(choice) - 1]);
        }
    }

    public void showBalance() {
        System.out.printf("\nBalance: $%.2f\n\n", getBalance());
    }

    public void showSaveMenu() {
        try {
            savePurchases();
        } catch (IOException ignored){}
        System.out.println("\nPurchases were saved!\n");
    }

    public void showLoadMenu() {
        try {
            loadPurchases();
        } catch (IOException ignored){}
        System.out.println("\nPurchases were loaded!\n");
    }

    public void showAnalyzeMenu(Scanner scanner) {
        SortCertainType sortCertainType = new SortCertainType(TypeOfPurchase.ALL);
        SortAllPurchases sortAllPurchases = new SortAllPurchases();
        SortByType sortByType = new SortByType();
        Analyzer analyzer = new Analyzer(sortAllPurchases);
        String choice;
        analyze:
        while (true) {
            System.out.println("\nHow do you want to sort?");
            System.out.println("1) Sort all purchases");
            System.out.println("2) Sort by type");
            System.out.println("3) Sort certain type");
            System.out.println("4) Back");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    analyzer.changeSort(sortAllPurchases);
                    break;
                case "2":
                    analyzer.changeSort(sortByType);
                    break;
                case "3":
                    sort:
                    while (true) {
                        System.out.println("\nChoose the type of purchase");
                        System.out.println("1) Food");
                        System.out.println("2) Clothes");
                        System.out.println("3) Entertainment");
                        System.out.println("4) Other");
                        choice = scanner.nextLine();

                        switch (choice) {
                            case "1":
                            case "2":
                            case "3":
                            case "4":
                                SortCertainType.type = TypeOfPurchase.values()[Integer.parseInt(choice) - 1];
                                analyzer.changeSort(sortCertainType);
                                break sort;
                            default:
                                break;
                        }
                    }
                    break;
                case "4":
                    System.out.println();
                    break analyze;
                default:
                    break;
            }
            analyzer.sort(purchaseList);
        }
    }

    public void showExitMenu() {
        System.out.println("\nBye!");
        System.exit(0);
    }

    public void startManager() {
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
