package me.jy.table;

import java.util.*;

/**
 * 链地址法散列表
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

        List<Entry<K, V>> table = getTable(k);
        if (table == null) {
            table = new ArrayList<>();
        }
        table.add(new SimpleEntry<>(k, v));
        elementSize++;
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
                    List<Entry<K, V>> entryList = table[newSize % hashCode];
                    if (entryList == null) {
                        entryList = new LinkedList<>();
                    }
                    entryList.add(entry);
                });

        this.tableSize = newSize;
        this.table = table;
    }

    private List<Entry<K, V>> getTable(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("Fuck off!");
        }
        return this.table[key.hashCode() % tableSize];
    }

    public V get(Object key) {
        List<Entry<K, V>> entries = getTable(key);
        if (entries == null || entries.isEmpty()) {
            return null;
        }
        Optional<Entry<K, V>> first = entries
                .parallelStream()
                .filter(entry -> Objects.equals(entry.getKey(), key))
                .findFirst();
        if (first.isPresent()) {
            return first.get().getValue();
        }
        return null;

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
            test.put("test", i);
        }
        System.out.println(test.entrySet());
    }
}
