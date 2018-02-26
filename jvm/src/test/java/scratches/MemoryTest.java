package scratches;

/**
 * @author jy
 * @date 2018/02/25
 */
public class MemoryTest {

    private static final int MB = 1024 * 1024;

    static String concat(String base) {
        base += base;
        return base;
    }

    public static void main(String[] args) throws InterruptedException {
        /*String b = new String("ASDF").intern();
        String ba = "ASDF";
        String base = "ASDF";
        System.out.println(System.identityHashCode(b));
        System.out.println(System.identityHashCode(ba));
        System.out.println(System.identityHashCode(base));*/

        /*List<byte[]> list = new ArrayList<>(100);
        while (true) {
            list.add(new byte[1024]);
        }*/

        /*String s = new StringBuilder("ja").append("via").toString();
        System.out.println(s == "javia");// false */

        /*Map<Integer, Obj> map = new WeakHashMap<>();
        map.put(1, new Obj());
        System.gc();
        System.runFinalization();
        System.out.println(map.get(1));*/

        //-Xmx20M -Xms20M -Xmn10M -verbose:gc -XX:+PrintGCDetails
        byte[] b1 = new byte[MB];
        byte[] b2 = new byte[2 * MB];
        byte[] b3 = new byte[3 * MB];
        byte[] b4 = new byte[4 * MB];
    }

    private static class Obj {
        private String i = "a";

        @Override
        public String toString() {
            return i;
        }
    }

}
