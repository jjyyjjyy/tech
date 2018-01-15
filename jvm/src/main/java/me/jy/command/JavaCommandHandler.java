package me.jy.command;

import java.util.Arrays;

import static me.jy.common.JVMConstants.JAVA_COMMAND;

/**
 * @author jy
 * @date 2018/01/15
 */
public class JavaCommandHandler implements CommandLineHandler {

    @Override
    public void handler(String[] args) {
    }

    @Override
    public boolean valid(String[] args) {
        if (!(args.length > 0) && JAVA_COMMAND.equalsIgnoreCase(args[0])) {
            throw new IllegalArgumentException("UnSupport command:" + Arrays.toString(args));
        }
        return true;
    }
}
