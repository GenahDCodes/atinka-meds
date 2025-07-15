package main;
import java.util.Scanner;

public class AppMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Pharmacy Inventory System ===");
            System.out.println("1. Customer Management");
            System.out.println("2. Supplier Management");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    CustomerMenu.main(null); // Navigate to CustomerMenu
                    break;
                case "2":
                    SupplierMenu.main(null); // Navigate to SupplierMenu
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
