package ua.voloschenko.theme12;

import java.nio.file.Path;

public class PathSafety {

    public static Path safeResolve(Path base, String userInput){
        Path resolved = base.resolve(userInput).normalize();

        Path normalizedBase = base.normalize();

        if (resolved.startsWith(normalizedBase)) {
            return resolved;
        } else {
            throw new IllegalArgumentException("Unsafe path attempt: " + userInput);
        }
    }

}
