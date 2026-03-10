package ua.voloschenko.theme7;

import java.io.IOException;

public class ReceiptWriter implements AutoCloseable {

    public void write(String receiptData) throws IOException {
        System.out.println("Запис чеку: " + receiptData);

    }

    @Override
    public void close() throws IOException {
        System.out.println("Метод close() викликано: ReceiptWriter успіш но закрито.");
    }
}