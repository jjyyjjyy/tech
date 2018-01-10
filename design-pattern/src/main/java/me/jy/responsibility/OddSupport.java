package me.jy.responsibility;

/**
 * @author jy
 * @date 2018/01/10
 */
public class OddSupport extends Support {
    @Override
    protected boolean support(Trouble trouble) {
        return trouble.getNumber() % 2 != 0;
    }
}
