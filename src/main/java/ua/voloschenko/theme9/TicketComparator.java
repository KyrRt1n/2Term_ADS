package ua.voloschenko.theme9;

import java.util.Comparator;

public class TicketComparator {

    public static final Comparator<Ticket> anonim_prior = new Comparator<Ticket>() {
        @Override
        public int compare(Ticket t1, Ticket t2) {
            return Integer.compare(t1.getPriority(), t2.getPriority());
        }
    };

    public static final Comparator<Ticket> lambda =
            (t1, t2) -> Integer.compare(t1.getPriority(), t2.getPriority());

    public static final Comparator<Ticket> byPriority = Comparator.comparingInt(Ticket::getPriority);
    public static final Comparator<Ticket> byDate = Comparator.comparing(Ticket::getCreatedAt);


    public static class StaticNestedComparator implements Comparator<Ticket> {
        @Override
        public int compare(Ticket t1, Ticket t2) {
            return Integer.compare(t1.getPriority(), t2.getPriority());
        }
    }

}
