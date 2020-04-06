package me.jy.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jy
 * @date 2018/02/13
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/")
    public String index() {
        return "index\n";
    }

    @GetMapping("/err")
    public String err() {
        throw new RuntimeException("test err\n");
    }

    @GetMapping(path = "/path/{i}")
    public Integer pathVar(@PathVariable Integer i) {
        return i;
    }

}
