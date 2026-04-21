package ua.voloschenko.theme14;

public class CashPaymentService implements PaymentService {
    @Override
    public void pay(double amount) {
        System.out.println("Cash payment success. Amount " + amount);
    }
}
