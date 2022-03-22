package budget;

import java.util.ArrayList;
import java.util.Map;

public class SortByType implements SortPurchases {

    @Override
    public void sort(Map<String, TypeOfPurchase> purchaseList) {
        ArrayList<String> typesList = new ArrayList<>();
        double sum = 0, total = 0;

        for (TypeOfPurchase type:
                TypeOfPurchase.values()) {
            if (type != TypeOfPurchase.ALL) {
                for (Map.Entry<String, TypeOfPurchase> entry :
                        purchaseList.entrySet()) {
                    if (entry.getValue() == type) {
                        sum += Double.parseDouble(entry.getKey().substring(entry.getKey().lastIndexOf("$") + 1));
                    }
                }
                typesList.add(String.format("%s - $%.2f", type.toString(), sum));
                total += sum;
                sum = 0;
            }
        }
        typesList.sort(new PurchasesComparator());
        System.out.println("\nTypes:");
        typesList.forEach(System.out::println);
        System.out.println("Total sum: $" + total);
    }
}
