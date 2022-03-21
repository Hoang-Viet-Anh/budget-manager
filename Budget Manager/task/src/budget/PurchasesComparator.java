package budget;

import java.util.Comparator;

public class PurchasesComparator implements Comparator<String> {

    @Override
    public int compare(String t1, String t2) {
        double price1 = Double.parseDouble(t1.substring(t1.lastIndexOf("$") + 1));
        double price2 = Double.parseDouble(t2.substring(t2.lastIndexOf("$") + 1));

        return Double.compare(price2, price1);
    }
}
