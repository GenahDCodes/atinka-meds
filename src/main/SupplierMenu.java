// main/SupplierMenu.java
package main;

import java.time.LocalDate;
import java.util.Scanner;
import models.Supplier;
import models.Transaction;
import services.CustomerService;
import services.FileService;
import services.SupplierService;
import java.util.List;

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
            System.out.println("0. Go back to Main Menu"); 
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    String name;
                    while (true) {
                        System.out.print("Enter name: ");
                        name = scanner.nextLine();
                        if (name.matches("^[a-zA-Z\\s]+$")) { 
                            break;
                        } else {
                            System.out.println("Invalid name format. Name should only contain letters and spaces. Please re-enter.");
                        }
                    }
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter location: ");
                    String location = scanner.nextLine();
                    
                    int deliveryTime;
                    while (true) {
                        System.out.print("Enter delivery time (in days): ");
                        try {
                            deliveryTime = Integer.parseInt(scanner.nextLine());
                            if (deliveryTime <= 0) {
                                System.out.println("Delivery time must be positive. Please re-enter.");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid delivery time format. Please enter a number. Please re-enter.");
                        }
                    }

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
                    System.out.println("Suppliers sorted by name:"); 
                    supplierService.printSuppliers(); 
                    break;

                case "6":
                    supplierService.sortSuppliersByDeliveryTime();
                    System.out.println("Suppliers sorted by delivery time:"); 
                    supplierService.printSuppliers(); 
                    break;

                case "7":
                    System.out.print("Enter location to filter: ");
                    String filterLocation = scanner.nextLine();
                    List<Supplier> filteredSuppliers = supplierService.filterByLocation(filterLocation);
                    if (filteredSuppliers.isEmpty()) {
                        System.out.println("No suppliers found for location: " + filterLocation);
                    } else {
                        System.out.println("Suppliers filtered by location '" + filterLocation + "':");
                        filteredSuppliers.forEach(System.out::println);
                    }
                    break;

                case "8":
                    int maxTime;
                    while (true) {
                        System.out.print("Enter max delivery time: ");
                        try {
                            maxTime = Integer.parseInt(scanner.nextLine());
                            if (maxTime <= 0) {
                                System.out.println("Max delivery time must be positive. Please re-enter.");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid delivery time format. Please enter a number. Please re-enter.");
                        }
                    }
                    List<Supplier> suppliersByDeliveryTime = supplierService.filterByDeliveryTime(maxTime);
                    if (suppliersByDeliveryTime.isEmpty()) {
                        System.out.println("No suppliers found with delivery time less than or equal to " + maxTime + " days.");
                    } else {
                        System.out.println("Suppliers with delivery time less than or equal to " + maxTime + " days:");
                        suppliersByDeliveryTime.forEach(System.out::println);
                    }
                    break;

                case "9":
                    supplierService.printSuppliers();
                    break;
                
                case "10":
                    System.out.print("Enter supplier ID: ");
                    String suppId = scanner.nextLine();
                    List<Transaction> supplierTxns = customerService.getSupplierTransactions(suppId);
                    if (supplierTxns.isEmpty()) {
                        System.out.println("No transactions found for supplier ID: " + suppId);
                    } else {
                        System.out.println("Transactions for Supplier ID " + suppId + ":");
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
                    
                    int qty;
                    while (true) {
                        System.out.print("Enter quantity: ");
                        try {
                            qty = Integer.parseInt(scanner.nextLine());
                            if (qty <= 0) {
                                System.out.println("Quantity must be positive. Please re-enter.");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid quantity format. Please enter a number. Please re-enter.");
                        }
                    }

                    Transaction newTxn = new Transaction(customerId, supplierId, drugCode, qty, LocalDate.now());
                    customerService.addTransaction(newTxn);
                    System.out.println("Transaction logged for supplier.");
                    break;

                case "0":
                    exit = true;
                    System.out.println("Returning to Main Menu..."); 
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}