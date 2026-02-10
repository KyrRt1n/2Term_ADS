package ua.voloschenko.theme5;

public class Theme5 {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        PaymentMethod card = new CardPayment();
        PaymentMethod paypal = new PayPalPayment();
        PaymentProcessor cpu = new PaymentProcessor();

        cpu.process(card, 150);
        cpu.process(paypal, 250);
        card.payWithFee(50, 20);
        System.out.println();

        ReportGenerator rg = new DailyReport();

        rg.genReport();
        System.out.println();


        MultiNotifier notif =  new MultiNotifier();
        notif.send();

    }

    static class MultiNotifier implements SmsNotifier, EmailNotifier{

        @Override
        public void send() {
            SmsNotifier.super.send();
            EmailNotifier.super.send();
        }
    }

    interface SmsNotifier {
        default void send() {
            System.out.println("You received SMS notification: ");
        }
    }
    interface EmailNotifier {
        default void send() {
            System.out.println("You received Email notification: ");
        }
    }


    static abstract class ReportGenerator {
        public final void genReport(){
            System.out.println("start");
            gatherData();
            sendReport();
            System.out.println("end");
        }

        protected abstract void sendReport();

        protected abstract void gatherData();
    }

    static class DailyReport extends ReportGenerator {
        @Override
        protected void sendReport() {
            System.out.println("Daily report data sending");
        }

        @Override
        protected void gatherData() {
            System.out.println("Daily report data gathering");
        }
    }

    interface PaymentMethod{
        String name();
        void pay(int amount);
        default void payWithFee(int amount, int fee){
            System.out.printf("Paying " +  amount + " with " + fee + " fee");
            pay(amount + fee);
        }
    }

    static class CardPayment implements PaymentMethod {
        @Override
        public String name() {
            return "Card";
        }

        @Override
        public void pay(int amount) {
            System.out.println("Paying with card " + amount);
        }
    }

    static class PayPalPayment implements PaymentMethod {
        @Override
        public String name() {
            return "PayPal";
        }

        @Override
        public void pay(int amount) {
            System.out.println("Paying with PayPal " + amount);
        }
    }

    static class PaymentProcessor{
        public void process(PaymentMethod paymentMethod, int amount){
            System.out.println("Processing " + paymentMethod.name());
            paymentMethod.pay(amount);
        }
    }
}