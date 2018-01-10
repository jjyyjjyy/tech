package me.jy.responsibility;

/**
 * @author jy
 * @date 2018/01/10
 */
public class NoSupport extends Support {
    @Override
    protected boolean support(Trouble trouble) {
        return false;
    }
}
