package me.jy.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author jy
 */
@SpringBootApplication
public class BeanLifeCycleApplicationDemo {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BeanLifeCycleApplicationDemo.class, args);
        LifeCycleBean bean = applicationContext.getBean(LifeCycleBean.class);
        System.out.println(bean.getClass());
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public LifeCycleBean lifeCycleBean() {
        return new LifeCycleBean();
    }

    @Bean
    public BeanPostProcessor lifecycleBeanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                if (LifeCycleBean.class.isAssignableFrom(bean.getClass())) {
                    System.out.println("BeanPostProcessor::postProcessBeforeInitialization");
                }
                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (LifeCycleBean.class.isAssignableFrom(bean.getClass())) {
                    System.out.println("BeanPostProcessor::postProcessAfterInitialization");
                }
                return bean;
            }
        };
    }

    private static class LifeCycleBean implements InitializingBean, DisposableBean, Lifecycle , SmartInitializingSingleton {

        private volatile boolean running;

        public LifeCycleBean() {
            System.out.println("Constructor");
        }

        @PostConstruct
        public void postConstruct() {
            System.out.println("PostConstruct");
        }

        @PreDestroy
        public void preDestroy() {
            System.out.println("PreDestroy");
        }

        public void initMethod() {
            System.out.println("initMethod");
        }

        public void destroyMethod() {
            System.out.println("destroyMethod");
        }

        @Override
        public void destroy() throws Exception {
            System.out.println("DisposableBean::destroy");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            System.out.println("InitializingBean::afterPropertiesSet");
        }

        @Override
        public void start() {
            System.out.println("Lifecycle::start");
            running = true;
        }

        @Override
        public void stop() {
            System.out.println("Lifecycle::stop");
        }

        @Override
        public boolean isRunning() {
            return running;
        }

        @Override
        public void afterSingletonsInstantiated() {
            System.out.println("SmartInitializingSingleton::afterSingletonsInstantiated");
        }
    }
}
