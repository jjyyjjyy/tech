package com.example;

import lombok.Data;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author jy
 */
public class IocDemoApplication {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(IocConfiguration.class);

        // by name
        System.out.println(applicationContext.getBean("demoBean", DemoBean.class));
        // by type
        System.out.println(applicationContext.getBean(DemoBean.class));
        // list all candidate beans by type
        System.out.println(applicationContext.getBeansOfType(DemoBean.class));
        // by annotation
        System.out.println(applicationContext.getBeansWithAnnotation(Data.class));
        // via ObjectFactory(lazy)
        System.out.println(applicationContext.getBean("demoBeanObjectFactory", ObjectFactory.class).getObject());

    }

}
