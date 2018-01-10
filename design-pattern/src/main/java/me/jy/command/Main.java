package me.jy.command;

/**
 * @author jy
 * @date 2018/01/10
 */
public class Main {

    public static void main(String[] args) {

        Keyboard keyboard = new Keyboard(new AudioPlayer());
        keyboard.play();
        keyboard.rewind();
        keyboard.stop();
    }
}
