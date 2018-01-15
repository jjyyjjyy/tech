package me.jy.command;

import static me.jy.common.JVMConstants.HELP_OPTION;

/**
 * @author jy
 * @date 2018/01/15
 */
public class HelpCommandHandler implements CommandLineHandler {

    @Override
    public void handler(String[] args) {
        System.out.println("Mini JVM supported commands:");
        System.out.println("help");
        System.out.println("-cp CLASSPATH className args...");
        System.out.println("-classpath CLASSPATH className args...");
        System.out.println("className args...");
        System.exit(0);
    }

    @Override
    public boolean valid(String[] args) {
        return args.length == 2 && HELP_OPTION.equalsIgnoreCase(args[1]);
    }
}
