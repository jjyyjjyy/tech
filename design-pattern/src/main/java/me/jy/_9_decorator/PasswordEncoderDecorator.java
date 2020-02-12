package me.jy._9_decorator;

/**
 * @author jy
 */
public abstract class PasswordEncoderDecorator implements PasswordEncoder {

    protected PasswordEncoder passwordEncoder;

    public PasswordEncoderDecorator(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}
