package ua.voloschenko.theme11;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Task5 {

    public record Book(String title, String author, int year) {}
    public record Sale(String customerEmail, String product, int cents) {}

    public static void main(String[] args) {

        List<Book> books = List.of(
                new Book("Clean Code", "Robert Martin", 2008),
                new Book("Effective Java", "Joshua Bloch", 2018),
                new Book("Modern Java", "Nicolai Parlog", 2020),
                new Book("Java Concurrency", "Brian Goetz", 2006)
        );

        List<Sale> sales = List.of(
                new Sale("a@ex.com", "Tea", 120),
                new Sale("b@ex.com", "Cake", 200),
                new Sale("a@ex.com", "Tea", 120),
                new Sale("c@ex.com", "Coffee", 150),
                new Sale("b@ex.com", "Cake", 200)
        );

        Map<Boolean, List<Book>> recentsVsOld = books.stream()
                .collect(Collectors.partitioningBy(book -> book.year() > 2015));


        System.out.println("Books published after 2015: " + recentsVsOld.get(true));
        System.out.println("Books published before 2015: " + recentsVsOld.get(false));

        Map<String, Integer> revenueByProduct = sales.stream()
                .collect(Collectors.toMap(Sale::product, Sale::cents, Integer::sum, TreeMap::new));

        System.out.println(revenueByProduct);

    }

}
