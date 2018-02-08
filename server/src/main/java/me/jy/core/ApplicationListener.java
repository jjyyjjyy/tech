package me.jy.core;

import me.jy.util.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EventObject;

/**
 * @author jy
 * @date 2018/02/08
 */
public interface ApplicationListener<E extends EventObject> {

    Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);

    default void handle(Object e) {

        if (e == null || !(EventObject.class.isAssignableFrom(e.getClass()) && support((EventObject) e))) {
            return;
        }
        /*if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(e.toString());
        }*/
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(e.toString());
        }
    }

    @SuppressWarnings("unchecked")
    default <T extends EventObject> boolean support(T eventObject) {
        Class<?> actualType = ClassUtils.getGenericType(getClass());
        return eventObject.getClass().isAssignableFrom(actualType);
    }
}
