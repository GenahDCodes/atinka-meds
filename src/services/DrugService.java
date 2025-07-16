//services/DrugService.java
package services;

import models.Drug;
import models.Supplier;
import utils.ValidationUtils;
import utils.DateUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.text.SimpleDateFormat;


public class DrugService {
    private List<Drug> drugs = new ArrayList<>();
    private SupplierService supplierService;

    public DrugService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // Add drug with validation
    public boolean addDrug(Drug drug) {
        if (!ValidationUtils.isValidDrugCode(drug.getCode())) {
            System.err.println("Invalid drug code format");
            return false;
        }
        
        if (drug.getPrice() <= 0) {
            System.err.println("Price must be positive");
            return false;
        }
        
        if (DateUtils.isExpired(drug.getExpirationDate())) {
            System.err.println("Cannot add expired drug");
            return false;
        }
        
        if (drug.getStockLevel() < 0) {
            System.err.println("Stock level cannot be negative");
            return false;
        }
        
        drugs.add(drug);
        return true;
    }

    // Update existing drug
    public boolean updateDrug(Drug updatedDrug) {
        for (int i = 0; i < drugs.size(); i++) {
            if (drugs.get(i).getCode().equals(updatedDrug.getCode())) {
                drugs.set(i, updatedDrug);
                return true;
            }
        }
        System.err.println("Drug not found with code: " + updatedDrug.getCode());
        return false;
    }

    // Delete drug by code
    public boolean deleteDrug(String code) {
        return drugs.removeIf(drug -> drug.getCode().equals(code));
    }

    // List all drugs
    public List<Drug> listDrugs() {
        return new ArrayList<>(drugs);
    }

    // Search drug by code
    public Drug searchDrug(String code) {
        return drugs.stream()
                   .filter(d -> d.getCode().equals(code))
                   .findFirst()
                   .orElse(null);
    }

    // Link drug to supplier
    public boolean linkDrugToSupplier(String drugCode, String supplierId) {
        Drug drug = searchDrug(drugCode);
        if (drug == null) {
            System.err.println("Drug not found");
            return false;
        }
        
        // This assumes SupplierService has a method to find supplier by ID
        Supplier supplier = supplierService.getSupplierById(supplierId);
        if (supplier == null) {
            System.err.println("Supplier not found");
            return false;
        }
        
        drug.getSuppliers().add(supplier);
        return true;
    }

    // Get drugs by supplier
    public List<Drug> getDrugsBySupplier(String supplierId) {
        return drugs.stream()
                   .filter(d -> d.getSuppliers().stream()
                       .anyMatch(s -> s.getId().equals(supplierId)))
                   .collect(Collectors.toList());
    }

    //Alerts for drugs with low stock levels using a min-heap approach
    public void checkLowStockAlerts(int threshold){
        PriorityQueue<Drug> stockHeap = new PriorityQueue<>(
            (d1, d2) -> Integer.compare(d1.getStockLevel(), d2.getStockLevel())
        );

        //Add all available drugs to the heap
        for(Drug drug:drugs){
            stockHeap.offer(drug);
        }

        System.out.println("\n----------Stock Alert Report-----------");
        boolean hasLowStock = false;

        //Process the heap to find the drugs less than or equal to the threshold
        while (!stockHeap.isEmpty()){
            Drug nextDrug = stockHeap.peek();

            if(nextDrug.getStockLevel()>threshold){
                break;
            }

            nextDrug = stockHeap.poll();
            hasLowStock = true;

            System.out.println("Low Stock: " + nextDrug.getName() + 
                               " [Code: " + nextDrug.getCode() + "]" +
                               " Stock Left: " + nextDrug.getStockLevel());
        }

        if(!hasLowStock){
            System.out.println("No low-stock drugs. Inventory is sufficient");
        }
    }
    
    //Suggest reorders for low-stock drugs based on fastest delivery time
    public void suggestAutoReorders(int threshold){
        System.out.println("\n----------Auto-Reorder Suggestions----------");

        for (Drug drug:drugs){
            if(drug.getStockLevel() <= threshold){
                List<Supplier> suppliers = drug.getSuppliers();

                if(suppliers == null || suppliers.isEmpty()){
                    System.out.println("No suppliers linked for: " + drug.getName() +
                    " [" + drug.getCode() + "]");
                    continue;
                }

                //Find supplier with shortest delivery time
                Supplier fastest = suppliers.get(0);
                for (Supplier s:suppliers){
                    if(s.getDeliveryTime() < fastest.getDeliveryTime()){
                        fastest = s;
                    }
                }

                System.out.println("Reorder " + drug.getName() + 
                                  " [Code: " + drug.getCode() + "]" +
                                  " from " + fastest.getName() + 
                                  " (Delivery in " + fastest.getDeliveryTime() + " days)");

            }
        }
    }

    // Generate inventory report(stock levels and expiration status)
    public void generateInventoryReport(int expiryDaysThreshold) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("\n----------Inventory Report----------");
        for (Drug drug : drugs) {
            String name = drug.getName();
            String code = drug.getCode();
            int stock = drug.getStockLevel();
            String expiry = (drug.getExpirationDate() != null)
                    ? sdf.format(drug.getExpirationDate()) : "N/A";

            boolean expiringSoon = DateUtils.isExpiringSoon(drug.getExpirationDate(), expiryDaysThreshold);

            System.out.print(name + " [Code: " + code + "]"
                    + " | Stock: " + stock
                    + " | Expiration Date: " + expiry);

            if (expiringSoon) {
                System.out.print(" - Expiring Soon");
            }

            System.out.println();
        }
        System.out.println("-------------------------------------");
    }
}






 