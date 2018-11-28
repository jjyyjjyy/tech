package me.jy._6_builder;

import lombok.Builder;

import java.time.Instant;

/**
 * @author jy
 */
@Builder
public class Product {

    private Long id;
    private String name;
    private Instant createdAt;

    public static void main(String[] args) {
        new Product.ProductBuilder().id(1L).name("pro").createdAt(Instant.now()).build();
    }
}
