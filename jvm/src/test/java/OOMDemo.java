import java.util.ArrayList;
import java.util.List;

/**
 * @author jy
 */
public class OOMDemo {

    private static void heapOOM() {
        List<HugeClass> classes = new ArrayList<>();
        while (true) {
            classes.add(new HugeClass());
        }
    }

    public static void main(String[] args) {
        heapOOM();
    }


    private static class HugeClass {
        private byte[] bytes = new byte[1 * 1024 * 1024];
    }
}
