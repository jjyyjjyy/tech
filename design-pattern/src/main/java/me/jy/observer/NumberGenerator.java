package me.jy.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jy
 * @date 2018/01/10
 */
public abstract class NumberGenerator {

    private final List<Observer> observers = new ArrayList<>();

    public final void generate() {
        observers.forEach(o -> o.update(doGenerate()));
    }

    protected abstract Number doGenerate();


    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }
}
