package me.jy.lang.inner;

/**
 * @author jy
 */
public class MemberClassDemo {

    private static int f = 12;
    private int modCount = 0;

    public static void main(String[] args) {
        new MemberClassDemo().test();
    }

    public void test(d dd) {
        dd.m();
    }

    public void test() {
        test(new d(11) {
            @Override
            void m() {
                System.out.println(11);
            }
        });
    }

    private static class StaticInner {

        public static void init() {
            System.out.println(f);
        }
    }

    private class ParentInner {
        //        private static int si = 10; // sad
        private int exceptedModCount = modCount;// reachable
    }

    private abstract class d {
        public d(int a) {

        }

        abstract void m();
    }

}
