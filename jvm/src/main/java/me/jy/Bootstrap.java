package me.jy;

import me.jy.command.ClassPathCommandHandler;
import me.jy.command.CommandLineHandler;
import me.jy.command.HelpCommandHandler;
import me.jy.command.JavaCommandHandler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jy
 * @date 2018/01/15
 */
public class Bootstrap {

    private static final List<CommandLineHandler> COMMAND_LINE_HANDLERS;

    static {
        COMMAND_LINE_HANDLERS = Stream.of(
                new JavaCommandHandler(),
                new HelpCommandHandler(),
                new ClassPathCommandHandler())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        for (CommandLineHandler handler : COMMAND_LINE_HANDLERS) {
            if (handler.valid(args)) {
                handler.handler(args);
            }
        }


    }
}
