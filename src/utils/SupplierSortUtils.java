package utils;

import models.Supplier;
import java.util.List;

public class SupplierSortUtils {

    // Insertion Sort by Supplier Name (A-Z)
    public static void insertionSortByName(List<Supplier> suppliers) {
        for (int i = 1; i < suppliers.size(); i++) {
            Supplier key = suppliers.get(i);
            int j = i - 1;
            while (j >= 0 && suppliers.get(j).getName().compareToIgnoreCase(key.getName()) > 0) {
                suppliers.set(j + 1, suppliers.get(j));
                j--;
            }
            suppliers.set(j + 1, key);
        }
    }

    // Insertion Sort by Delivery Time (Ascending)
    public static void insertionSortByDeliveryTime(List<Supplier> suppliers) {
        for (int i = 1; i < suppliers.size(); i++) {
            Supplier key = suppliers.get(i);
            int j = i - 1;
            while (j >= 0 && suppliers.get(j).getDeliveryTime() > key.getDeliveryTime()) {
                suppliers.set(j + 1, suppliers.get(j));
                j--;
            }
            suppliers.set(j + 1, key);
        }
    }
}
