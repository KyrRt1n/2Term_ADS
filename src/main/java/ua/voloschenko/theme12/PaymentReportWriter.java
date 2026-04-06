package ua.voloschenko.theme12;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class PaymentReportWriter {

    public static void writeReport(Path out, List<Main.Payment> payments, int invalidLines) throws IOException {

        int paidTotalCents = 0;
        int countNew = 0;
        int countPaid = 0;
        int countFailed = 0;

        for (Main.Payment p : payments) {
            switch (p.status()) {
                case NEW -> countNew++;
                case PAID -> {
                    countPaid++;
                    paidTotalCents += p.amountCents();
                }
                case FAILED -> countFailed++;
            }
        }

        Path tmpFile = out.resolveSibling(out.getFileName() + ".tmp");

        try(BufferedWriter writer = Files.newBufferedWriter(tmpFile, StandardCharsets.UTF_8)){
            writer.write("invalidLines=" + invalidLines);
            writer.newLine();

            writer.write("paidTotalCents=" + paidTotalCents);
            writer.newLine();

            writer.write("NEW=" + countNew + ", PAID=" + countPaid + ", FAILED=" + countFailed);
            writer.newLine();

            Files.move(tmpFile, out, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        }
    }
}
