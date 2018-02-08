package me.jy.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author jy
 * @date 2018/02/08
 */
public class IOUtils {


    public static void close(Closeable closeable) {
        try {
            if (closeable != null)
                closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
