package me.jy._4_singleton;

/**
 * @author jy
 */
public class SingletonProduct {

    private enum S5 {
        INSTANCE;

        public void doStuff() {
            // NO-OP
        }
    }

    private static class S1 {
        private static final S1 INSTANCE = new S1();

        public static S1 getInstance() {
            return INSTANCE;
        }
    }

    private static class S2 {
        private static final S2 INSTANCE;

        static {
            INSTANCE = new S2();
        }

        public static S2 getInstance() {
            return INSTANCE;
        }
    }

    private static class S3 {
        private static volatile S3 INSTANCE;

        public static S3 getInstance() {
            if (INSTANCE == null) {
                synchronized (S3.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new S3();
                    }
                }
            }
            return INSTANCE;
        }
    }

    private static class S4 {

        public static S4 getInstance() {
            return S4Holder.INSTANCE;
        }

        private static class S4Holder {
            private static S4 INSTANCE = new S4();
        }
    }

}
