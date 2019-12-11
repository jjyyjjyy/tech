package me.jy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jy
 * @date 2018/02/13
 */
@ComponentScan("me.jy.web")
@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
}
