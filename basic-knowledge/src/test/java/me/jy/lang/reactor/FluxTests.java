package me.jy.lang.reactor;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.*;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author jy
 */
@Slf4j
class FluxTests {

    @Test
    void testFluxLog() {

        // The core difference is that Reactive is a push model, whereas the Java 8 Streams are a pull model.
        // In reactive approach. events are pushed to the subscribers as they come in.
        List<Integer> list = new ArrayList<>();
        Flux.just(1, 1, 2, 3)
            .log()
//            .subscribe(list::add)
            .subscribe(new Subscriber<Integer>() {
                @Override
                public void onSubscribe(Subscription s) {
                    s.request(Long.MAX_VALUE);
                }

                @Override
                public void onNext(Integer integer) {
                    list.add(integer);
                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onComplete() {

                }
            });

        Assertions.assertTrue(list.containsAll(Arrays.asList(1, 2, 3, 1)));

    }

    @Test
    void testMono() {
        Mono.empty().log().subscribe(System.out::println);
    }

    @Test
    void testError() {
        Flux.range(5, 3)
            .map(i -> {
                if (i < 7) {
                    return i;
                }
                throw new RuntimeException(i + "");
            })
            .log()
            .subscribe(System.out::println, System.err::println, () -> System.out.println("Done"), sub -> sub.request(1));
    }

    @Test
    void testSubscribeCustom() {
        Flux.range(5, 3)
            .log()
            .limitRequest(1)
            .subscribe(new SampleSubscriber<>());
    }

    @Test
    void testGenerate() {

        // infinite sequence
        List<Integer> list = new ArrayList<>();
        Flux.<Integer>generate(sink -> sink.next(1))
            .log()
            .limitRequest(10)
            .subscribe(list::add);
        assertEquals(10, list.size());
        list.forEach(i -> assertEquals(1, (int) i));
        list.clear();

        Flux.generate(() -> 0, (state, sink) -> {
            sink.next(state++);
            if (state == 10) {
                sink.complete();
            }
            return state;
        })
            .subscribe(i -> list.add((int) i));
        assertEquals(10, list.size());
        for (int i = 0; i < list.size(); i++) {
            assertEquals(i, (int) list.get(i));
        }
    }

    @Test
    void testCreate() throws InterruptedException {

        /*StateGenerator stateGenerator = new StateGenerator();

        Flux<Object> flux = Flux.generate(stateGenerator::setSink);

        new Thread(() -> Stream.generate(() -> 1)
            .parallel()
            .peek(i -> {
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            })
            .forEach(i -> stateGenerator.createState())).start();

        TimeUnit.SECONDS.sleep(2L);

        flux.subscribe(System.out::println); // FIXME*/

    }

    @Test
    void testZip() {
        Flux.fromIterable(Arrays.asList("a", "b", "c"))
            .zipWith(Flux.range(1, 30), (e, l) -> l + ". " + e)
            .subscribe(System.out::println);

        Flux.just("a", "fdasf", "sa", "ff")
            .flatMap(s -> Flux.fromArray(s.split("")))
            .distinct()
            .subscribe(System.out::println);
    }

    @Test
    void testInterval() throws InterruptedException {
        Flux.interval(Duration.ofSeconds(1L))
            .map(i -> "fast " + i)
            .log()
            .subscribe(System.out::println);
        Flux.interval(Duration.ofSeconds(2L))
            .map(i -> "slow " + i)
            .log()
            .subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(100L);
    }

    @Test
    void testHandle() {
        assertThrows(UnsupportedOperationException.class, () -> Flux.just(1, 2, 3, null).subscribe(System.out::println));

        List<Integer> list = new ArrayList<>();
        Flux.just(1, 2, 3, 4)
            .<Integer>handle((i, sink) -> {
                if (i > 3) {
                    i = null;
                }
                if (i != null) {
                    sink.next(i);
                }
            })
            .subscribe(list::add);
        assertEquals(3, list.size());
    }

    @Test
    void testConcurrency() {

        List<Integer> list = new ArrayList<>();
        Flux.range(1, 10)
            .subscribeOn(Schedulers.immediate())
            .filter(i -> "main".equals(Thread.currentThread().getName()))
            .subscribe(list::add);
        assertEquals(10, list.size());

    }

    @Test
    void testHotFlux() {

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        ConnectableFlux<Integer> publish = Flux.just(1, 2, 3, 4, 5, 50).publish();
        publish.subscribe(l1::add);
        publish.subscribe(l2::add);

        publish.connect();

        for (int i = 0; i < l1.size(); i++) {
            assertEquals(l1.get(i), l2.get(i));
        }

        List<Integer> lateList = new ArrayList<>();
        publish.subscribe(lateList::add);

        assertTrue(lateList.size() <= l1.size());

    }

    @Setter
    private static class StateGenerator {

        private SynchronousSink<Object> sink;

        private void createState() {
            int t = ThreadLocalRandom.current().nextInt(2020);
            if (sink != null) {
                sink.next(t);
                if (t > 2010) {
                    sink.complete();
                }
            }
        }
    }

    private static class SampleSubscriber<T> extends BaseSubscriber<T> {
        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            log.debug("hookOnSubscribe");
            request(Long.MAX_VALUE);
        }

        @Override
        protected void hookOnNext(T value) {
            log.debug("hookOnNext: {}", value);
        }

    }
}
