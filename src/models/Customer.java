//src/models/customer.java
package models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Customer implements Serializable {
    private static final long serialVersionUID = 2L;

    final  String id;
    final String name;
    final String contactInfo;
    final String address;
    
    // ✅ Add transaction history
    final List<Transaction> transactions;

    public Customer(String id, String name, String contactInfo, String address) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
        this.transactions = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getContactInfo() { return contactInfo; }
    public String getAddress() { return address; }

    public List<Transaction> getTransactions() { return transactions; }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    @Override
    public String toString() {
        return String.format("Customer ID: %s | Name: %s | Contact: %s | Address: %s",
                id, name, contactInfo, address);
    }
}
