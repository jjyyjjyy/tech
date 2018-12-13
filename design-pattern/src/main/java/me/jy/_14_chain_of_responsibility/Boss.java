package me.jy._14_chain_of_responsibility;

/**
 * @author jy
 */
public class Boss extends Approver {

    @Override
    protected void doRequest() {
        System.out.println("BOSS handling request");
    }
}
