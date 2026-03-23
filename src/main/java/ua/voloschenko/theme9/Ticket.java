package ua.voloschenko.theme9;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ticket {

    public static void main(String[] args) {
        List<Ticket> tickets = new ArrayList<>();
        Ticket t1 = new Ticket(1, LocalDate.now());
        Ticket t2 = new Ticket(2, LocalDate.of(2026, 3, 15));
        Ticket t3 = new Ticket(3, LocalDate.of(2026, 3, 11));
        tickets.add(t1);
        tickets.add(t2);
        tickets.add(t3);
        Comparator<Ticket> comparator;

        System.out.println("Before");
        tickets.forEach(System.out::println);
//        tickets.sort(TicketComparator.anonim_prior);
//        tickets.sort(TicketComparator.byPriority);
        tickets.sort(TicketComparator.lambda);
        System.out.println("After");
        tickets.forEach(System.out::println);
        tickets.sort(TicketComparator.byDate);
//        TicketComparator.StaticNestedComparator SNC = new TicketComparator.StaticNestedComparator();
//        tickets.sort(SNC);

        System.out.println("After");
        tickets.forEach(System.out::println);
        
    };

    private int priority;
    private LocalDate createdAt;

    public Ticket(int priority, LocalDate createdAt) {
        this.priority = priority;
        this.createdAt = createdAt;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "priority=" + priority +
                ", createdAt=" + createdAt +
                '}';
    }
}
