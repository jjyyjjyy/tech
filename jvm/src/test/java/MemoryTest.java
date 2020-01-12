/**
 * @author jy
 */
public class MemoryTest {

    private static final int MB = 1024 * 1024;

    /**
     * 对象优先在Eden区分配
     * -Xmx20M -Xms20M -Xmn10M -Xlog:gc+heap=debug -XX:+UseSerialGC
     * <p>
     * [0.102s][info ][gc,heap] GC(0) DefNew: 6674K->1024K(9216K)
     * [0.102s][info ][gc,heap] GC(0) Tenured: 0K->4504K(10240K)
     */
    private static void eden() {
        byte[] b1 = new byte[2 * MB];
        byte[] b2 = new byte[2 * MB];
        byte[] b3 = new byte[2 * MB];
        byte[] b4 = new byte[4 * MB]; // Minor GC
    }

    /**
     * 大对象直接进入老年代
     * -Xmx20M -Xms20M -Xmn10M -Xlog:exit* -XX:+UseSerialGC -XX:PretenureSizeThreshold=3145728
     * <p>
     * [0.094s][info][gc,heap,exit] Heap
     * [0.094s][info][gc,heap,exit]  def new generation   total 9216K, used 2743K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     * [0.094s][info][gc,heap,exit]   eden space 8192K,  33% used [0x00000000fec00000, 0x00000000feeadc58, 0x00000000ff400000)
     * [0.094s][info][gc,heap,exit]   from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
     * [0.094s][info][gc,heap,exit]   to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
     * [0.094s][info][gc,heap,exit]  tenured generation   total 10240K, used 4096K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     * [0.094s][info][gc,heap,exit]    the space 10240K,  40% used [0x00000000ff600000, 0x00000000ffa00010, 0x00000000ffa00200, 0x0000000100000000)
     * [0.094s][info][gc,heap,exit]  Metaspace       used 604K, capacity 4534K, committed 4864K, reserved 1056768K
     * [0.094s][info][gc,heap,exit]   class space    used 54K, capacity 403K, committed 512K, reserved 1048576K
     * [0.094s][debug][gc,heap,exit] Accumulated young generation GC time 0.0000000 secs, 0 GC's, avg GC time 0.0000000
     * [0.094s][debug][gc,heap,exit] Accumulated old generation GC time 0.0000000 secs, 0 GC's, avg GC time 0.0000000
     */
    private static void tenuredImmediately() {
        byte[] b4 = new byte[4 * MB]; // 在老年代分配
    }

    /**
     * 长期存活的对象进入老年代
     * -Xmx20M -Xms20M -Xmn10M -Xlog:gc+heap=debug -XX:+UseSerialGC -XX:MaxTenuringThreshold=1
     * <p>
     * [0.118s][info ][gc,heap] GC(0) DefNew: 6931K->1023K(9216K)
     * [0.118s][info ][gc,heap] GC(0) Tenured: 0K->4760K(10240K)
     * <p>
     * [0.121s][info ][gc,heap] GC(1) DefNew: 5202K->0K(9216K)
     * [0.121s][info ][gc,heap] GC(1) Tenured: 4760K->5784K(10240K)
     * [0.121s][debug][gc,heap] GC(1) Heap after GC invocations=2 (full 0): def new generation   total 9216K, used 0K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     * [0.121s][debug][gc,heap] GC(1)   eden space 8192K,   0% used [0x00000000fec00000, 0x00000000fec00000, 0x00000000ff400000)
     * [0.121s][debug][gc,heap] GC(1)   from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff4002d8, 0x00000000ff500000)
     * [0.121s][debug][gc,heap] GC(1)   to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
     * [0.121s][debug][gc,heap] GC(1)  tenured generation   total 10240K, used 5784K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     * [0.121s][debug][gc,heap] GC(1)    the space 10240K,  56% used [0x00000000ff600000, 0x00000000ffba60b8, 0x00000000ffba6200, 0x0000000100000000)
     * [0.121s][debug][gc,heap] GC(1)  Metaspace       used 660K, capacity 4534K, committed 4864K, reserved 1056768K
     * [0.121s][debug][gc,heap] GC(1)   class space    used 57K, capacity 403K, committed 512K, reserved 1048576K
     */
    private static void tenuredOldPapa() {
        byte[] b1 = new byte[MB / 4];
        byte[] b2 = new byte[4 * MB];
        byte[] b3 = new byte[4 * MB]; // Minor GC 0
        b3 = null;
        b3 = new byte[4 * MB]; // Minor GC 1, b1进入;老年代
    }

