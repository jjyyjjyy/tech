package me.jy.proxy.processor;

import me.jy.proxy.repository.CrudRepository;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.util.Arrays;
import java.util.Set;

/**
 * @author jy
 */
public class CrudRepositoryBeanFactoryPostProcessor implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
            }
        };
        scanner.addIncludeFilter((reader, factory) -> Arrays.asList(reader.getClassMetadata().getInterfaceNames()).contains(CrudRepository.class.getName()));
        Set<BeanDefinition> beanDefinitions = scanner.findCandidateComponents(importingClassMetadata.getAnnotations().get(EnableCrudRepository.class).getString("value"));

        for (BeanDefinition beanDefinition : beanDefinitions) {
            Class type = ClassUtils.resolveClassName(beanDefinition.getBeanClassName(), null);
            CrudRepositoryFactoryBean factoryBean = new CrudRepositoryFactoryBean(type);
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(type, () -> factoryBean.getObject());
            beanDefinitionBuilder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
            beanDefinitionBuilder.setLazyInit(true);
            BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinitionBuilder.getBeanDefinition(), beanDefinition.getBeanClassName());
            BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);

        }
    }
}
