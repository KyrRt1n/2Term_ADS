package ua.voloschenko.theme13;

import java.util.concurrent.BlockingQueue;

public class TicketProducer implements Runnable{

    private final BlockingQueue<SupportTicket> queue;
    private final int ticketsToProduce;
    private final int consumerCount;

    public TicketProducer(BlockingQueue<SupportTicket> queue, int ticketsToProduce, int consumerCount) {
        this.queue = queue;
        this.ticketsToProduce = ticketsToProduce;
        this.consumerCount = consumerCount;
    }

    @Override
    public void run() {
        try {
            String[] topics = {"Billing", "Tech", "Account", "General"};
            for (int i = 1; i <= ticketsToProduce; i++) {
                SupportTicket ticket = new SupportTicket(i, "Customer " + i, topics[i % topics.length]);
                queue.put(ticket);
                System.out.println("[Producer] Created: " + ticket.id());
            }

            for (int i = 0; i < consumerCount; i++) {
                queue.put(SupportTicket.POISON_PILL);
            }
            System.out.println("Producer sent poison pills and finished");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
