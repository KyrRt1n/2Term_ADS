package ua.voloschenko.theme7;
import java.io.IOException;

public class Exceptions {

    public static class PaymentGatewayException extends Exception {
        public PaymentGatewayException(String message) {
            super(message);
        }
    }

    public static class AppException extends RuntimeException {
        public AppException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class OrderProcessingException extends AppException {
        public OrderProcessingException(String message, Throwable cause) {
            super(message, cause);
        }
    }


    public static class ReceiptGenerationException extends RuntimeException {
        public ReceiptGenerationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
