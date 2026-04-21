package ua.voloschenko.theme14;

public class PracticeMain {
    public static void main(String[] args) {
        ClassInfoPrinter.print(Book.class);

        //task2
        System.out.println("\n\n\ntask2");
        //task2

        PaymentService cardService = ServiceFactory.create("ua.voloschenko.theme14.CardPaymentService");
        PaymentService cashService = ServiceFactory.create("ua.voloschenko.theme14.CashPaymentService");

        cardService.pay(555.55);
        cashService.pay(350);

        //task3
        System.out.println("\n\n\ntask3");
        //task3

        SetupFlow flow = new SetupFlow();
        StepRunner.run(flow);
    }
}