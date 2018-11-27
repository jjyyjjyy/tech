package me.jy._2_factory_method;

/**
 * @author jy
 */
public class ConsoleLogger implements Logger {

    private ConsoleLogger() {
    }

    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
