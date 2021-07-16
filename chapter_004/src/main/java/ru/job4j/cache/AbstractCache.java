package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * AbstractCache.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 07.07.2021.
 */
public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    /**
     * @param key   Filename.
     * @param value Value from file.
     */
    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    /**
     * Return text from cache.
     * If cache doesn't contain file - load file to cache.
     *
     * @param key File name.
     * @return Text from file.
     */
    public V get(K key) {
        V value = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (value == null) {
            return this.load(key);
        }
        return value;
    }

    /**
     * Load file to cache.
     *
     * @param key File name.
     * @return Text from file.
     */
    protected abstract V load(K key);

}
