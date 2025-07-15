package models;

import java.io.Serializable;
import java.time.LocalDate;

public class Purchase implements Serializable {
    private String drugCode;
    private String supplierName;
    private int quantityPurchased;
    private double unitCost;
    private double totalCost;
    private LocalDate purchaseDate;

    public Purchase(String drugCode, String supplierName, int quantityPurchased, double unitCost) {
        this.drugCode = drugCode;
        this.supplierName = supplierName;
        this.quantityPurchased = quantityPurchased;
        this.unitCost = unitCost;
        this.totalCost = quantityPurchased * unitCost;
        this.purchaseDate = LocalDate.now();
    }

    public LocalDate getPurchaseDate() { return purchaseDate; }
    public int getQuantityPurchased() { return quantityPurchased; }
    public double getTotalCost() { return totalCost; }

    @Override
    public String toString() {
        return String.format("%-10s %-15s %-5d %-8.2f %-10.2f %s",
            drugCode, supplierName, quantityPurchased, unitCost, totalCost, purchaseDate);
    }
}