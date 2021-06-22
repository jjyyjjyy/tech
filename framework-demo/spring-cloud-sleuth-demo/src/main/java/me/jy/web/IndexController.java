package me.jy.web;

import lombok.extern.slf4j.Slf4j;
import me.jy.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jy
 */
@Slf4j
@RestController
public class IndexController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/api/v1/index")
    public String getIndex() {
        log.info("index page visited");
        demoService.saying();
        return "Spring Cloud Sleuth Demo Application";
    }
}
