package budget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SortCertainType implements SortPurchases {
    public static TypeOfPurchase type;

    public SortCertainType(TypeOfPurchase type) {
        SortCertainType.type = type;
    }

    @Override
    public void sort(Map<String, TypeOfPurchase> purchaseList) {
        List<String> list = new ArrayList<>();
        double sum = 0;
        for (Map.Entry<String, TypeOfPurchase> entry:
                purchaseList.entrySet()) {
            if (type == entry.getValue()) {
                list.add(entry.getKey());
                sum += Double.parseDouble(entry.getKey().substring(entry.getKey().lastIndexOf("$") + 1));
            }
        }
        if (!list.isEmpty()) {
            list.sort(new PurchasesComparator());
            list = list.stream().map(String::trim).collect(Collectors.toList());
            System.out.println();
            list.forEach(System.out::println);
            System.out.printf("Total sum: $%.2f\n", sum);
        } else {
            System.out.println("\nThe purchase list is empty!");
        }
    }
}
