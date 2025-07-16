// File: src/utils/SupplierSearchUtils.java
package utils;

import models.Supplier;
import java.util.List;

public class SupplierSearchUtils {

    // Binary search for Supplier by ID (list must be sorted by ID)
    public static Supplier binarySearchById(List<Supplier> suppliers, String id) {
        int low = 0;
        int high = suppliers.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Supplier midSupplier = suppliers.get(mid);
            int cmp = midSupplier.getId().compareToIgnoreCase(id);

            if (cmp == 0) return midSupplier;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }

        return null; // Not found
    }

    // Linear search by Supplier Name
    public static Supplier linearSearchByName(List<Supplier> suppliers, String name) {
        for (Supplier s : suppliers) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    // Filter suppliers by location
    public static List<Supplier> filterByLocation(List<Supplier> suppliers, String location) {
        return suppliers.stream()
            .filter(s -> s.getLocation().equalsIgnoreCase(location))
            .toList();
    }

    // Filter suppliers by max delivery time
    public static List<Supplier> filterByDeliveryTime(List<Supplier> suppliers, int maxDays) {
        return suppliers.stream()
            .filter(s -> s.getDeliveryTime() <= maxDays)
            .toList();
    }
}

