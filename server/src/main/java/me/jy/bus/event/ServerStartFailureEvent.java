package me.jy.bus.event;

import java.util.EventObject;

/**
 * @author jy
 * @date 2018/02/08
 */
public class ServerStartFailureEvent extends EventObject {

    public ServerStartFailureEvent(Object source) {
        super(source);
    }
}
