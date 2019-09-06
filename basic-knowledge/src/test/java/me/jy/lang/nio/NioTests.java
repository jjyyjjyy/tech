package me.jy.lang.nio;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author jy
 */
class NioTests {

    @Test
    @SneakyThrows
    void testChannelRead() {
        URI uri = getClass().getClassLoader().getResource("./kern.log").toURI();
        FileChannel fileChannel = new FileInputStream(new File(uri)).getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int length;
        StringBuilder sb = new StringBuilder();
        while ((length = fileChannel.read(byteBuffer)) > 0) {
            byte[] bytes = new byte[length];
            byteBuffer.flip();
            byteBuffer.get(bytes);
            byteBuffer.clear();
            sb.append(new String(bytes));
        }
        String fileContentViaFileChannel = sb.toString().trim();

        String fileContentViaFiles = String.join(System.lineSeparator(), Files.readAllLines(Paths.get(uri)));

        Assertions.assertEquals(fileContentViaFiles, fileContentViaFileChannel);
        System.out.println(Integer.toBinaryString(-15));
        System.out.println(Short.MIN_VALUE);
        byte s = (byte) (Byte.MAX_VALUE + 1);
        System.out.println(s);
    }

}
