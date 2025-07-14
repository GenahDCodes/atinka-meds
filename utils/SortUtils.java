//utils/SortUtils.java

package utils;

import java.util.ArrayList;
import java.util.List;

import models.Drug;

/**
 * Provides sorting algorithms for the application.
 * Includes methods to sort drugs by name and price using QuickSort.
 */
public class SortUtils {
    // Sort by name (A-Z)
    public static void sortByName(List<Drug> drugs) {
        quickSortByName(drugs, 0, drugs.size() - 1);
    }

    // Sort by price (low to high)
    public static void sortByPrice(List<Drug> drugs) {
        quickSortByPrice(drugs, 0, drugs.size() - 1);
    }

    // QuickSort by Name
    private static void quickSortByName(List<Drug> list, int low, int high) {
        if (low < high) {
            int pi = partitionByName(list, low, high);
            quickSortByName(list, low, pi - 1);
            quickSortByName(list, pi + 1, high);
        }
    }

    private static int partitionByName(List<Drug> list, int low, int high) {
        Drug pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).getName().compareToIgnoreCase(pivot.getName()) <= 0) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    // QuickSort by Price
    private static void quickSortByPrice(List<Drug> list, int low, int high) {
        if (low < high) {
            int pi = partitionByPrice(list, low, high);
            quickSortByPrice(list, low, pi - 1);
            quickSortByPrice(list, pi + 1, high);
        }
    }

    private static int partitionByPrice(List<Drug> list, int low, int high) {
        Drug pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).getPrice() <= pivot.getPrice()) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    // Swap helper
    private static void swap(List<Drug> list, int i, int j) {
        Drug temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public static void mergeSortByName(List<Drug> drugs) {
        if (drugs == null || drugs.size() <= 1)
            return;
        List<Drug> sorted = mergeSortByNameRecursive(drugs);
        drugs.clear();
        drugs.addAll(sorted);
    }

    private static List<Drug> mergeSortByNameRecursive(List<Drug> drugs) {
        if (drugs.size() <= 1)
            return drugs;

        int mid = drugs.size() / 2;
        List<Drug> left = mergeSortByNameRecursive(drugs.subList(0, mid));
        List<Drug> right = mergeSortByNameRecursive(drugs.subList(mid, drugs.size()));

        return mergeByName(left, right);
    }

    private static List<Drug> mergeByName(List<Drug> left, List<Drug> right) {
        List<Drug> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getName().compareToIgnoreCase(right.get(j).getName()) <= 0) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }

        while (i < left.size())
            merged.add(left.get(i++));
        while (j < right.size())
            merged.add(right.get(j++));

        return merged;
    }

    public static void mergeSortByPrice(List<Drug> drugs) {
        if (drugs == null || drugs.size() <= 1)
            return;
        List<Drug> sorted = mergeSortByPriceRecursive(drugs);
        drugs.clear();
        drugs.addAll(sorted);
    }

    private static List<Drug> mergeSortByPriceRecursive(List<Drug> drugs) {
        if (drugs.size() <= 1)
            return drugs;

        int mid = drugs.size() / 2;
        List<Drug> left = mergeSortByPriceRecursive(drugs.subList(0, mid));
        List<Drug> right = mergeSortByPriceRecursive(drugs.subList(mid, drugs.size()));

        return mergeByPrice(left, right);
    }

    private static List<Drug> mergeByPrice(List<Drug> left, List<Drug> right) {
        List<Drug> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getPrice() <= right.get(j).getPrice()) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }

        while (i < left.size())
            merged.add(left.get(i++));
        while (j < right.size())
            merged.add(right.get(j++));

        return merged;
    }

    public static void quickSortByCode(List<Drug> drugs) {
        quickSortByCode(drugs, 0, drugs.size() - 1);
    }

    private static void quickSortByCode(List<Drug> list, int low, int high) {
        if (low < high) {
            int pi = partitionByCode(list, low, high);
            quickSortByCode(list, low, pi - 1);
            quickSortByCode(list, pi + 1, high);
        }
    }

    private static int partitionByCode(List<Drug> list, int low, int high) {
        Drug pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getCode().compareToIgnoreCase(pivot.getCode()) <= 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }
}

// Example: Merge sort implementation
// public static void mergeSort(int[] arr) {
// // To be implemented
// }
// // Example: Insertion sort implementation
// public static void insertionSort(int[] arr) {
// // To be implemented
// }