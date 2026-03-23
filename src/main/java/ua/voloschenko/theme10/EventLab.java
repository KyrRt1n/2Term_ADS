package ua.voloschenko.theme10;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class EventLab {

    public static List<Event> pick(List<Event> events, Predicate<Event> rule){
        List<Event> result = new ArrayList<Event>();
        for (Event event : events) {
            if(rule.test(event)){
                result.add(event);
            }
        }
        return result;
    }

    public static List<String> labels(List<Event> events, Function<Event, String> mapper){
        List<String> result = new ArrayList<String>();
        for (Event event : events) {
            result.add(mapper.apply(event));
        }
        return result;
    }

    public static void notifyAll(List<Event> events, Consumer<Event> action){
        for (Event event : events) {
            action.accept(event);
        }
    }

    public static Event create(Supplier<Event> factory) {
        return factory.get();
    }
}