    /**
     * Survivor超过半数的对象年龄相同时, 超过该年龄的对象将直接进入老年代
     * -Xmx20M -Xms20M -Xmn10M -Xlog:gc+heap=debug -XX:+UseSerialGC
     * <p>
     * [0.096s][info ][gc,heap] GC(0) DefNew: 7186K->1024K(9216K)
     * [0.097s][info ][gc,heap] GC(0) Tenured: 0K->5020K(10240K)
     * <p>
     * [0.099s][info ][gc,heap] GC(1) DefNew: 5201K->1K(9216K)
     * [0.099s][info ][gc,heap] GC(1) Tenured: 5020K->6041K(10240K)
     */
    private static void tenuredMostly() {
        byte[] b1 = new byte[MB / 4];
        byte[] b2 = new byte[MB / 4];
        byte[] b3 = new byte[4 * MB];
        byte[] b4 = new byte[4 * MB]; // Minor GC 0
        b4 = null;
        b4 = new byte[4 * MB]; // Minor GC 1, b1&b2直接进入老年代
    }

    /**
     * 老年代空间分配担保
     * -Xmx20M -Xms20M -Xmn10M -Xlog:gc+heap=debug -XX:+UseSerialGC
     * <p>
     * [0.104s][info ][gc,heap] GC(0) DefNew: 6671K->1024K(9216K)
     * [0.104s][info ][gc,heap] GC(0) Tenured: 0K->4501K(10240K)
     * <p>
     * [0.109s][debug][gc,heap] GC(1) Heap after GC invocations=2 (full 0): def new generation   total 9216K, used 6389K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     * [0.109s][debug][gc,heap] GC(1)   eden space 8192K,  77% used [0x00000000fec00000, 0x00000000ff23bec8, 0x00000000ff400000)
     * [0.109s][debug][gc,heap] GC(1)   from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff401690, 0x00000000ff500000)
     * [0.109s][debug][gc,heap] GC(1)   to   space 1024K, 100% used [0x00000000ff500000, 0x00000000ff600000, 0x00000000ff600000)
     * [0.109s][debug][gc,heap] GC(1)  tenured generation   total 10240K, used 9620K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     * [0.109s][debug][gc,heap] GC(1)    the space 10240K,  93% used [0x00000000ff600000, 0x00000000fff65088, 0x00000000fff65200, 0x0000000100000000)
     * [0.109s][debug][gc,heap] GC(1)  Metaspace       used 597K, capacity 4537K, committed 4864K, reserved 1056768K
     * [0.109s][debug][gc,heap] GC(1)   class space    used 53K, capacity 403K, committed 512K, reserved 1048576K
     * <p>
     * [0.113s][info ][gc,heap] GC(2) DefNew: 7407K->0K(9216K)
     * [0.113s][info ][gc,heap] GC(2) Tenured: 4501K->9625K(10240K)
     * [0.113s][debug][gc,heap] GC(2) Heap after GC invocations=2 (full 1): def new generation   total 9216K, used 0K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
     * [0.113s][debug][gc,heap] GC(2)   eden space 8192K,   0% used [0x00000000fec00000, 0x00000000fec00000, 0x00000000ff400000)
     * [0.113s][debug][gc,heap] GC(2)   from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
     * [0.113s][debug][gc,heap] GC(2)   to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
     * [0.113s][debug][gc,heap] GC(2)  tenured generation   total 10240K, used 9625K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
     * [0.113s][debug][gc,heap] GC(2)    the space 10240K,  93% used [0x00000000ff600000, 0x00000000fff66608, 0x00000000fff66800, 0x0000000100000000)
     * [0.113s][debug][gc,heap] GC(2)  Metaspace       used 597K, capacity 4537K, committed 4864K, reserved 1056768K
     * [0.113s][debug][gc,heap] GC(2)   class space    used 53K, capacity 403K, committed 512K, reserved 1048576K
     */
    private static void tenuredPromotionFailure() {
        byte[] b1 = new byte[2 * MB];
        byte[] b2 = new byte[2 * MB];
        byte[] b3 = new byte[2 * MB];
        b1 = null;
        byte[] b4 = new byte[2 * MB];
        byte[] b5 = new byte[2 * MB];
        byte[] b6 = new byte[2 * MB];
        b4 = null;
        b5 = null;
        b6 = null;
        byte[] b7 = new byte[2 * MB];
    }

    public static void main(String[] args) {
//        eden();
//        tenuredImmediately();
//        tenuredOldPapa();
//        tenuredMostly();
        tenuredPromotionFailure();
    }
}
