package me.jy._2_simple_factory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jy
 */
@Slf4j
public class CycleDrawer implements Drawer {

    @Override
    public void draw() {
        log.info("CycleDrawer is drawing");
    }

    @Override
    public void erase() {
        log.info("CycleDrawer is erasing");
    }
}
