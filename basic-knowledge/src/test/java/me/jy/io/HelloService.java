package me.jy.io;

/**
 * @author jy
 * @date 2018/02/05
 */
public class HelloService implements ServiceInterface {
    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }
}
