package com.example;

import lombok.Data;

/**
 * @author jy
 */
@Data
public class DemoBean {

    public DemoBean() {
        System.out.println("DemoBean init...");
    }

    private Long id;

    private String name;
}
