package me.jy.bus.event;

import java.util.EventObject;

/**
 * @author jy
 * @date 2018/02/08
 */
public class FailureEvent extends EventObject {

    private Exception e;

    public FailureEvent(Object source) {
        super(source);

        if (Exception.class.isAssignableFrom(source.getClass())) {
            this.e = (Exception) source;
        }

    }

    public Exception getException() {
        return e;
    }
}
