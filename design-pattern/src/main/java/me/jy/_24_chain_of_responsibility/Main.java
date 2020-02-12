package me.jy._24_chain_of_responsibility;

/**
 * @author jy
 */
class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.setSuccessor(new Boss());

        manager.handlerRequest();
    }
}
