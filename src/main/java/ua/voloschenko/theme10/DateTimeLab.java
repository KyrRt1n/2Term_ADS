package ua.voloschenko.theme10;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeLab {

    public static Instant toInstant(Event e) {
        return e.getStartTime().atZone(e.getZone()).toInstant();
    }

    public static long minutesBetween(Event a, Event b) {
        Instant instA = toInstant(a);
        Instant instB = toInstant(b);
        return Math.abs(Duration.between(instA, instB).toMinutes());
    }

    public static String startInZone(Event e, String zone) {
        ZoneId targetZone = ZoneId.of(zone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return e.getStartTime()
                .atZone(e.getZone())
                .withZoneSameInstant(targetZone)
                .format(formatter);
    }
}
