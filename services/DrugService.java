package services;

import models.Drug;
import models.Supplier;
import utils.ValidationUtils;
import utils.DateUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;

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
}