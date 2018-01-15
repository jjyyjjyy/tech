package me.jy.common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jy
 * @date 2018/01/15
 */
public final class JVMConstants {

    private JVMConstants() {
    }


    public static final String JAVA_COMMAND = "java";
    public static final String HELP_OPTION = "help";
    public static final List<String> CP_OPTION = Arrays.asList("-cp", "-classpath");

    public static final List<String> CLASSPATH = Stream.of(".").collect(Collectors.toList());

}
