package com.example;

import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jy
 */
@Configuration
public class IocConfiguration {

    @Bean
    public DemoBean demoBean() {
        return new DemoBean().setId(1L).setName("First");
    }

    @Bean
    public ObjectFactoryCreatingFactoryBean demoBeanObjectFactory() {
        ObjectFactoryCreatingFactoryBean factoryBean = new ObjectFactoryCreatingFactoryBean();
        factoryBean.setTargetBeanName("demoBean");
        return factoryBean;
    }
}
