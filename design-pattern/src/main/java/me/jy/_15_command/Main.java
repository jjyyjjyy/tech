package me.jy._15_command;

/**
 * @author jy
 */
class Main {

    public static void main(String[] args) {

        Keyboard keyboard = new Keyboard(new AudioPlayer());
        keyboard.play();
        keyboard.rewind();
        keyboard.stop();
    }
}
