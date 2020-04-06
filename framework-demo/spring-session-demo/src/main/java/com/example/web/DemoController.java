package com.example.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author jy
 */
@RestController
public class DemoController {

    @GetMapping("/")
    public String index(@Value("${spring.application.name}") String name, HttpSession session) {

        return name;
    }
}
