package me.jy.proxy;

import me.jy.proxy.domain.User;
import me.jy.proxy.processor.EnableCrudRepository;
import me.jy.proxy.stub.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

/**
 * @author jy
 */
@EnableCrudRepository("me.jy.proxy.stub")
@SpringBootApplication
public class DemoProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProxyApplication.class, args);
    }

    @Bean
    public CommandLineRunner clr(UserRepository userRepository) {
        return args -> {
            System.out.println(userRepository.get(1L));
            userRepository.save(new User().setId(2L).setUsername(UUID.randomUUID().toString()));
        };
    }
}
