// src/services/SupplierService.java
package services;

import java.util.ArrayList;
import java.util.List;
import models.Supplier;
import utils.SupplierSearchUtils;
import utils.SupplierSortUtils;

public class SupplierService {
    private List<Supplier> suppliers;
    final FileService fileService;

    public SupplierService(FileService fileService) {
        this.fileService = fileService;
        this.suppliers = fileService.readSuppliers();
        if (suppliers == null) suppliers = new ArrayList<>();
    }

    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
        fileService.writeSuppliers(suppliers);
    }

    public void removeSupplier(String id) {
        suppliers.removeIf(s -> s.getId().equals(id));
        fileService.writeSuppliers(suppliers);
    }

    public List<Supplier> filterByLocation(String location) {
        return SupplierSearchUtils.filterByLocation(suppliers, location);
    }

    public List<Supplier> filterByDeliveryTime(int days) {
        return SupplierSearchUtils.filterByDeliveryTime(suppliers, days);
    }

    public Supplier searchSupplierById(String id) {
        sortSuppliersById(); // must sort before binary search
        return SupplierSearchUtils.binarySearchById(suppliers, id);
    }

    public Supplier getSupplierById(String id) {
        return searchSupplierById(id); // Reuse existing search method
    }

    public Supplier searchSupplierByName(String name) {
        return SupplierSearchUtils.linearSearchByName(suppliers, name);
    }

    public void sortSuppliersByName() {
        SupplierSortUtils.insertionSortByName(suppliers);
    }

    public void sortSuppliersByDeliveryTime() {
        SupplierSortUtils.insertionSortByDeliveryTime(suppliers);
    }

    public void sortSuppliersById() {
        suppliers.sort((s1, s2) -> s1.getId().compareToIgnoreCase(s2.getId()));
    }

    public void printSuppliers() {
        for (Supplier s : suppliers) {
            System.out.println(s);
        }
    }

    public List<Supplier> getAllSuppliers() {
        return suppliers;
    }
}