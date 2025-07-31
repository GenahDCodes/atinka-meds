//main/AppMenu.java
package main;

import java.time.LocalDate;
import java.time.format.DateTimeParseException; 
import java.util.Scanner;
import models.Sale;
import services.TransactionService;
import services.FileService;

public class AppMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileService fileService = new FileService();
        TransactionService transactionService = new TransactionService(fileService);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Pharmacy Inventory System ===");
            System.out.println("1. Customer Management");
            System.out.println("2. Supplier Management");
            System.out.println("3. Drug Management");
            System.out.println("4. Log Sale");
            System.out.println("5. View Purchase History");
            System.out.println("6. View Recent Sales");
            System.out.println("0. Go back to Main Menu");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    CustomerMenu.main(null);
                    break;
                case "2":
                    SupplierMenu.main(null);
                    break;
                case "3":
                    DrugMenu.main(null);
                    break;
                case "4":
                    System.out.print("Enter drug code: ");
                    String drugCode = scanner.nextLine();
                    System.out.print("Enter drug name: ");
                    String drugName = scanner.nextLine();
                    
                    int qtySold;
                    while (true) {
                        System.out.print("Enter quantity sold: ");
                        try {
                            qtySold = Integer.parseInt(scanner.nextLine());
                            if (qtySold <= 0) {
                                System.out.println("Quantity sold must be positive. Please re-enter.");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid quantity format. Please enter a number. Please re-enter.");
                        }
                    }

                    double unitPrice;
                    while (true) {
                        System.out.print("Enter unit price: ");
                        try {
                            unitPrice = Double.parseDouble(scanner.nextLine());
                            if (unitPrice <= 0) {
                                System.out.println("Unit price must be positive. Please re-enter.");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid price format. Please enter a number. Please re-enter.");
                        }
                    }

                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();

                    Sale sale = new Sale(drugCode, drugName, qtySold, unitPrice, customerName);
                    transactionService.addSale(sale);
                    System.out.println("Sale logged.");
                    break;
                case "5":
                    LocalDate startPurchase = null;
                    LocalDate endPurchase = null;
                    while (true) {
                        System.out.print("Enter start date (yyyy-MM-dd): ");
                        String startDateStr = scanner.nextLine();
                        System.out.print("Enter end date (yyyy-MM-dd): ");
                        String endDateStr = scanner.nextLine();
                        try {
                            startPurchase = LocalDate.parse(startDateStr);
                            endPurchase = LocalDate.parse(endDateStr);
                            if (endPurchase.isBefore(startPurchase)) {
                                System.out.println("End date cannot be before start date. Please re-enter dates.");
                            } else {
                                break; // Valid dates, exit loop
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format. Please use yyyy-MM-dd. Please re-enter dates.");
                        }
                    }
                    transactionService.printPurchaseHistory(startPurchase, endPurchase);
                    break;
                case "6":
                    LocalDate startSales = null;
                    LocalDate endSales = null;
                    while (true) {
                        System.out.print("Enter start date (yyyy-MM-dd): ");
                        String startDateStr = scanner.nextLine();
                        System.out.print("Enter end date (yyyy-MM-dd): ");
                        String endDateStr = scanner.nextLine();
                        try {
                            startSales = LocalDate.parse(startDateStr);
                            endSales = LocalDate.parse(endDateStr);
                            if (endSales.isBefore(startSales)) {
                                System.out.println("End date cannot be before start date. Please re-enter dates.");
                            } else {
                                break; // Valid dates, exit loop
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format. Please use yyyy-MM-dd. Please re-enter dates.");
                        }
                    }
                    transactionService.printRecentSales(startSales, endSales);
                    break;
                case "0":
                    exit = true;
                    System.out.println("Exiting Pharmacy Inventory System...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}