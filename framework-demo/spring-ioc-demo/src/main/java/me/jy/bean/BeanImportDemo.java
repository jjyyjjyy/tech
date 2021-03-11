package me.jy.bean;

import lombok.Data;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author jy
 */
@SpringBootApplication
public class BeanImportDemo {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BeanImportDemo.class, args);
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanAutoRegisterConfiguration.class);
        System.out.println(context.getBeansOfType(Universe.class));
    }


    @Import({Universe.class, DemoImportSelector.class, OuterConfiguration.class, DemoBeanDefinitionRegistrar.class})
    @Configuration
    public static class BeanAutoRegisterConfiguration {
    }

    public static class DemoBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
            registry.registerBeanDefinition("ImportBeanDefinitionRegistrarUniverse", new RootBeanDefinition(Universe.class));
        }
    }

    public static class DemoImportSelector implements ImportSelector {
        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            return new String[]{ImportUniverse.class.getName()};
        }
    }

    @Configuration
    public static class OuterConfiguration {
        @Bean
        public Universe outerUniverse() {
            return new Universe().setName("OuterUniverse");
        }
    }

    @Data
    public static class Universe {
        private String name;
    }

    public static class ImportUniverse extends Universe {
    }
}
