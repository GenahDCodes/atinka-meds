import models.Drug;
import models.Supplier;
import utils.SortUtils;
import utils.SearchUtils;

import java.util.*;

public class TestAlgorithms {
    public static void main(String[] args) {
        // 1. Create sample drugs
        List<Drug> drugs = new ArrayList<>();

        Drug d1 = new Drug();
        d1.setCode("D003");
        d1.setName("Zinc");
        d1.setPrice(3.50);
        d1.setStockLevel(20);
        d1.setExpirationDate(new Date(System.currentTimeMillis() + 10000000));

        Drug d2 = new Drug();
        d2.setCode("D001");
        d2.setName("Aspirin");
        d2.setPrice(2.00);
        d2.setStockLevel(15);
        d2.setExpirationDate(new Date(System.currentTimeMillis() + 10000000));

        Drug d3 = new Drug();
        d3.setCode("D002");
        d3.setName("Paracetamol");
        d3.setPrice(5.00);
        d3.setStockLevel(50);
        d3.setExpirationDate(new Date(System.currentTimeMillis() + 10000000));

        drugs.add(d1);
        drugs.add(d2);
        drugs.add(d3);

        // 2. Sort by name
        System.out.println("Before sort by name:");
        printDrugs(drugs);

        SortUtils.mergeSortByName(drugs); // You can also test quickSortByName
        System.out.println("\nAfter sort by name:");
        printDrugs(drugs);

        // 3. Sort by price
        SortUtils.mergeSortByPrice(drugs);
        System.out.println("\nAfter sort by price:");
        printDrugs(drugs);

        // 4. Sort by code (QuickSort)
        SortUtils.quickSortByCode(drugs);
        System.out.println("\nAfter sort by code:");
        printDrugs(drugs);

        // 5. Search by code
        System.out.println("\nSearch for drug with code 'D002':");
        Drug resultByCode = SearchUtils.binarySearchByCode(drugs, "D002");
        System.out.println(resultByCode != null ? resultByCode.getName() : "Not found");

        // 6. Search by name
        System.out.println("\nSearch for drug with name 'Zinc':");
        Drug resultByName = SearchUtils.binarySearchByName(drugs, "Zinc");
        System.out.println(resultByName != null ? resultByName.getCode() : "Not found");

        // 7. Search by supplier (linear)
        Supplier supplierA = new Supplier();
        d1.getSuppliers().add(supplierA);
        d3.getSuppliers().add(supplierA);

        System.out.println("\nDrugs supplied by 'S01':");
        List<Drug> bySupplier = SearchUtils.searchBySupplier(drugs, "S01");
        for (Drug d : bySupplier) {
            System.out.println(d.getName());
        }
    }

    // Helper to print a list of drugs
    private static void printDrugs(List<Drug> drugs) {
        for (Drug d : drugs) {
            System.out.println(d.getCode() + " - " + d.getName() + " - $" + d.getPrice());
        }
    }
}
