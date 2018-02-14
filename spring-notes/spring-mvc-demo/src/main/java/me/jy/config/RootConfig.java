package me.jy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jy
 * @date 2018/02/14
 */
@Configuration
@ComponentScan(basePackages = "me.jy", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = RestController.class)})
public class RootConfig {
}
