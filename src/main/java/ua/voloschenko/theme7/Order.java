package ua.voloschenko.theme7;

public record Order(int id, String userEmail, double totalCents){
    public Order{
        if(id<=0)
            throw new IllegalArgumentException("ID must be > 0");
        if(!userEmail.contains("@"))
            throw new IllegalArgumentException("Email address must contain @");
        if(totalCents<0)
            throw new IllegalArgumentException("Total Cents must be positive");

        if(totalCents<id)
            try {
                throw new Exceptions.PaymentGatewayException("Total Cents must be positive");
            } catch (Exceptions.PaymentGatewayException e) {
                throw new RuntimeException(e);
            }
    }
}
