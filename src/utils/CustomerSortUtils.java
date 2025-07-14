package utils;

import models.Customer;
import java.util.List;

public class CustomerSortUtils {

    // Insertion Sort by Customer Name (A-Z)
    public static void insertionSortByName(List<Customer> customers) {
        for (int i = 1; i < customers.size(); i++) {
            Customer key = customers.get(i);
            int j = i - 1;
            while (j >= 0 && customers.get(j).getName().compareToIgnoreCase(key.getName()) > 0) {
                customers.set(j + 1, customers.get(j));
                j--;
            }
            customers.set(j + 1, key);
        }
    }

    // Insertion Sort by Customer ID (Ascending)
    public static void insertionSortById(List<Customer> customers) {
        for (int i = 1; i < customers.size(); i++) {
            Customer key = customers.get(i);
            int j = i - 1;
            while (j >= 0 && customers.get(j).getId().compareToIgnoreCase(key.getId()) > 0) {
                customers.set(j + 1, customers.get(j));
                j--;
            }
            customers.set(j + 1, key);
        }
    }
}
