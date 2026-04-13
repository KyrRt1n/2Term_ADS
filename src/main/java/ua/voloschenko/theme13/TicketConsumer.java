package ua.voloschenko.theme13;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class TicketConsumer implements Runnable{

    private final BlockingQueue<SupportTicket> queue;
    private final ConcurrentHashMap<String, Integer> stats;

    public TicketConsumer(BlockingQueue<SupportTicket> queue, ConcurrentHashMap<String, Integer> stats) {
        this.queue = queue;
        this.stats = stats;
    }

    @Override
    public void run() {
        try {
            while (true) {
                SupportTicket ticket = queue.take();

                if (ticket == SupportTicket.POISON_PILL) {
                    System.out.println("[Consumer " + Thread.currentThread().getName() + "] Received Poison Pill. Shutting down.");
                    break;
                }

                stats.merge(ticket.topic(), 1, Integer::sum);
                System.out.println("Consumer " + Thread.currentThread().getName() + " Processed: " + ticket.topic());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
