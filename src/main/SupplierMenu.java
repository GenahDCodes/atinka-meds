package main;

import java.time.LocalDate;
import java.util.Scanner;
import models.Supplier;
import models.Transaction;
import services.CustomerService;
import services.FileService;
import services.SupplierService;

public class SupplierMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileService fileService = new FileService();
        SupplierService supplierService = new SupplierService(fileService);
        CustomerService customerService = new CustomerService(fileService);


        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== Supplier Management Menu =====");
            System.out.println("1. Add Supplier");
            System.out.println("2. Remove Supplier");
            System.out.println("3. Search Supplier by ID");
            System.out.println("4. Search Supplier by Name");
            System.out.println("5. Sort Suppliers by Name");
            System.out.println("6. Sort Suppliers by Delivery Time");
            System.out.println("7. Filter by Location");
            System.out.println("8. Filter by Delivery Time");
            System.out.println("9. Display All Suppliers");
            System.out.println("10. View Supplier Transactions");
            System.out.println("11. Log New Transaction for Supplier");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter location: ");
                    String location = scanner.nextLine();
                    System.out.print("Enter delivery time (in days): ");
                    int deliveryTime = Integer.parseInt(scanner.nextLine());

                    Supplier newSupplier = new Supplier(name, id, location, deliveryTime);
                    supplierService.addSupplier(newSupplier);
                    System.out.println("Supplier added.");
                    break;

                case "2":
                    System.out.print("Enter supplier ID to remove: ");
                    String removeId = scanner.nextLine();
                    supplierService.removeSupplier(removeId);
                    System.out.println("Supplier removed.");
                    break;

                case "3":
                    System.out.print("Enter supplier ID to search: ");
                    String searchId = scanner.nextLine();
                    Supplier foundById = supplierService.searchSupplierById(searchId);
                    System.out.println(foundById != null ? foundById : "Supplier not found.");
                    break;

                case "4":
                    System.out.print("Enter supplier name to search: ");
                    String searchName = scanner.nextLine();
                    Supplier foundByName = supplierService.searchSupplierByName(searchName);
                    System.out.println(foundByName != null ? foundByName : "Supplier not found.");
                    break;

                case "5":
                    supplierService.sortSuppliersByName();
                    System.out.println("Suppliers sorted by name.");
                    break;

                case "6":
                    supplierService.sortSuppliersByDeliveryTime();
                    System.out.println("Suppliers sorted by delivery time.");
                    break;

                case "7":
                    System.out.print("Enter location to filter: ");
                    String filterLocation = scanner.nextLine();
                    supplierService.filterByLocation(filterLocation).forEach(System.out::println);
                    break;

                case "8":
                    System.out.print("Enter max delivery time: ");
                    int maxTime = Integer.parseInt(scanner.nextLine());
                    supplierService.filterByDeliveryTime(maxTime).forEach(System.out::println);
                    break;

                case "9":
                    supplierService.printSuppliers();
                    break;
                
                case "10":
                    System.out.print("Enter supplier ID: ");
                    String suppId = scanner.nextLine();
                    var supplierTxns = customerService.getSupplierTransactions(suppId);
                    if (supplierTxns.isEmpty()) {
                        System.out.println("No transactions found.");
                    } else {
                        supplierTxns.forEach(System.out::println);
                    }
                    break;

                case "11":
                    System.out.print("Enter customer ID: ");
                    String customerId = scanner.nextLine();
                    System.out.print("Enter supplier ID: ");
                    String supplierId = scanner.nextLine();
                    System.out.print("Enter drug code: ");
                    String drugCode = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int qty = Integer.parseInt(scanner.nextLine());

                    Transaction newTxn = new Transaction(customerId, supplierId, drugCode, qty, LocalDate.now());
                    customerService.addTransaction(newTxn);
                    System.out.println("Transaction logged for supplier.");
                    break;

                case "0":
                    exit = true;
                    System.out.println("Exiting Supplier Management...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}
