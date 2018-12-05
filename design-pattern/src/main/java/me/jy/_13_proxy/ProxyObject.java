package me.jy._13_proxy;

import lombok.Data;

/**
 * @author jy
 */
@Data
public class ProxyObject implements Subject {

    private Subject subject;

    @Override
    public void request() {
        // pre
        subject.request();
        // post
    }
}
