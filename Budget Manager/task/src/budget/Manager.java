package budget;

import java.text.DecimalFormat;
import java.util.ArrayList;

//Class Manager to make action with budget
public class Manager {
    private double balance;
    private ArrayList<String> purchaseList;
    private double total;
    DecimalFormat decimalFormat; //decimalformat to output number with 2 number after decimal .##

    public Manager() {
        balance = 0;
        total = 0;
        purchaseList = new ArrayList<>();
        decimalFormat = new DecimalFormat("##.##");
    }
    //Getters and setters
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<String> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(ArrayList<String> purchaseList) {
        this.purchaseList = purchaseList;
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
    public void addPurchase(String purchase) {
        purchaseList.add(purchase);
        double price = Double.parseDouble(purchase.substring(purchase.indexOf("$") + 1));
        if (balance <= price) {
            balance = 0;
        } else {
            balance -= price;
        }
    }
    //Show list of purchases and calculate total of purchases
    public void showListOfPurchase() {
        if (purchaseList.size() > 0) {
            for (String purchase :
                    purchaseList) {
                total += Double.parseDouble(purchase.substring(purchase.indexOf("$") + 1));
            }
            System.out.println();
            purchaseList.forEach(System.out::println);
            System.out.println("Total sum: $" + decimalFormat.format(total) + "\n");
        } else {
            System.out.println("\nThe purchase list is empty\n");
        }
    }
}
