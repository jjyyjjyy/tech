package me.jy.service;

import org.springframework.stereotype.Service;

/**
 * @author jy
 */
@Service
public class DemoService {

    public void handle(int i) {
        System.out.println(i);
    }
}
