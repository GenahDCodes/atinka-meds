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
    // Getters, setters, and constructors to be implemented
} 