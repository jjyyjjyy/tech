package scratches;

/**
 * @author jy
 */
public class DataTypeTest {

    public static void main(String[] args) {
        float sn1 = 0.0f / 0.0f;
        float f1 = 0.0f;
        float sn2 = sn1 / f1;
        float sn3 = 0xff800001;
        System.out.println("sn1 = " + Integer.toHexString(Float.floatToRawIntBits(sn1)));
        System.out.println("sn2 = " + Integer.toHexString(Float.floatToRawIntBits(sn2)));
        System.out.println("sn2 = " + sn3);
        System.out.println("NaN!=NaN : " + (Float.NaN != Float.NaN));
    }
}
