package hash;

public class HashMap01<K, V> implements Map<K, V> {
    private final Object[] tab = new Object[8];

    @Override
    public void put(K key, V value) {
        int idx = key.hashCode() & (tab.length - 1);
        tab[idx] = value;
    }

    @Override
    public V get(K key) {
        int idx = key.hashCode() & (tab.length - 1);
        return (V) tab[idx];
    }
}
