package me.jy.lang.thread;

/**
 * @author jy
 */
public class MemoryVisibility {

    private volatile int number = 0;

    public static void main(String[] args) throws Exception {
        MemoryVisibility mv = new MemoryVisibility();
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(mv::add);
            threads[i] = thread;
            thread.start();
        }
        for (int i = 0; i < 1000; i++) {
            threads[i].join();
        }
        System.out.println(mv.number);
    }

    public void add() {
        number++;
    }
}
