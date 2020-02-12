package me.jy._16_visitor;

/**
 * @author jy
 */
public interface Element {

    void accept(Visitor visitor);
}
