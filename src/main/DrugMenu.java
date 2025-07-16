package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import models.Drug;
import services.DrugService;
import services.SupplierService;
import services.FileService;
import utils.SearchUtils;
import utils.SortUtils;

public class DrugMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileService fileService = new FileService();
        SupplierService supplierService = new SupplierService(fileService);
        DrugService drugService = new DrugService(supplierService);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== Drug Management Menu =====");
            System.out.println("1. Add Drug");
            System.out.println("2. Remove Drug");
            System.out.println("3. Update Drug");
            System.out.println("4. List All Drugs");
            System.out.println("5. Search Drug by Code");
            System.out.println("6. Search Drug by Name");
            System.out.println("7. Search Drug by Supplier");
            System.out.println("8. Sort Drugs by Name");
            System.out.println("9. Sort Drugs by Price");
            System.out.println("10. Check Low Stock Alerts");
            System.out.println("11. Suggest Auto-Reorders");
            System.out.println("12. Generate Inventory Report");
            System.out.println("13. Link Drug to Supplier");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter drug name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter drug code (D-XXXX): ");
                    String code = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price;
                    try {
                        price = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price format. Drug not added.");
                        break;
                    }
                    System.out.print("Enter stock level: ");
                    int stockLevel;
                    try {
                        stockLevel = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid stock level format. Drug not added.");
                        break;
                    }
                    System.out.print("Enter expiration date (yyyy-MM-dd, or leave blank): ");
                    String dateStr = scanner.nextLine();
                    Date expDate = null;
                    if (!dateStr.isEmpty()) {
                        try {
                            expDate = sdf.parse(dateStr);
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. Please use yyyy-MM-dd. Drug not added.");
                            break;
                        }
                    }

                    Drug newDrug = new Drug(name, code);
                    newDrug.setPrice(price);
                    newDrug.setStockLevel(stockLevel);
                    newDrug.setExpirationDate(expDate);
                    if (drugService.addDrug(newDrug)) {
                        System.out.println("Drug added.");
                    }
                    break;

                case "2":
                    System.out.print("Enter drug code to remove: ");
                    String removeCode = scanner.nextLine();
                    if (drugService.deleteDrug(removeCode)) {
                        System.out.println("Drug removed.");
                    } else {
                        System.out.println("Drug not found.");
                    }
                    break;

                case "3":
                    System.out.print("Enter drug code to update: ");
                    String updateCode = scanner.nextLine();
                    Drug existingDrug = drugService.searchDrug(updateCode);
                    if (existingDrug == null) {
                        System.out.println("Drug not found.");
                        break;
                    }
                    System.out.print("Enter new name (or press Enter to keep '" + existingDrug.getName() + "'): ");
                    String newName = scanner.nextLine();
                    if (!newName.isEmpty()) existingDrug.setName(newName);

                    System.out.print("Enter new price (or press Enter to keep " + existingDrug.getPrice() + "): ");
                    String priceStr = scanner.nextLine();
                    if (!priceStr.isEmpty()) {
                        try {
                            existingDrug.setPrice(Double.parseDouble(priceStr));
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid price format. Price not updated.");
                        }
                    }

                    System.out.print("Enter new stock level (or press Enter to keep " + existingDrug.getStockLevel() + "): ");
                    String stockStr = scanner.nextLine();
                    if (!stockStr.isEmpty()) {
                        try {
                            existingDrug.setStockLevel(Integer.parseInt(stockStr));
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid stock level format. Stock level not updated.");
                        }
                    }

                    System.out.print("Enter new expiration date (yyyy-MM-dd, or press Enter to keep): ");
                    String newDateStr = scanner.nextLine();
                    if (!newDateStr.isEmpty()) {
                        try {
                            existingDrug.setExpirationDate(sdf.parse(newDateStr));
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. Please use yyyy-MM-dd. Expiration date not updated.");
                        }
                    }

                    if (drugService.updateDrug(existingDrug)) {
                        System.out.println("Drug updated.");
                    }
                    break;

                case "4":
                    drugService.listDrugs().forEach(d -> System.out.println(
                            String.format("Name: %s | Code: %s | Price: %.2f | Stock: %d | Expiry: %s",
                                    d.getName(), d.getCode(), d.getPrice(), d.getStockLevel(),
                                    d.getExpirationDate() != null ? sdf.format(d.getExpirationDate()) : "N/A")));
                    break;

                case "5":
                    System.out.print("Enter drug code to search: ");
                    String searchCode = scanner.nextLine();
                    Drug drugByCode = drugService.searchDrug(searchCode);
                    System.out.println(drugByCode != null ? drugByCode : "Drug not found.");
                    break;

                case "6":
                    System.out.print("Enter drug name to search: ");
                    String searchName = scanner.nextLine();
                    Drug drugByName = SearchUtils.binarySearchByName(drugService.listDrugs(), searchName);
                    System.out.println(drugByName != null ? drugByName : "Drug not found.");
                    break;

                case "7":
                    System.out.print("Enter supplier ID to search: ");
                    String supplierId = scanner.nextLine();
                    drugService.getDrugsBySupplier(supplierId).forEach(d -> System.out.println(
                            String.format("Name: %s | Code: %s", d.getName(), d.getCode())));
                    break;

                case "8":
                    SortUtils.sortByName(drugService.listDrugs());
                    System.out.println("Drugs sorted by name.");
                    break;

                case "9":
                    SortUtils.sortByPrice(drugService.listDrugs());
                    System.out.println("Drugs sorted by price.");
                    break;

                case "10":
                    System.out.print("Enter stock threshold: ");
                    int threshold;
                    try {
                        threshold = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid threshold format. Please enter a number.");
                        break;
                    }
                    drugService.checkLowStockAlerts(threshold);
                    break;

                case "11":
                    System.out.print("Enter stock threshold for reordering: ");
                    int reorderThreshold;
                    try {
                        reorderThreshold = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid threshold format. Please enter a number.");
                        break;
                    }
                    drugService.suggestAutoReorders(reorderThreshold);
                    break;

                case "12":
                    System.out.print("Enter expiry days threshold: ");
                    int expiryDays;
                    try {
                        expiryDays = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid threshold format. Please enter a number.");
                        break;
                    }
                    drugService.generateInventoryReport(expiryDays);
                    break;

                case "13":
                    System.out.print("Enter drug code: ");
                    String linkDrugCode = scanner.nextLine();
                    System.out.print("Enter supplier ID: ");
                    String linkSupplierId = scanner.nextLine();
                    if (drugService.linkDrugToSupplier(linkDrugCode, linkSupplierId)) {
                        System.out.println("Drug linked to supplier.");
                    }
                    break;

                case "0":
                    exit = true;
                    System.out.println("Exiting Drug Management...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}