package me.jy._14_chain_of_responsibility;

import lombok.Data;

/**
 * @author jy
 */
@Data
public abstract class Approver {

    protected Approver successor;

    public void handlerRequest() {
        doRequest();
        if (successor != null) {
            successor.doRequest();
        }
    }

    protected abstract void doRequest();

}
