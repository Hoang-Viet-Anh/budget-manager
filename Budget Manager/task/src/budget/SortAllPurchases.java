package budget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SortAllPurchases implements SortPurchases {
    @Override
    public void sort(Map<String, TypeOfPurchase> purchaseList) {
        if (!purchaseList.isEmpty()) {
            List<String> list = new ArrayList<>(purchaseList.keySet());
            list.sort(new PurchasesComparator());
            list = list.stream().map(String::trim).collect(Collectors.toList());
            System.out.println();
            list.forEach(System.out::println);
        } else {
            System.out.println("\nThe purchase list is empty!");
        }
    }
}
