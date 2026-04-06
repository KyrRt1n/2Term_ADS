package ua.voloschenko.theme12;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

public class StatusFile {

    public static void updateStatus(Path file, int index, byte status) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file.toFile(), "rw");
             FileChannel channel = raf.getChannel()) {

            channel.position(index);

            ByteBuffer buffer = ByteBuffer.wrap(new byte[]{status});
            channel.write(buffer);
        }
    }
}
