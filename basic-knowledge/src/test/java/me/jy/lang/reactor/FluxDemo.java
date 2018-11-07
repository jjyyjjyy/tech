package me.jy.lang.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;

/**
 * @author jy
 */
@Slf4j
public class FluxDemo {

    @Test
    public void fluxScratch() {

        Flux.range(5, 3).map(String::valueOf).subscribe(log::info);

        Flux.range(10, 5)
            .map(i -> {
                if (i == 12) {
                    throw new RuntimeException();
                }
                return i;
            })
            .map(String::valueOf)
            .subscribe(log::info, e -> log.error(e.toString()), () -> System.out.println("Done"));
    }
}
