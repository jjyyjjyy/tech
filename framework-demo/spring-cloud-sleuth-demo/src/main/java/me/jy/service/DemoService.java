package me.jy.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Service;

/**
 * @author jy
 */
@Slf4j
@Service
public class DemoService {

    @NewSpan("saying")
    public void saying() {
        log.info("DemoService saying ...");
    }
}
