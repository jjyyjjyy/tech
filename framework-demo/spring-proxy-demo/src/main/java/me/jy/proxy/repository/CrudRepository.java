package me.jy.proxy.repository;

/**
 * @author jy
 */
public interface CrudRepository<T, K> {

    T get(K id);

    void save(T t);

}
