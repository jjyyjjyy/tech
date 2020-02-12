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
        beforeRequest();
        subject.request();
        afterRequest();
    }

    private void beforeRequest() {
    }

    private void afterRequest() {
    }
}
