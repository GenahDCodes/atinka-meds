// TransactionService.java
package services;

import models.Transaction;
import models.Sale;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class TransactionService {
    private LinkedList<Transaction> purchaseHistory;
    private Stack<Sale> salesLog;
    private final FileService fileService;
    private static final String PURCHASE_FILE = "data/purchases.dat";
    private static final String SALES_FILE = "data/sales.dat";

    public TransactionService(FileService fileService) {
        this.fileService = fileService;
        this.purchaseHistory = loadPurchases();
        this.salesLog = loadSales();
        if (purchaseHistory == null) purchaseHistory = new LinkedList<>();
        if (salesLog == null) salesLog = new Stack<>();
    }

    public void addTransaction(Transaction transaction) {
        purchaseHistory.add(transaction);
        fileService.writeObject(purchaseHistory, PURCHASE_FILE);
    }

    public void addSale(Sale sale) {
        salesLog.push(sale);
        fileService.writeObject(salesLog, SALES_FILE);
    }

    public List<Transaction> getPurchaseHistory() {
        return new LinkedList<>(purchaseHistory);
    }

    public List<Sale> getRecentSales() {
        Stack<Sale> result = new Stack<>();
        result.addAll(salesLog);
        return result;
    }

    public List<Transaction> getRecentPurchases(String drugCode, int limit) {
        return purchaseHistory.stream()
                .filter(t -> t.getDrugCode().equals(drugCode))
                .sorted((t1, t2) -> t2.getDate().compareTo(t1.getDate()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public void printPurchaseHistory(LocalDate start, LocalDate end) {
        System.out.printf("\n%-10s %-10s %-15s %-5s %s\n",
                "Drug Code", "Customer ID", "Supplier ID", "Qty", "Date");
        System.out.println("--------------------------------------------------");

        purchaseHistory.stream()
                .filter(t -> !t.getDate().isBefore(start) && !t.getDate().isAfter(end))
                .forEach(t -> System.out.println(
                        String.format("%-10s %-10s %-15s %-5d %s",
                                t.getDrugCode(), t.getCustomerId(), t.getSupplierId(), t.getQuantity(), t.getDate())
                ));

        System.out.println("--------------------------------------------------");
    }

    public void printRecentSales(LocalDate start, LocalDate end) {
        System.out.printf("\n%-10s %-15s %-5s %-8s %-10s %s\n",
                "Drug Code", "Drug Name", "Qty", "Price", "Total", "Date");
        System.out.println("--------------------------------------------------------------");

        salesLog.stream()
                .filter(s -> !s.getSaleDate().isBefore(start) && !s.getSaleDate().isAfter(end))
                .forEach(System.out::println);

        System.out.println("--------------------------------------------------------------");
    }

    @SuppressWarnings("unchecked")
    private LinkedList<Transaction> loadPurchases() {
        Object data = fileService.readObject(PURCHASE_FILE);
        return (data instanceof LinkedList) ? (LinkedList<Transaction>) data : null;
    }

    @SuppressWarnings("unchecked")
    private Stack<Sale> loadSales() {
        Object data = fileService.readObject(SALES_FILE);
        return (data instanceof Stack) ? (Stack<Sale>) data : null;
    }
}