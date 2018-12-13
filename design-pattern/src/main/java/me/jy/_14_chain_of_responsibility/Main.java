package me.jy._14_chain_of_responsibility;

/**
 * @author jy
 */
public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.setSuccessor(new Boss());

        manager.handlerRequest();
    }
}
