package memory;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author jy
 */
public class ObjectSizeTest {

    /**
     * -XX:+UseCompressedClassPointers -XX:+UseCompressedOops
     * <p>
     * java.lang.Integer object internals:
     * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     * 0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * 8     4        (object header)                           48 71 00 00 (01001000 01110001 00000000 00000000) (29000)
     * 12     4    int Integer.value                             1000
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
     * <p>
     * java.lang.Long object internals:
     * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     * 0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * 8     4        (object header)                           8d 76 00 00 (10001101 01110110 00000000 00000000) (30349)
     * 12     4        (alignment/padding gap)
     * 16     8   long Long.value                                100000
     * Instance size: 24 bytes
     * Space losses: 4 bytes internal + 0 bytes external = 4 bytes total
     * <p>
     * <p>
     * -XX:-UseCompressedClassPointers -XX:-UseCompressedOops
     * <p>
     * java.lang.Integer object internals:
     * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     * 0     4        (object header)                           09 00 00 00 (00001001 00000000 00000000 00000000) (9)
     * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * 8     4        (object header)                           48 0c 55 11 (01001000 00001100 01010101 00010001) (290786376)
     * 12     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     * 16     4    int Integer.value                             1000
     * 20     4        (loss due to the next object alignment)
     * Instance size: 24 bytes
     * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
     * <p>
     * java.lang.Long object internals:
     * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     * 0     4        (object header)                           09 00 00 00 (00001001 00000000 00000000 00000000) (9)
     * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * 8     4        (object header)                           98 63 55 11 (10011000 01100011 01010101 00010001) (290808728)
     * 12     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     * 16     8   long Long.value                                100000
     * Instance size: 24 bytes
     * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
     */
    @Test
    void testBoxedSize() {
        Integer i = 1000;
        Long l = 100000L;
        ClassLayout iLayout = ClassLayout.parseInstance(i);
        ClassLayout lLayout = ClassLayout.parseInstance(l);

        System.out.println(iLayout.toPrintable());
        System.out.println(lLayout.toPrintable());
    }
}
