package me.jy.table;

import java.util.*;

/**
 * 分离链接法散列表
 *
 * @author jy
 * @date 2018/02/02
 */
@SuppressWarnings("unchecked")
public class SelfHashTable<K, V> extends AbstractMap<K, V> {

    private List<Entry<K, V>>[] table;

    private int tableSize = 16;

    private double loadFactor = 0.75;

    private int elementSize;

    private Set<Map.Entry<K, V>> cachedEntries = new HashSet<>();

    public SelfHashTable() {
        this.table = new LinkedList[tableSize];
    }

    public SelfHashTable(int tableSize) {
        this.tableSize = tableSize;
    }

    public SelfHashTable(int tableSize, double loadFactor) {
        this.tableSize = tableSize;
        this.loadFactor = loadFactor;
    }


    public V put(K k, V v) {

        int tableIndex = getTableIndex(k);
        List<Entry<K, V>> table = this.table[tableIndex];
        if (table == null) {
            table = new LinkedList<>();
            this.table[tableIndex] = table;

        }
        boolean isKeyExists = false;
        for (Entry<K, V> kvEntry : table) {
            if (Objects.equals(kvEntry.getKey(), k)) {
                kvEntry.setValue(v);
                isKeyExists = true;
                break;
            }
        }
        if (!isKeyExists) {
            table.add(new SimpleEntry<>(k, v));
            elementSize++;
        }

        if (elementSize * loadFactor > tableSize) {
            resize();
        }
        return v;
    }

    private void resize() {
        int newSize = this.tableSize << 1;
        List<Entry<K, V>>[] table = new LinkedList[newSize];
        entrySet()
                .parallelStream()
                .forEach(entry -> {
                    int hashCode = entry.getKey().hashCode();
                    int index = hashCode % newSize;
                    List<Entry<K, V>> entryList = table[index];
                    if (entryList == null) {
                        entryList = new LinkedList<>();
                        table[index] = entryList;
                    }
                    entryList.add(entry);
                });

        this.tableSize = newSize;
        this.table = table;
    }

    private int getTableIndex(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("Fuck off!");
        }
        return key.hashCode() % tableSize;
    }

    public V get(Object key) {
        int tableIndex = getTableIndex(key);
        List<Entry<K, V>> entries = this.table[tableIndex];
        if (entries == null || entries.isEmpty()) {
            return null;
        }
        Optional<Entry<K, V>> first = entries
                .parallelStream()
                .filter(entry -> Objects.equals(entry.getKey(), key))
                .findFirst();
        return first.map(Entry::getValue).orElse(null);

    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        if (cachedEntries.size() != elementSize) {
            cachedEntries.clear();
            for (List<Entry<K, V>> entryList : table) {
                if (entryList != null) {
                    entryList.stream().filter(Objects::nonNull)
                            .forEach(cachedEntries::add);
                }
            }
        }
        return cachedEntries;
    }

    public static void main(String[] args) {
        Map<String, Integer> test = new SelfHashTable<>();
        for (int i = 0; i < 100; i++) {
            test.put(String.valueOf((char) ('a' + i)), i);
        }
        System.out.println(test.entrySet());
    }
}
