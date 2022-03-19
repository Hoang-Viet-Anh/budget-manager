package budget;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> purchaseList = new ArrayList<>();
        String purchase = "";
        double total = 0;
        int index = 0;

        do {
            purchase = scanner.nextLine();
            purchaseList.add(purchase);
            index = purchase.indexOf("$") + 1;
            total += Double.parseDouble(purchase.substring(index));
        } while (scanner.hasNext());

        purchaseList.forEach(System.out::println);

        System.out.println("\nTotal: $" + total);
    }
}
