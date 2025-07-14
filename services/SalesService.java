package services;

import models.Sale;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalesService {
    private List<Sale> sales;

    public SalesService() {
        sales = new ArrayList<>();
        loadSales();
    }

    public void recordSale(Sale sale) {
        sales.add(sale);
        saveSales();
        System.out.println("Sale recorded successfully.");
    }

    public void printReport(LocalDate startDate, LocalDate endDate) {
        System.out.printf("\n%-10s %-15s %-5s %-8s %-10s %s\n",
            "Code", "Drug Name", "Qty", "Price", "Total", "Date");
        System.out.println("----------------------------------------------------------------------------");

        int totalQty = 0;
        double totalRevenue = 0.0;

        for (Sale s : sales) {
            if (!s.getSaleDate().isBefore(startDate) && !s.getSaleDate().isAfter(endDate)) {
                System.out.println(s);
                totalQty += s.getQuantitySold();
                totalRevenue += s.getTotalPrice();
            }
        }

        System.out.println("----------------------------------------------------------------------------");
        System.out.printf("Total Items Sold: %d\n", totalQty);
        System.out.printf("Total Revenue: GHS %.2f\n", totalRevenue);
    }

    public void printTodayReport() {
        LocalDate today = LocalDate.now();
        printReport(today, today);
    }

    public void printThisWeekReport() {
        LocalDate today = LocalDate.now();
        LocalDate monday = today.minusDays(today.getDayOfWeek().getValue() - 1);
        printReport(monday, today);
    }

    public void printThisMonthReport() {
        LocalDate today = LocalDate.now();
        LocalDate firstDay = today.withDayOfMonth(1);
        printReport(firstDay, today);
    }

    private void saveSales() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("sales.dat"))) {
            out.writeObject(sales);
        } catch (IOException e) {
            System.out.println("Error saving sales: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadSales() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("sales.dat"))) {
            sales = (List<Sale>) in.readObject();
        } catch (Exception e) {
            sales = new ArrayList<>();
        }
    }
}
