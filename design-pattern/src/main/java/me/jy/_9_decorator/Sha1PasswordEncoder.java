package me.jy._9_decorator;

/**
 * @author jy
 */
public class Sha1PasswordEncoder extends PasswordEncoderDecorator {

    public Sha1PasswordEncoder(PasswordEncoder passwordEncoder) {
        super(passwordEncoder);
    }

    private static String sha1(String raw) {
        return "{sha1}" + raw;
    }

    @Override
    public String encode(String raw) {
        return sha1(passwordEncoder.encode(raw));
    }

}
