package me.jy._1_simple_factory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jy
 */
@Slf4j
public class RectangleDrawer implements Drawer {
    @Override
    public void draw() {
        log.info("RectangleDrawer is drawing");
    }

    @Override
    public void erase() {
        log.info("RectangleDrawer is erasing");
    }
}
