package ua.voloschenko.theme10;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, world!");

        ZoneId kyiv = ZoneId.of("Europe/Kiev");

        List<Event> events = List.of(
                new Event("Studying", LocalDateTime.of(2026, 6, 1, 9,  0), 60,  kyiv, "A"),
                new Event("Dota 2 match", LocalDateTime.of(2026, 3, 23, 22, 20), 130, kyiv, "B"),
                new Event("Vibecode",  LocalDateTime.of(2026, 1, 1, 0, 0), 0,  kyiv, "A"),
                new Event("Air attack",  LocalDateTime.of(2026, 3, 24, 0, 19), 60,  kyiv, "C")
        );

        for (Event event : events) {
            System.out.println(event.label());
        }

    }

}
