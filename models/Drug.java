//models/Drug.java
package models;

import java.util.List;
import java.util.Date;

/**
 * Represents a drug in the pharmacy inventory.
 * Contains details such as name, code, suppliers, expiration date, price, and stock level.
 */
public class Drug {
    private String name;
    private String code;
    private List<Supplier> suppliers;
    private Date expirationDate;
    private double price;
    private int stockLevel;

// Getters
    public String getName() { return name; }
    public String getCode() { return code; }
    public List<Supplier> getSuppliers() { return suppliers; }
    public Date getExpirationDate() { return expirationDate; }
    public double getPrice() { return price; }
    public int getStockLevel() { return stockLevel; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setCode(String code) { this.code = code; }
    public void setSuppliers(List<Supplier> suppliers) { this.suppliers = suppliers; }
    public void setExpirationDate(Date expirationDate) { this.expirationDate = expirationDate; }
    public void setPrice(double price) { this.price = price; }
    public void setStockLevel(int stockLevel) { this.stockLevel = stockLevel; }

    // Constructor
    public Drug() {
        this.suppliers = new ArrayList<>();
    }
} 