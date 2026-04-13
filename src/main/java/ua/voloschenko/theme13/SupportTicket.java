package ua.voloschenko.theme13;

public record SupportTicket(long id, String customer, String topic) {
    public static final SupportTicket POISON_PILL = new SupportTicket(-1, "shutdown", "shutdown");
}