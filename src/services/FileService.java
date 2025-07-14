package services;

import java.io.*;
import java.util.List;
import models.Customer;
import models.Supplier;
import models.Transaction;

public class FileService {

    private final String SUPPLIER_FILE = "data/suppliers.dat";
    private final String CUSTOMER_FILE = "data/customers.dat";
    private final String TRANSACTION_FILE = "data/transactions.dat";

    // === Suppliers ===
    public void writeSuppliers(List<Supplier> suppliers) {
        writeObject(suppliers, SUPPLIER_FILE);
    }

    @SuppressWarnings("unchecked")
    public List<Supplier> readSuppliers() {
        return (List<Supplier>) readObject(SUPPLIER_FILE);
    }

    // === Customers ===
    public void writeCustomers(List<Customer> customers) {
        writeObject(customers, CUSTOMER_FILE);
    }

    @SuppressWarnings("unchecked")
    public List<Customer> readCustomers() {
        return (List<Customer>) readObject(CUSTOMER_FILE);
    }

    // === Transactions ===
    public void writeTransactions(List<Transaction> transactions) {
        writeObject(transactions, TRANSACTION_FILE);
    }

    @SuppressWarnings("unchecked")
    public List<Transaction> readTransactions() {
        return (List<Transaction>) readObject(TRANSACTION_FILE);
    }

    // === Generic writeObject ===
    private void writeObject(Object data, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(data);
        } catch (IOException e) {
            System.err.println("Error writing to " + filename + ": " + e.getMessage());
        }
    }

    // === Generic readObject ===
    private Object readObject(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return in.readObject();
        } catch (FileNotFoundException e) {
            return null; // File not yet created
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from " + filename + ": " + e.getMessage());
            return null;
        }
    }
}
