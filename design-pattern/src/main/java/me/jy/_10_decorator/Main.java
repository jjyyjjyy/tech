package me.jy._10_decorator;

/**
 * @author jy
 */
public class Main {

    public static void main(String[] args) {
        String raw = "123456";
        System.out.println(new Sha1PasswordEncoder(new Md5PasswordEncoder(new NoopPasswordEncoder())).encode(raw));

    }
}
