package ua.voloschenko.theme11;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task1 {
    public record Book(String title, String author, int year, List<String> tags) {}
    public static void main(String[] args) {

    List<Book> books = List.of(
            new Book("Clean Code", "Robert Martin", 2008, List.of("clean", "practice", "java")),
            new Book("Effective Java", "Joshua Bloch", 2018, List.of("java", "best", "api")),
            new Book("Modern Java", "Nicolai Parlog", 2020, List.of("java", "streams", "records")),
            new Book("Java Concurrency", "Brian Goetz", 2006, List.of("concurrency", "java"))
    );

        System.out.println("======== Task 1 ========");
        System.out.println("======== Task 1 ========");
        System.out.println("======== Task 1 ========");

        List<String> result = books.stream()
                .filter(book -> book.year() > 2015)
                .map(book -> book.title().toUpperCase())
                .sorted()
                .limit(3)
                .toList();

        System.out.println(result);

    }
}