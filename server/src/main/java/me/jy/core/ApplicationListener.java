package me.jy.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EventObject;

/**
 * @author jy
 * @date 2018/02/08
 */
public interface ApplicationListener<E extends EventObject> {

    Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);

    default void handle(E e) {
        if (e != null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(e.toString());
            }
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(e.toString());
            }
        }
    }
}
