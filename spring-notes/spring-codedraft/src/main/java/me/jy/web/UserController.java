package me.jy.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jy
 */
@RestController
public class UserController {

    @GetMapping("/users")
    public String getUsers() {
        return "jy";
    }
}
