package me.jy.observer;

/**
 * @author jy
 * @date 2018/01/10
 */
public class Observer {

    public void update(Object obj) {
        System.out.println("receive update message: " + obj);
    }
}
