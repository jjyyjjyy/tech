package me.jy.proxy.processor;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author jy
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(CrudRepositoryBeanFactoryPostProcessor.class)
public @interface EnableCrudRepository {

    String value();
}
