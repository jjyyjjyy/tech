package me.jy._18_observer;

/**
 * @author jy
 */
class Main {

    public static void main(String[] args) {

        Observer observer = new Observer();
        NumberGenerator generator = new OddNumberGenerator();

        generator.addObserver(observer);
        generator.addObserver(observer);
        generator.addObserver(observer);
        generator.addObserver(observer);

        generator.generate();
    }
}
