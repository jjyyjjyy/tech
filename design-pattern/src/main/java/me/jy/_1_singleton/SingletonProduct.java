package me.jy._1_singleton;

/**
 * @author jy
 */
public class SingletonProduct {

    // tag::singleton-lazy-1[]
    private static class S1 {

        private static final S1 INSTANCE = new S1();

        public static S1 getInstance() {
            return INSTANCE;
        }
    }
    // end::singleton-lazy-1[]

    // tag::singleton-lazy-2[]
    private static class S2 {
        private static final S2 INSTANCE;

        static {
            INSTANCE = new S2();
        }

        public static S2 getInstance() {
            return INSTANCE;
        }
    }
    // end::singleton-lazy-2[]

    // tag::singleton-eager-1[]
    private static class S3 {
        private static volatile S3 INSTANCE;

        public synchronized static S3 getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new S3();
            }
            return INSTANCE;
        }
    }
    // end::singleton-eager-1[]

    // tag::singleton-eager-2[]
    private static class S4 {
        private static volatile S4 INSTANCE;

        public static S4 getInstance() {
            if (INSTANCE == null) {
                synchronized (S4.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new S4();
                    }
                }
            }
            return INSTANCE;
        }
    }
    // end::singleton-eager-2[]

    // tag::singleton-inner-static-class[]
    private static class S5 {

        public static S5 getInstance() {
            return S5Holder.INSTANCE;
        }

        private static class S5Holder {
            private static S5 INSTANCE = new S5();
        }
    }
    // end::singleton-inner-static-class[]

    // tag::singleton-enum[]
    private enum S6 {
        INSTANCE;

        public void doStuff() {
            // NO-OP
        }
    }
    // end::singleton-enum[]

}
