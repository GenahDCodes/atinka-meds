//services/PurchaseService.java
package services;

import models.Purchase;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class PurchaseService {
    private List<Purchase> purchases;

    public PurchaseService() {
        purchases = new ArrayList<>();
        loadPurchases();
    }

    public void recordPurchase(Purchase purchase) {
        purchases.add(purchase);
        savePurchases();
        System.out.println("Purchase recorded.");
    }

    public void printPurchaseHistory(LocalDate start, LocalDate end) {
        System.out.printf("\n%-10s %-15s %-5s %-8s %-10s %s\n",
            "Code", "Supplier", "Qty", "Cost", "Total", "Date");
        System.out.println("--------------------------------------------------------------");

        int totalQty = 0;
        double totalCost = 0;

        for (Purchase p : purchases) {
            if (!p.getPurchaseDate().isBefore(start) && !p.getPurchaseDate().isAfter(end)) {
                System.out.println(p);
                totalQty += p.getQuantityPurchased();
                totalCost += p.getTotalCost();
            }
        }

        System.out.println("--------------------------------------------------------------");
        System.out.printf("Total Purchased: %d\n", totalQty);
        System.out.printf("Total Cost: GHS %.2f\n", totalCost);
    }

    private void savePurchases() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("purchases.dat"))) {
            out.writeObject(purchases);
        } catch (IOException e) {
            System.out.println("Error saving purchases.");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadPurchases() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("purchases.dat"))) {
            purchases = (List<Purchase>) in.readObject();
        } catch (Exception e) {
            purchases = new ArrayList<>();
        }
    }
}