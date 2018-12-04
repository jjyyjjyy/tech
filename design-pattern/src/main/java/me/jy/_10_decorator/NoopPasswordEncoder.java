package me.jy._10_decorator;

/**
 * @author jy
 */
public class NoopPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(String raw) {
        return raw;
    }
}
