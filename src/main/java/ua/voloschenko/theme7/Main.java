package ua.voloschenko.theme7;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        //Order Correct = new Order(10, "luxongamesofficial@gmail.com", 1.5);
        //Order IncorrectID = new Order(-5, "konodioda@gmail.com", 1.5);
        //Order IncorrectEmail = new Order(10, "lux.mail.com", 1.5);
        //Order IncorrectCents = new Order(10, "lux@mail.com", -1.5);

        //OrderService orderService = new OrderService();
        //orderService.checkout(15, "av@gw");

        ReceiptService service = new ReceiptService();
        service.generate("JoskiReceipt");

    }
}


