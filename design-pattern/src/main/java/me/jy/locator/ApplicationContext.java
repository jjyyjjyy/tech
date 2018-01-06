package me.jy.locator;

/**
 * @author jy
 * @date 2017/12/25
 */
public class ApplicationContext {

    private static final String PACKAGE_SEPARATOR = ".";

    public <T> T lookup(String serviceName, Class<T> type) {
        String packagePrefix = getClass().getPackage().getName();
        try {
            return type.cast(Class.forName(packagePrefix + PACKAGE_SEPARATOR + serviceName).newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("No such service found: " + serviceName);
    }
}
