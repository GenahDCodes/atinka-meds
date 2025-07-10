// package utils;

// /**
//  * Provides search algorithms for the application, such as binary search.
//  */
// public class SearchUtils {
//     // Example: Binary search implementation
//     public static int binarySearch(int[] arr, int key) {
//         // To be implemented
//         return -1;
//     }

// } 

package utils;

import models.Drug;
import java.util.List;

public class SearchUtils {

    // BINARY SEARCH BY CODE
    public static Drug binarySearchByCode(List<Drug> drugs, String code) {
        SortUtils.quickSortByCode(drugs);  // optional: Sort by code first
        int low = 0, high = drugs.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Drug midDrug = drugs.get(mid);
            int cmp = midDrug.getCode().compareToIgnoreCase(code);

            if (cmp == 0) return midDrug;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }

        return null;
    }

    // BINARY SEARCH BY NAME
    public static Drug binarySearchByName(List<Drug> drugs, String name) {
        SortUtils.mergeSortByName(drugs);  // sort before searching
        int low = 0, high = drugs.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Drug midDrug = drugs.get(mid);
            int cmp = midDrug.getName().compareToIgnoreCase(name);

            if (cmp == 0) return midDrug;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }

        return null;
    }

    // LINEAR SEARCH BY SUPPLIER ID
    public static List<Drug> searchBySupplier(List<Drug> drugs, String supplierId) {
        return drugs.stream()
            .filter(d -> d.getSuppliers().stream()
                .anyMatch(s -> s.getId().equalsIgnoreCase(supplierId)))
            .toList();
    }
}
