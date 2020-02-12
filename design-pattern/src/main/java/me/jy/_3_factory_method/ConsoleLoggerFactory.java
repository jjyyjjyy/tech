package me.jy._3_factory_method;

/**
 * @author jy
 */
public class ConsoleLoggerFactory implements LoggerFactory {

    @Override
    public Logger createLogger() {
        return new ConsoleLogger();
    }
}
