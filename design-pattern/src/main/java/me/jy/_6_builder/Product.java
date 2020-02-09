package me.jy._6_builder;

import java.time.Instant;

/**
 * @author jy
 */
public class Product {

    private Long id;
    private String name;
    private Instant createdAt;

    public static class ProductBuilder{

        private Long id;
        private String name;
        private Instant createdAt;

        public ProductBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.id = this.id;
            product.name = this.name;
            product.createdAt = this.createdAt;
            return product;
        }
    }

    public static void main(String[] args) {
        new ProductBuilder().id(1L).name("pro").createdAt(Instant.now()).build();
    }
}
