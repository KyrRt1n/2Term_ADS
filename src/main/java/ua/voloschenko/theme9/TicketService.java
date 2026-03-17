package ua.voloschenko.theme9;

public class TicketService {

    public String buildTicketId(String base) {
        double a = Math.random() * 256;
        double b = Math.random() * 322;
        int ticketId = (int) (a * b);

        class IdBuilder {
            public String generate() {
                return "TICKET-" + base + "-" + ticketId;
            }
        }
        IdBuilder builder = new IdBuilder();
        return builder.generate();
    }

    public Runnable runOnce(){
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("Running once");
            }
        };
    }
}