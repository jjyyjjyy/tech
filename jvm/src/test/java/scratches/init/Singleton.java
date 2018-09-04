package scratches.init;

/**
 * @author jy
 */
public class Singleton {

    private Singleton() {
    }

    public static Object getInstance(boolean flag) {
        if (flag) {
            return new SingletonHolder[2];
        }
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(getInstance(true)); // new数组只会加载类, 不会导致类被链接&初始化
        System.out.println("----");
        System.out.println(getInstance(false));
    }

    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();

        static {
            System.out.println("SingletonHolder <clinit>");
            if (true) throw new RuntimeException();
        }
    }
}
