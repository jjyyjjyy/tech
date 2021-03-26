package me.jy.lang.thread.interview;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ABC交替打印5次
 *
 * @author jy
 */
public class PrintInTurn {

    private enum Type {
        A, B, C
    }

    private static class Solution1 {

        private static Thread t1, t2, t3;

        public static void main(String[] args) {
            t1 = new Thread(() -> {
                int times = 5;
                while (times-- > 0) {
                    System.out.println("A");
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            });
            t2 = new Thread(() -> {
                int times = 5;
                while (times-- > 0) {
                    LockSupport.park();
                    System.out.println("B");
                    LockSupport.unpark(t3);
                }
            });
            t3 = new Thread(() -> {
                int times = 5;
                while (times-- > 0) {
                    LockSupport.park();
                    System.out.println("C");
                    LockSupport.unpark(t1);
                }
            });
            t1.start();
            t2.start();
            t3.start();
        }
    }

    private static class Solution2 {

        private static volatile Type type = Type.A;

        public static void main(String[] args) {
            new Thread(() -> {
                int times = 5;
                while (times-- > 0) {
                    while (type != Type.A) {
                        Thread.onSpinWait();
                    }
                    System.out.println("A");
                    type = Type.B;
                }
            }).start();
            new Thread(() -> {
                int times = 5;
                while (times-- > 0) {
                    while (type != Type.B) {
                        Thread.onSpinWait();
                    }
                    System.out.println("B");
                    type = Type.C;
                }
            }).start();
            new Thread(() -> {
                int times = 5;
                while (times-- > 0) {
                    while (type != Type.C) {
                        Thread.onSpinWait();
                    }
                    System.out.println("C");
                    type = Type.A;
                }
            }).start();
        }

    }

    private static class Solution3 {
        private static final ReentrantLock lock = new ReentrantLock();
        private static Type type = Type.A;
        private static Condition conditionA = lock.newCondition();
        private static Condition conditionB = lock.newCondition();
        private static Condition conditionC = lock.newCondition();

        public static void main(String[] args) {
            new Thread(() -> {
                int times = 5;
                try {
                    lock.lock();
                    while (times-- > 0) {
                        while (type != Type.A) {
                            conditionA.await();
                        }
                        System.out.println("A");
                        type = Type.B;
                        conditionB.signal();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }).start();
            new Thread(() -> {
                int times = 5;
                try {
                    lock.lock();
                    while (times-- > 0) {
                        while (type != Type.B) {
                            conditionB.await();
                        }
                        System.out.println("B");
                        type = Type.C;
                        conditionC.signal();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }).start();
            new Thread(() -> {
                int times = 5;
                try {
                    lock.lock();
                    while (times-- > 0) {
                        while (type != Type.C) {
                            conditionC.await();
                        }
                        System.out.println("C");
                        type = Type.A;
                        conditionA.signal();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }).start();
        }
    }

    private static class Solution4 {

        private static final Semaphore semaphoreA = new Semaphore(1);
        private static final Semaphore semaphoreB = new Semaphore(0);
        private static final Semaphore semaphoreC = new Semaphore(0);

        public static void main(String[] args) {
            new Thread(() -> {
                int times = 5;
                while (times-- > 0) {
                    try {
                        semaphoreA.acquire();
                        System.out.println("A");
                        semaphoreB.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
            new Thread(() -> {
                int times = 5;
                while (times-- > 0) {
                    try {
                        semaphoreB.acquire();
                        System.out.println("B");
                        semaphoreC.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
            new Thread(() -> {
                int times = 5;
                while (times-- > 0) {
                    try {
                        semaphoreC.acquire();
                        System.out.println("C");
                        semaphoreA.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
    }
}
