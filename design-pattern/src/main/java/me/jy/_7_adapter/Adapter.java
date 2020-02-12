package me.jy._7_adapter;

import lombok.Data;

/**
 * @author jy
 */
@Data
public class Adapter implements Target {

    private Adaptee adaptee;

    public void call() {
        adaptee.anotherCall();
    }
}
