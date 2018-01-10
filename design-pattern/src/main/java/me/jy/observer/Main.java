package me.jy.observer;

/**
 * @author jy
 * @date 2018/01/10
 */
public class Main {

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
