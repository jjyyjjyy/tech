package me.jy._10_decorator;

/**
 * @author jy
 */
public class Md5PasswordEncoder extends PasswordEncoderDecorator {

    public Md5PasswordEncoder(PasswordEncoder passwordEncoder) {
        super(passwordEncoder);
    }

    private static String md5(String raw) {
        return "{md5}" + raw;
    }

    @Override
    public String encode(String raw) {
        return md5(passwordEncoder.encode(raw));
    }
}
