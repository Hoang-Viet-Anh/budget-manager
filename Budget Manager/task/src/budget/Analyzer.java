package budget;

import java.util.List;
import java.util.Map;

public class Analyzer {
    public SortPurchases sortPurchases;
    
    public Analyzer(SortPurchases sortPurchases) {
        this.sortPurchases = sortPurchases;
    }

    public void changeSort(SortPurchases sortPurchases) {
        this.sortPurchases = sortPurchases;
    }
    
    public void sort(Map<String, TypeOfPurchase> purchaseList) {
       sortPurchases.sort(purchaseList);
    }
}
