package me.jy._14_chain_of_responsibility;

/**
 * @author jy
 */
public class Manager extends Approver {

    @Override
    protected void doRequest() {
        System.out.println("Manager handling request");
    }
}
