package me.jy.lang.nio;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author jy
 */
class BufferTests {

    @Test
    void testBufferAPI() {
        IntBuffer buffer = IntBuffer.allocate(100);
        assertEquals(0, buffer.position());
        assertEquals(100, buffer.limit());
        assertEquals(100, buffer.capacity());

        int[] arr = new int[100];
        IntBuffer bufferFromArray = IntBuffer.wrap(arr);
        assertEquals(0, bufferFromArray.position());
        assertEquals(100, bufferFromArray.limit());
        assertEquals(100, bufferFromArray.capacity());

        IntBuffer bufferFromArray2 = IntBuffer.wrap(arr, 12, 40);
        assertEquals(12, bufferFromArray2.position());
        assertEquals(52, bufferFromArray2.limit());
        assertEquals(100, bufferFromArray2.capacity());

        assertEquals(ByteOrder.LITTLE_ENDIAN, ByteOrder.nativeOrder()); // Intel CPU
        assertEquals(ByteOrder.BIG_ENDIAN, ByteBuffer.allocate(0).order()); // ByteBuffer默认为大端

    }
}
