package me.jy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jy
 */
@RestController
public class DemoController {

    @GetMapping("/")
    public String index() {
        return "Spring Web Demo";
    }
}
