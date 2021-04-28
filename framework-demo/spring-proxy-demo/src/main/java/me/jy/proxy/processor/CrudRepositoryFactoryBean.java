package me.jy.proxy.processor;

import me.jy.proxy.domain.User;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * @author jy
 */
public class CrudRepositoryFactoryBean implements FactoryBean<Object> {

    private final Class<?> type;

    public CrudRepositoryFactoryBean(Class<?> type) {
        this.type = type;
    }

    @Override
    public Object getObject() {
        return Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, (proxy, method, args) -> {
            switch (method.getName()) {
                case "save":
                    System.out.println("save " + args[0]);
                    return Void.class;
                case "get":
                    System.out.println("get " + args[0]);
                    return new User().setId((Long) args[0]).setUsername(UUID.randomUUID().toString());
                default:
                    return method.invoke(this, args);
            }
        });
    }

    @Override
    public Class<?> getObjectType() {
        return type;
    }
}
