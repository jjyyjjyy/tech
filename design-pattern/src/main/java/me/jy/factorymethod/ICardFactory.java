package me.jy.factorymethod;

/**
 * @author jy
 * @date 2018/01/09
 */
public class ICardFactory implements Factory {

    @Override
    public final Product create(String type) {
        if ("ICBC".equals(type)) {
            return new ICBCCard();
        }
        if ("CCB".equals(type)) {
            return new CCBCard();
        }
        throw new IllegalArgumentException("UnSupported iCard type!");
    }
}
