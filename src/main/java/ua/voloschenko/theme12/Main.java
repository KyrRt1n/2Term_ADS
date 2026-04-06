package ua.voloschenko.theme12;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static ua.voloschenko.theme12.InboxArchiver.archiveTmpFiles;
import static ua.voloschenko.theme12.PathSafety.safeResolve;

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

        //Task 2

        Path reportPath = Path.of("report.txt");
        try {
            PaymentReportWriter.writeReport(reportPath, result.payments(), result.invalidLines());
            System.out.println("\nReported to: " + reportPath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error in creating report: " + e.getMessage());
        }

        //Task 3
        Path inboxDir = Path.of("practical-data", "inbox");
        Path archiveDir = Path.of("practical-data", "archive");

        try {
            Files.createDirectories(inboxDir);

            Files.writeString(inboxDir.resolve("report1.txt"), "Important");
            Files.writeString(inboxDir.resolve("cache1.tmp"), "Temp 1");
            Files.writeString(inboxDir.resolve("notes.txt"), "Hello world");
            Files.writeString(inboxDir.resolve("cache2.tmp"), "Temp2");

            System.out.println("Test created, start archiving");

            // Викликаємо наш метод
            archiveTmpFiles(inboxDir, archiveDir);

        } catch (IOException e) {
            System.err.println("Test data error " + e.getMessage());
        }

        //Task 4
        Path base = Path.of("practical-data");

        System.out.println("Base directory " + base.normalize() + "\n");

        System.out.println("--- Test 1: reports/2025.txt ---");
        try {
            Path safePath = safeResolve(base, "reports/2025.txt");
            System.out.println("Approved: " + safePath);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println();

        System.out.println("--- Test 2: ../secret.txt ---");
        try {
            Path unsafePath = safeResolve(base, "../secret.txt");
            System.out.println("Approved: " + unsafePath);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
}