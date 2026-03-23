package ua.voloschenko.theme10;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, world!");

        ZoneId kyiv = ZoneId.of("Europe/Kiev");

        List<Event> events = List.of(
                new Event("Tiredness because of laziness", LocalDateTime.of(2026, 6, 1, 9,  0), 60,  kyiv, "A"),
                new Event("life flashing before my eyes",  LocalDateTime.of(2026, 3, 23, 22, 20), 130, kyiv, "B"),
                new Event("AriaMath",                      LocalDateTime.of(2026, 3, 23, 23, 0), 15,  kyiv, "A"),
                new Event("Decadence",                     LocalDateTime.of(2026, 3, 21, 0, 19), 60,  kyiv, "C")
        );

        System.out.println("======== Task 1 ========");
        System.out.println("======== Task 1 ========");
        System.out.println("======== Task 1 ========");

        for (Event event : events) {
            System.out.println(event.label());
        }

        Predicate<Event> isMorning = event -> event.getStartTime().getHour() < 12;
        Predicate<Event> isTrackA = event -> event.getTrack().equals("A");
        Predicate<Event> morningTrackA = isMorning.and(isTrackA);
        Predicate<Event> isAfternoon = isMorning.negate();

        System.out.println("======== Task 2 ========");
        System.out.println("======== Task 2 ========");
        System.out.println("======== Task 2 ========");

        List<Event> morning = EventLab.pick(events, morningTrackA);
        System.out.println("Morning A tracks:");
        morning.forEach(e -> System.out.println(e.label()));

        List<String> allLabels = EventLab.labels(events, Event::label);
        System.out.println("All labels: " + allLabels);

        EventLab.notifyAll(events, e -> System.out.println("Notif: " + e.getTitle()));

        findConflits(events).forEach(System.out::println);

        System.out.println("======== Task 3 ========");
        System.out.println("======== Task 3 ========");
        System.out.println("======== Task 3 ========");

        List<Event> byAnon = LambdaRefactorLab.sortAnonymous(events);
        List<Event> byLambda = LambdaRefactorLab.sortLambda(events);
        List<Event> byRef = LambdaRefactorLab.sortMethodRef(events);

        System.out.println("Anonymous:");
        byAnon.forEach(e -> System.out.println(e.label() + " " + e.getStartTime()));

        System.out.println("Lambda:");
        byLambda.forEach(e -> System.out.println(e.label() + " " + e.getStartTime()));

        System.out.println("MethodRef: ");
        byRef.forEach(e -> System.out.println(e.label() + " " + e.getStartTime()));

        System.out.println("======== Task 4 ========");
        System.out.println("======== Task 4 ========");
        System.out.println("======== Task 4 ========");

        Event e1 = events.get(1);
        Event e2 = events.get(2);

        System.out.println("Instant e1: " + DateTimeLab.toInstant(e1));
        System.out.println("Instant e2: " + DateTimeLab.toInstant(e2));
        System.out.println("Between e1 and e2: " + DateTimeLab.minutesBetween(e1, e2) + " хв");
        System.out.println("e1 in London: " + DateTimeLab.startInZone(e1, "Europe/London"));
        System.out.println("e1 in Tokyo: "   + DateTimeLab.startInZone(e1, "Asia/Tokyo"));

    }

    public static List<String> findConflits(List<Event> events) {
        List<String> conflicts = new ArrayList<>();
        for (Event event : events) {
            for (Event other : events) {
                Instant eventStart = event.getStartTime().atZone(event.getZone()).toInstant();
                Instant otherStart = other.getStartTime().atZone(other.getZone()).toInstant();
                Instant eventEnd = event.end().atZone(event.getZone()).toInstant();
                Instant otherEnd = other.end().atZone(other.getZone()).toInstant();

                if (eventStart.isBefore(otherEnd) && otherStart.isBefore(eventEnd) && !event.equals(other)) {
                    conflicts.add(event.label() + " conflicts with " + other.label());
                }
            }
        }
        return conflicts;
    }

}
