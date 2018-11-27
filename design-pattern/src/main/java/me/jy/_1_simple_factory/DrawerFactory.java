package me.jy._1_simple_factory;

import java.util.Objects;

/**
 * @author jy
 */
public abstract class DrawerFactory {

    public static Drawer getDrawer(DrawerType drawerType) {
        if (Objects.equals(DrawerType.CYCLE, drawerType)) {
            return new CycleDrawer();
        } else if (Objects.equals(DrawerType.RECTANGLE, drawerType)) {
            return new RectangleDrawer();
        }
        throw new UnSupportedShapeException("UnSupportedShape: " + drawerType);
    }
}
