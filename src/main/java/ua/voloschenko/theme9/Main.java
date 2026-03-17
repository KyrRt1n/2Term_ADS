package ua.voloschenko.theme9;

public class Main {

    public static void main(String[] args) {
        TicketService ticketService = new TicketService();
        System.out.println(ticketService.buildTicketId("Scream"));

        Runnable runnable = new TicketService().runOnce();
        runnable.run();
    }

}
