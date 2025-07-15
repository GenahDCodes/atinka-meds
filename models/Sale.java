package models;

import java.io.Serializable;
import java.time.LocalDate;

public class Sale implements Serializable {
    private String drugCode;
    private String drugName;
    private int quantitySold;
    private double unitPrice;
    private double totalPrice;
    private LocalDate saleDate;

    public Sale(String drugCode, String drugName, int quantitySold, double unitPrice, String customerName) {
        this.drugCode = drugCode;
        this.drugName = drugName;
        this.quantitySold = quantitySold;
        this.unitPrice = unitPrice;
        this.totalPrice = quantitySold * unitPrice;
        this.saleDate = LocalDate.now();
    }

    public int getQuantitySold() { return quantitySold; }
    public double getTotalPrice() { return totalPrice; }
    public LocalDate getSaleDate() { return saleDate; }

    @Override
    public String toString() {
        return String.format("%-10s %-15s %-5d %-8.2f %-10.2f %s",
            drugCode, drugName, quantitySold, unitPrice, totalPrice, saleDate);
    }
}
