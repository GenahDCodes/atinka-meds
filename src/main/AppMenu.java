package main;

import java.time.LocalDate;
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
            System.out.println("0. Exit");
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
                    System.out.print("Enter quantity sold: ");
                    int qtySold = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter unit price: ");
                    double unitPrice = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();

                    Sale sale = new Sale(drugCode, drugName, qtySold, unitPrice, customerName);
                    transactionService.addSale(sale);
                    System.out.println("Sale logged.");
                    break;
                case "5":
                    System.out.print("Enter start date (yyyy-MM-dd): ");
                    String startDateStr = scanner.nextLine();
                    System.out.print("Enter end date (yyyy-MM-dd): ");
                    String endDateStr = scanner.nextLine();
                    LocalDate start = LocalDate.parse(startDateStr);
                    LocalDate end = LocalDate.parse(endDateStr);
                    transactionService.printPurchaseHistory(start, end);
                    break;
                case "6":
                    System.out.print("Enter start date (yyyy-MM-dd): ");
                    startDateStr = scanner.nextLine();
                    System.out.print("Enter end date (yyyy-MM-dd): ");
                    endDateStr = scanner.nextLine();
                    start = LocalDate.parse(startDateStr);
                    end = LocalDate.parse(endDateStr);
                    transactionService.printRecentSales(start, end);
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