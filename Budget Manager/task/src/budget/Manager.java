package budget;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
        purchaseList.put(purchase, type);
        double price = Double.parseDouble(purchase.substring(purchase.indexOf("$") + 1));
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
            System.out.println("Total sum: $" + (type == TypeOfPurchase.ALL ? total : typeSum));
        } else {
            System.out.println("\nThe purchase list is empty\n");
        }
    }
}
