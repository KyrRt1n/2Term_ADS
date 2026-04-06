package ua.voloschenko.theme11;

import java.util.*;
import java.util.stream.Collectors;

public class Task3 {
    public record Sale(String customerEmail, String product, int cents) {}

    public static void main(String[] args) {

        List<Sale> sales = List.of(
                new Sale("a@ex.com", "Tea", 120),
                new Sale("b@ex.com", "Cake", 200),
                new Sale("a@ex.com", "Tea", 120),
                new Sale("c@ex.com", "Coffee", 150),
                new Sale("b@ex.com", "Cake", 200)
        );


        Map<String, Integer> salesByProduct = sales.stream()
                .sorted(Comparator.comparing(Sale::product))
                .collect(Collectors.toMap(Sale::product, Sale::cents, Integer::sum));

        System.out.println("Sales by product:\n" + salesByProduct);

        Map<String, Long> transactionsByCustomer = sales.stream()
                .collect(Collectors.groupingBy(Sale::customerEmail, Collectors.counting()));

        System.out.println("Transactions by customer:\n" + transactionsByCustomer);

    }



}
