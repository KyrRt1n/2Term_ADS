package ua.voloschenko.theme14;

public class PracticeMain {
    public static void main(String[] args) {
        ClassInfoPrinter.print(Book.class);

        //task2
        //task2
        //task2
        //task2
        //task2
        //task2
        //task2
        //task2

        PaymentService cardService = ServiceFactory.create("ua.voloschenko.theme14.CardPaymentService");
        PaymentService cashService = ServiceFactory.create("ua.voloschenko.theme14.CashPaymentService");

        cardService.pay(555.55);
        cashService.pay(350);
    }
}