package me.jy._10_decorator;

/**
 * @author jy
 */
public abstract class PasswordEncoderDecorator implements PasswordEncoder {

    protected PasswordEncoder passwordEncoder;

    public PasswordEncoderDecorator(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String raw) {
        return passwordEncoder.encode(raw);
    }

}
