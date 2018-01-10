package me.jy.visitor;

/**
 * @author jy
 * @date 2018/01/10
 */
public interface Element {

    void accept(Visitor visitor);
}
