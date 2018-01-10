package me.jy.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jy
 * @date 2018/01/10
 */
public class Directory extends Entry {

    private List<Entry> subEntries = new ArrayList<>();

    public Directory(String name, int size, Entry parentDir) {
        super(name, size, parentDir);
    }

    public void addSubEntry(Entry entry) {
        subEntries.add(entry);
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

}
