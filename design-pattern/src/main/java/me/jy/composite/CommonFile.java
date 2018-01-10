package me.jy.composite;

/**
 * @author jy
 * @date 2018/01/10
 */
public class CommonFile extends Entry {

    public CommonFile(String name, int size, Entry parentDir) {
        super(name, size, parentDir);
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

}
