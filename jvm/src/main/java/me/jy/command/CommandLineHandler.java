package me.jy.command;

/**
 * @author jy
 * @date 2018/01/15
 */
public interface CommandLineHandler {

    void handler(String[] args);

    default boolean valid(String[] args) {
        return true;
    }
}
