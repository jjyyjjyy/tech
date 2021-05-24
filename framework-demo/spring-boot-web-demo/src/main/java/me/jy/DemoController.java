package me.jy;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jy
 */
@RestController
public class DemoController {

    @GetMapping("/")
    public String demoMethod() {
        stubA();
        stubB();
        stubC();
        return "Spring Web Demo";
    }

    public void stubA() {

    }

    @SneakyThrows
    public void stubB() {
        System.out.println("Method stubB Disabled");
//        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextLong(3));
    }

    public void stubC() {

    }

    @GetMapping("/check")
    public boolean check() {
        // remote check...
        return false;
    }

}
