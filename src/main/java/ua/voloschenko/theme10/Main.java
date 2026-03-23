package ua.voloschenko.theme10;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, world!");

        ZoneId kyiv = ZoneId.of("Europe/Kiev");

        List<Event> events = List.of(
                new Event("Java Basics",    LocalDateTime.of(2026, 4, 1, 9,  0), 60,  kyiv, "A"),
                new Event("Spring Boot",    LocalDateTime.of(2026, 4, 1, 10, 30), 90, kyiv, "B"),
                new Event("Docker 101",     LocalDateTime.of(2026, 4, 1, 13, 0), 45,  kyiv, "A"),
                new Event("Clean Code",     LocalDateTime.of(2026, 4, 1, 14, 0), 60,  kyiv, "C")
        );

        for (Event event : events) {
            System.out.println(event.label());
        }

    }

}
