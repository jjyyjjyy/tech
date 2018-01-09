package me.jy.factorymethod;

/**
 * @author jy
 * @date 2018/01/09
 */
public class CCBCard extends ICard {
    @Override
    protected String getType() {
        return "CCB";
    }
}
