package ua.voloschenko.theme7;

import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderService {

    private static final Logger log = Logger.getLogger(OrderService.class.getName());

    public void checkout(int ID, String userEmail){
        try {
            doPayment();
        }
        catch(Exceptions.PaymentGatewayException e){
            log.log(Level.SEVERE, "Payment failed for Order ID: " + ID + " (User: " + userEmail + ")", e);

            throw new Exceptions.OrderProcessingException("Failed to process order for " + userEmail, e);
        }
    }

    private void doPayment() throws Exceptions.PaymentGatewayException {
        throw new Exceptions.PaymentGatewayException("Payment gateway is unresponsive");
    }
}