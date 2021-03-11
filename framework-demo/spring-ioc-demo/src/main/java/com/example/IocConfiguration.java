package com.example;

import me.jy.bean.DifferentPackageBean;
import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author jy
 */
@Import(DifferentPackageBean.class)
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
