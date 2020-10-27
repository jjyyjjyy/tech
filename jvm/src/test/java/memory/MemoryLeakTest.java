package memory;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jy
 */
public class MemoryLeakTest {

    @Test
    void testMemoryLeak() {
        Map<LongWrapper, Long> map = new HashMap<>();
        while (true) {
            for (long i = 0; i < 1000000; i++) {
                map.put(new LongWrapper(i), Instant.now().getEpochSecond());
            }
        }
    }

    public static class LongWrapper {

        private final Long id;

        public LongWrapper(Long id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }
    }
}
