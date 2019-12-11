package me.jy.lang.random;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jy
 * @date 2018/02/24
 */
public class RandomDeviceFileDemo {

    private static final String RANDOM = "/dev/random";
    private static final String U_RANDOM = "/dev/urandom";

    private static final ExecutorService pool = Executors
        .newFixedThreadPool(10);

    private static String toHex(byte[] result) {
        StringBuilder sb = new StringBuilder(result.length * 2);
        for (byte aResult : result) {
            sb.append(Character.forDigit((aResult & 240) >> 4, 16));
            sb.append(Character.forDigit(aResult & 15, 16));
        }
        return sb.toString();

    }

    public static void main(String[] args) throws Exception {
        String file = args.length == 0 ? RANDOM : args[0];
        FileInputStream inputStream = new FileInputStream(file);
        for (int i = 0; i < 1000; i++) {
            pool.execute(new RandomThread(inputStream));
        }
    }

    private static class RandomThread implements Runnable {

        private final InputStream inputStream;

        private RandomThread(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            byte[] b = new byte[16];
            try {
                inputStream.read(b);
                System.out.println(toHex(b));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
