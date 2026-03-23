package ua.voloschenko.theme10;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Event {
    private String title, track;
    private LocalDateTime start;
    private int durationMinutes;
    private ZoneId zone;

    public Event(String title, LocalDateTime start, int durationMinutes, ZoneId zone, String track) {
        this.title = title;
        this.start = start;
        this.zone = zone;
        this.track = track;
        this.durationMinutes = durationMinutes;
    }

    public LocalDateTime end() {
        return start.plusMinutes(durationMinutes);
    }

    public String label() {
        return title + ", " + track;
    }
}
