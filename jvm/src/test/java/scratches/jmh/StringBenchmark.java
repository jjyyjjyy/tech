package scratches.jmh;

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
@Warmup(iterations = 3)
@Measurement(iterations = 5, time = 5)
@Threads(4)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class StringBenchmark {

    private static final int ITERATION = 1000;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(StringBenchmark.class.getSimpleName())
            .resultFormat(ResultFormatType.JSON)
            .build();

        new Runner(opt).run();

    }

    @Benchmark
    public void testStringAdd() {
        String start = "Hello";
        for (int i = 0; i < ITERATION; i++) {
            start += i;
        }
        print(start);
    }

    @Benchmark
    public void testStringBufferAdd() {
        StringBuffer sb = new StringBuffer("Hello");
        for (int i = 0; i < ITERATION; i++) {
            sb.append(i);
        }
        print(sb.toString());
    }

    @Benchmark
    public void testStringBuilderAdd() {
        StringBuilder sb = new StringBuilder("Hello");
        for (int i = 0; i < ITERATION; i++) {
            sb.append(i);
        }
        print(sb.toString());
    }

    private void print(String s) {
        // NO-OP
    }

}
