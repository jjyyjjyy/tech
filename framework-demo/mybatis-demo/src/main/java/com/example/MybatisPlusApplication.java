package com.example;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.example.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author jy
 */
@MapperScan("com.example.mapper")
@SpringBootApplication
public class MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public CommandLineRunner clr(UserMapper userMapper) {
        return args -> {
            System.out.println(userMapper.findById(1, "wow"));
        };
    }
}
