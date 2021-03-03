package me.jy.lang.thread;

import org.openjdk.jol.vm.VM;
import org.openjdk.jol.vm.VirtualMachine;

/**
 * @formatter:off
 * unused:25 hash:31 -->| unused_gap:1   age:4    biased_lock:1 lock:2 (normal object)
 * Object header size: 12
 * HashCode: 614ddd49 (x86 CPU平台使用小端存储, 所以二进制以8位为一个单位, hashCode从后向前数26~56位, 即1100001010011011101110101001001)
 * java.lang.Object object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           01 49 dd 4d (00000001 01001001 11011101 01001101) (1306347777)
 *       4     4        (object header)                           61 00 00 00 (01100001 00000000 00000000 00000000) (97)
 *       8     4        (object header)                           82 06 00 00 (10000010 00000110 00000000 00000000) (1666)
 *      12     4        (loss due to the next object alignment)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
 *
 * -XX:-UseCompressedClassPointers -XX:-UseCompressedOops: 8字节MarkWord+8字节类型指针
 * Object header size: 16
 * java.lang.Object object internals:
 *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 *       8     4        (object header)                           30 1e 00 2b (00110000 00011110 00000000 00101011) (721428016)
 *      12     4        (object header)                           02 00 00 00 (00000010 00000000 00000000 00000000) (2)
 * Instance size: 16 bytes
 * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
 * @formatter:on
 *
 * @author jy
 */
public class ObjectLayoutDemo {

    private static void printMarkWord(Object instance) {
        VirtualMachine virtualMachine = VM.current();
        instance.hashCode();

        long markWord = virtualMachine.getLong(instance, 0);
        long hashCode = (markWord & Long.parseLong("0".repeat(25) + "1".repeat(31) + "0".repeat(8), 2)) >>> 8;
        long age = (markWord & 0b1111000) >>> 3;
        boolean isBiasLock = (markWord & 0b100) >>> 2 == 1;
        System.out.println("HashCode: " + Long.toHexString(hashCode));
        System.out.println("对象分代年龄: " + age);
        System.out.println("是否处于偏向锁状态: " + isBiasLock);
    }

    public static void main(String[] args) {
        Object o = new Object();
        printMarkWord(o);
    }

}
