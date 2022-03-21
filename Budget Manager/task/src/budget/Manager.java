package budget;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

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
}
