package ua.voloschenko.theme7;

import java.io.IOException;

public class ReceiptService {

    public void generate(String receiptData) {
        try (ReceiptWriter writer = new ReceiptWriter()) {

            writer.write(receiptData);

        } catch (IOException e) {
            throw new Exceptions.ReceiptGenerationException("Cannot generate receipt cuz of IOexception", e);
        }
    }
}