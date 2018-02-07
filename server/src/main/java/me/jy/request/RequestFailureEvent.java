package me.jy.request;

import java.util.EventObject;

/**
 * @author jy
 * @date 2018/02/08
 */
public class RequestFailureEvent extends EventObject {

    public RequestFailureEvent(Object o) {
        super(o);
    }
}
