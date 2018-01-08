package me.jy.adapter.example;

import java.io.IOException;

/**
 * @author jy
 * @date 2018/01/08
 */
public interface FileIO {

    void readFromFile(String fileName) throws IOException;

    void writeToFile(String fileName) throws IOException;

    void setValue(String key, Object value);

    Object getValue(String key);
}
