package ua.voloschenko.theme10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LambdaRefactorLab {

    public static List<Event> sortAnonymous(List<Event> events) {
        List<Event> result = new ArrayList<>(events);
        result.sort(new Comparator<Event>() {
            @Override
            public int compare(Event a, Event b) {
                return a.getStartTime().compareTo(b.getStartTime());
            }
        });
        return result;
    }

    public static List<Event> sortLambda(List<Event> events) {
        List<Event> result = new ArrayList<>(events);
        result.sort((a, b) -> a.getStartTime().compareTo(b.getStartTime()));
        return result;
    }

    public static List<Event> sortMethodRef(List<Event> events) {
        List<Event> result = new ArrayList<>(events);
        result.sort(Comparator.comparing(Event::getStartTime));
        return result;
    }
}