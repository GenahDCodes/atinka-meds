//src/main/models/Transaction.java
package models;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 4L;

    final String customerId;
    final  String supplierId;
    final String drugCode;
    final int quantity;
    final LocalDate date;

    public Transaction(String customerId, String supplierId, String drugCode, int quantity, LocalDate date) {
        this.customerId = customerId;
        this.supplierId = supplierId;
        this.drugCode = drugCode;
        this.quantity = quantity;
        this.date = date;
    }

    public String getCustomerId() { return customerId; }
    public String getSupplierId() { return supplierId; }
    public String getDrugCode() { return drugCode; }
    public int getQuantity() { return quantity; }
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
        return String.format("Transaction[customerId=%s, supplierId=%s, drugCode=%s, quantity=%d, date=%s]",
                customerId, supplierId, drugCode, quantity, date);
    }
}
