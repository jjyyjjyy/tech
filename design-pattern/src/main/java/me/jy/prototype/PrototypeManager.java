package me.jy.prototype;

/**
 * @author jy
 * @date 2018/01/09
 */
public class PrototypeManager<T extends Product> {

    private final T target;

    public PrototypeManager(T target) {
        this.target = target;
    }

    public Object create() {
        return target.clone();
    }
}
