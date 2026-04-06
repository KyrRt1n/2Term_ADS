package ua.voloschenko.theme11;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task4 {

    public sealed interface Result permits Success, Failure {}

    public record Success(String data) implements Result {}
    public record Failure(String message) implements Result {}

    public static void main(String[] args) {


        List<Result> results = List.of(
                new Success("Logged in"),
                new Failure("Connection error"),
                new Success("File uploaded"),
                new Failure("Incorrect password"),
                new Failure("Error 504")
        );

        List<String> errors = results.stream()
                .filter(result -> result instanceof Failure)
                .map(result -> (Failure) result)
                .map(Failure::message)
                .toList();

        Map<Boolean, Long> counts = results.stream()
                .collect(Collectors.partitioningBy(
                        result -> result instanceof Success,
                        Collectors.counting()
                ));

        System.out.println("Errors: " + errors);
        System.out.println("Failure count: " + counts.get(false) + ", Success count: " + counts.get(true));

    }
}
