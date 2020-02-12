package me.jy._18_observer;

/**
 * @author jy
 */
public class Observer {

    public void update(Object obj) {
        System.out.println("receive update message: " + obj);
    }
}
