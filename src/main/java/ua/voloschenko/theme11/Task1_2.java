package ua.voloschenko.theme11;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task1_2 {
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

        System.out.println("======== Task 2 ========");
        System.out.println("======== Task 2 ========");
        System.out.println("======== Task 2 ========");


        List<String> uniqueTags = books.stream().
                flatMap(book -> book.tags().stream())
                .distinct().
                sorted().
                toList();

        System.out.println("Unique tags: " + uniqueTags);

        Map<String, Long> tagFrequency = books.stream()
                .flatMap(book -> book.tags()
                        .stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("Tag frequency: " + tagFrequency);
        int topN = 3;
        List<Map.Entry<String, Long>> topTags = tagFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(topN)
                .toList();

        System.out.println("Top-" + topN + " tags:");
        topTags.forEach(entry ->
                System.out.println(entry.getKey() + " : " + entry.getValue())
        );
    }
}