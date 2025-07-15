package main;
import java.time.LocalDate;
import java.util.Scanner;
import models.Customer;
import models.Transaction;
import services.CustomerService;
import services.FileService;

public class CustomerMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileService fileService = new FileService();
        CustomerService customerService = new CustomerService(fileService);

        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== Customer Management Menu =====");
            System.out.println("1. Add Customer");
            System.out.println("2. Remove Customer");
            System.out.println("3. Search Customer by ID");
            System.out.println("4. Search Customer by Name");
            System.out.println("5. Sort Customers by Name");
            System.out.println("6. Sort Customers by ID");
            System.out.println("7. Display All Customers");
            System.out.println("8. View Customer Transactions");
            System.out.println("9. Log New Transaction");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter contact info: ");
                    String contact = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();

                    Customer newCustomer = new Customer(id, name, contact, address);
                    customerService.addCustomer(newCustomer);
                    System.out.println("Customer added.");
                    break;

                case "2":
                    System.out.print("Enter customer ID to remove: ");
                    String removeId = scanner.nextLine();
                    customerService.removeCustomer(removeId);
                    System.out.println("Customer removed.");
                    break;

                case "3":
                    System.out.print("Enter customer ID to search: ");
                    String searchId = scanner.nextLine();
                    Customer foundById = customerService.searchCustomerById(searchId);
                    System.out.println(foundById != null ? foundById : "Customer not found.");
                    break;

                case "4":
                    System.out.print("Enter customer name to search: ");
                    String searchName = scanner.nextLine();
                    Customer foundByName = customerService.searchCustomerByName(searchName);
                    System.out.println(foundByName != null ? foundByName : "Customer not found.");
                    break;

                case "5":
                    customerService.sortCustomersByName();
                    System.out.println("Customers sorted by name.");
                    break;

                case "6":
                    customerService.sortCustomersById();
                    System.out.println("Customers sorted by ID.");
                    break;

                case "7":
                    customerService.printCustomers();
                    break;

                case "8":
                    System.out.print("Enter customer ID to view transactions: ");
                    String custId = scanner.nextLine();
                    var txns = customerService.getCustomerTransactions(custId);
                    if (txns.isEmpty()) {
                        System.out.println("No transactions found.");
                    } else {
                        txns.forEach(System.out::println);
                    }
                    break;

                case "9":
                    System.out.print("Enter customer ID: ");
                    String customerId = scanner.nextLine();
                    System.out.print("Enter supplier ID: ");
                    String supplierId = scanner.nextLine();
                    System.out.print("Enter drug code: ");
                    String drugCode = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int qty = Integer.parseInt(scanner.nextLine());

                    Transaction txn = new Transaction(
                            customerId,
                            supplierId,
                            drugCode,
                            qty,
                            LocalDate.now()
                    );

                    customerService.addTransaction(txn);
                    System.out.println("Transaction logged.");
                    break;

                case "0":
                    exit = true;
                    System.out.println("Exiting Customer Management...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}
