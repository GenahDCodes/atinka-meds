//src/main/Main.java
package main;


import java.time.LocalDate;
import models.*;
import services.*;


public class Main {
    public static void main(String[] args) {
        FileService fileService = new FileService();
        SupplierService supplierService = new SupplierService(fileService);
        CustomerService customerService = new CustomerService(fileService);

        // ✅ Add sample supplier and customer
        Supplier sampleSupplier = new Supplier("PharmaPro", "SUP001", "Accra", 3);
        supplierService.addSupplier(sampleSupplier);

        Customer sampleCustomer = new Customer("CUST001", "Hikma", "0240000000", "Tamale");
        customerService.addCustomer(sampleCustomer);

        // ✅ Create sample transaction
        Transaction sampleTransaction = new Transaction(
                "CUST001",
                "SUP001",
                "Paracetamol",
                5,
                LocalDate.now()
        );
        customerService.addTransaction(sampleTransaction);

        // ✅ Display data
        System.out.println("\n--- All Suppliers ---");
        supplierService.printSuppliers();

        System.out.println("\n--- All Customers ---");
        customerService.printCustomers();

        System.out.println("\n--- Transactions for Customer CUST001 ---");
        customerService.getCustomerTransactions("CUST001").forEach(System.out::println);
    }
}
