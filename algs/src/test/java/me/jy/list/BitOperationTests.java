package me.jy.list;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author jy
 */
@BenchmarkMode(Mode.Throughput)
@Fork(1)
@Threads(4)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 3)
@Warmup(iterations = 3)
public class BitOperationTests {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(BitOperationTests.class.getSimpleName())
            .resultFormat(ResultFormatType.JSON)
            .build();

        new Runner(opt).run();

    }

    @Benchmark
    public void testModOrigin() {
        int[] integers = new int[]{2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};

        for (int i : integers) {
            take(i % 8);
        }

    }

    @Benchmark
    public void testModBit() {
        int[] integers = new int[]{2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};

        for (int i : integers) {
            take(i & 7);
        }
    }

    private void take(int i) {
        // NO-OP
    }
}
