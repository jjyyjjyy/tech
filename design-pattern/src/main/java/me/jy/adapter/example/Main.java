package me.jy.adapter.example;

import java.io.IOException;

/**
 * @author jy
 * @date 2018/01/08
 */
public class Main {

    public static void main(String[] args) throws IOException {
        FileIO fileIO = new FileProperties();
        fileIO.readFromFile("c://Users/jy/Downloads/test.properties");
        fileIO.setValue("aaa",111);
        fileIO.setValue("bbb",222);
        fileIO.writeToFile("./another.properties");
    }
}
