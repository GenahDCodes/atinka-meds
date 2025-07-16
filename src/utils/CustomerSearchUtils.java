//src/utils/CustomerSearchUtils.java
package utils;

import models.Customer;
import java.util.List;

public class CustomerSearchUtils {

    // Binary search by ID (list must be sorted by ID before using this)
    public static Customer binarySearchById(List<Customer> customers, String id) {
        int low = 0;
        int high = customers.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Customer midCustomer = customers.get(mid);
            int cmp = midCustomer.getId().compareToIgnoreCase(id);

            if (cmp == 0) return midCustomer;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }

        return null;
    }

    // Linear search by name
    public static Customer linearSearchByName(List<Customer> customers, String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }
}
