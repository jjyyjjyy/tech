package me.jy.lang.privilege;

/**
 * @author jy
 * @date 2017/12/03
 */
public class Parent {

    protected void get() {
        System.out.println("You got me!");
    }


    static protected void staticGet() {
        System.out.println("Static:You got me!");
    }
}
