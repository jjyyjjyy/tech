package me.jy._20_memento;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jy
 */
public class CareTaker {

    private List<Memento> mementos = new ArrayList<>();

    public void addMemento(Memento memento) {
        mementos.add(memento);
    }

    public Memento get(int index) {
        return mementos.get(index);
    }
}
