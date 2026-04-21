package ua.voloschenko.theme14;

public class CardPaymentService implements PaymentService {
    @Override
    public void pay(double amount) {
        System.out.println("Card payment success. Amount " + amount);
    }
}

