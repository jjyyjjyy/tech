package me.jy.composite;

import java.util.LinkedList;

/**
 * @author jy
 * @date 2018/01/10
 */
public abstract class Entry {

    private final String name;
    private final int size;
    private final Entry parentDir;

    private static final String PATH_SEPARATOR = "/";

    public Entry(String name, int size, Entry parentDir) {
        this.name = name;
        this.size = size;
        this.parentDir = parentDir;
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return name;
    }

    public String pwd() {
        Entry parent = this.parentDir;
        if (parent == null) {
            return PATH_SEPARATOR;
        }
        LinkedList<Entry> parentDirs = new LinkedList<>();
        while (parent != null) {
            parentDirs.addFirst(parent);
            parent = parent.parentDir;
        }

        StringBuilder path = new StringBuilder(PATH_SEPARATOR);
        parentDirs
                .forEach(dir -> path.append(dir.toString()).append(PATH_SEPARATOR));
        path.append(this.name);
        if (this.isDirectory()) {
            path.append(PATH_SEPARATOR);
        }
        return path.substring(2);
    }

    public abstract boolean isDirectory();
}
