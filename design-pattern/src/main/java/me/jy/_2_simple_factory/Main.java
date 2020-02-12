package me.jy._2_simple_factory;

/**
 * @author jy
 */
class Main {

    public static void main(String[] args) {
        Drawer cycleDrawer = DrawerFactory.getDrawer(DrawerType.CYCLE);

        cycleDrawer.draw();

        cycleDrawer.erase();
    }
}
