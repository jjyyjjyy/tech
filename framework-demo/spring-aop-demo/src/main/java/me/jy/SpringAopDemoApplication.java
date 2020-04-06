package me.jy;

import me.jy.service.sub.SubDemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author jy
 */
@SpringBootApplication
public class SpringAopDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringAopDemoApplication.class, args);
        SubDemoService demoService = applicationContext.getBean(SubDemoService.class);
        demoService.subHandle(1);
        applicationContext.close();
    }
}
