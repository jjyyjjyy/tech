package me.jy._1_simple_factory;

/**
 * @author jy
 */
public class Main {

    public static void main(String[] args) {
        Drawer cycleDrawer = DrawerFactory.getDrawer(DrawerType.CYCLE);

        cycleDrawer.draw();

        cycleDrawer.erase();
    }
}
