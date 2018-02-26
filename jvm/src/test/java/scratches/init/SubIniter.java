package scratches.init;

/**
 * @author jy
 * @date 2018/02/25
 */
public class SubIniter extends Initer {

    public static final int sub_i = 2;

    static {
        System.out.println("sub init");
    }

    public static void hello() {
        System.out.println("hello");
    }
}
