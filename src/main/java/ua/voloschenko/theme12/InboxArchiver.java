package ua.voloschenko.theme12;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class InboxArchiver {

    public static void archiveTmpFiles(Path inbox, Path archive){
        try {
            Files.createDirectories(archive);

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(inbox, "*.tmp")) {
                int i = 0;
                for(Path sourceFile : stream){
                    Path targetFile = archive.resolve(sourceFile.getFileName());
                    Files.move(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Moved: " + sourceFile + " -> " + targetFile);
                    i++;
                }
                System.out.println("Archived " + i + " files");
            }

        } catch (IOException e) {
            System.out.println("Archivation error " + e.getMessage());
        }
    }

}
