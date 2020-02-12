package me.jy._11_facade;

/**
 * @author jy
 */
class Main {

    public static void main(String[] args) {
        SubSystemFacade facade = new SubSystemFacade();
        facade.setSystemA(new SystemA());
        facade.setSystemB(new SystemB());
        facade.setSystemC(new SystemC());

        facade.operate();
    }
}
