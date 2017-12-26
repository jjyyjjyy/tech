package me.jy.locator;

/**
 * @author jy
 * @date 2017/12/25
 */
public interface NamedService extends Service {

    default String getName() {
        return getClass().getSimpleName();
    }
}
