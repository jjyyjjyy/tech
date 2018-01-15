package me.jy.command;

import me.jy.common.JVMConstants;

import static me.jy.common.JVMConstants.CP_OPTION;

/**
 * @author jy
 * @date 2018/01/15
 */
public class ClassPathCommandHandler implements CommandLineHandler {

    @Override
    public void handler(String[] args) {
        JVMConstants.CLASSPATH.add(args[2]);
    }

    @Override
    public boolean valid(String[] args) {
        return args.length > 3 && CP_OPTION.stream().anyMatch(opt -> opt.equalsIgnoreCase(args[1]));
    }
}
