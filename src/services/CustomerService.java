package services;

import java.util.ArrayList;
import java.util.List;
import models.Customer;
import models.Transaction;
import utils.CustomerSearchUtils;
import utils.CustomerSortUtils;

public class CustomerService {
    private List<Customer> customers;
    private List<Transaction> transactions;
    final FileService fileService;

    public CustomerService(FileService fileService) {
        this.fileService = fileService;
        this.customers = fileService.readCustomers();
        this.transactions = fileService.readTransactions();
        if (customers == null) customers = new ArrayList<>();
        if (transactions == null) transactions = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        fileService.writeCustomers(customers);
    }

    public void removeCustomer(String id) {
        customers.removeIf(c -> c.getId().equals(id));
        fileService.writeCustomers(customers);
    }

    public void addTransaction(Transaction transaction) {
    transactions.add(transaction);

    // Also add to the customer's personal transaction history
    Customer customer = searchCustomerById(transaction.getCustomerId());
    if (customer != null) {
        customer.addTransaction(transaction);  // <- this updates the internal list
    }

    fileService.writeTransactions(transactions);
    fileService.writeCustomers(customers); // Save updated customer state
}


    public List<Transaction> getCustomerTransactions(String customerId) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getCustomerId().equals(customerId)) result.add(t);
        }
        return result;
    }

    public List<Transaction> getSupplierTransactions(String supplierId) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getSupplierId().equals(supplierId)) result.add(t);
        }
        return result;
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public void sortCustomersByName() {
        CustomerSortUtils.insertionSortByName(customers);
    }

    public void sortCustomersById() {
        CustomerSortUtils.insertionSortById(customers);
    }

    public Customer searchCustomerById(String id) {
        sortCustomersById(); // Required for binary search
        return CustomerSearchUtils.binarySearchById(customers, id);
    }

    public Customer searchCustomerByName(String name) {
        return CustomerSearchUtils.linearSearchByName(customers, name);
    }

    public void printCustomers() {
        for (Customer c : customers) {
            System.out.println(c);
        }
    }
}
