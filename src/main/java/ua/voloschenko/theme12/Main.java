package ua.voloschenko.theme12;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static ua.voloschenko.theme12.InboxArchiver.archiveTmpFiles;

public class Main {

    public record Payment(String id, String email, PaymentStatus status, double amountCents){}

    public record PaymentLoadResult(List<Payment> payments, int invalidLines){}

    public enum PaymentStatus{
        NEW,
        PAID,
        FAILED
    }

    public static class PaymentLoader{

        public static PaymentLoadResult loadWithStats(Path csv){
            List<Payment> payments = new ArrayList<>();
            int invalidLines = 0;

            try (BufferedReader reader = Files.newBufferedReader(csv, StandardCharsets.UTF_8)) {

                String header = reader.readLine();
                if (header == null)
                    return new PaymentLoadResult(payments, invalidLines); // empty file

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(",");
                    if (fields.length != 4) {
                        invalidLines++;
                        continue;
                    }
                    try {
                        String id = fields[0].trim();
                        String email = fields[1].trim();
                        PaymentStatus status = PaymentStatus.valueOf(fields[2].trim().toUpperCase());
                        int amountCents = Integer.parseInt(fields[3].trim());

                        payments.add(new Payment(id, email, status, amountCents));

                    } catch (Exception e) {
                        invalidLines++;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return new PaymentLoadResult(payments, invalidLines);
        }
    }

    public static void main(String[] args) {
        Path csvPath = Path.of("csv.csv");

        PaymentLoadResult result = PaymentLoader.loadWithStats(csvPath);

        System.out.println("Successfully loaded: " + result.payments().size());
        System.out.println("Dropped invalids: " + result.invalidLines());

        System.out.println("\nSuccessful payments:");
        for (Payment p : result.payments()) {
            System.out.println(p);
        }

    }
}