package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * MemStore.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 11.05.2020.
 */
public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    /**
     * Adds element.
     *
     * @param model Model to add.
     */
    @Override
    public void add(T model) {
        this.mem.add(model);
    }

    /**
     * Replaces element in list with new element by id.
     *
     * @param id    Id of element to replace.
     * @param model Replacing element.
     * @return boolean If success.
     */
    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        for (T t : this.mem
        ) {
            if (t != null && t.getId().equals(id)) {
                this.mem.set(this.mem.indexOf(t), model);
                result = true;
            }
        }
        return result;
    }

    /**
     * Removes element from list.
     *
     * @param id Id of deleting element.
     * @return boolean If success.
     */
    @Override
    public boolean delete(String id) {
        return this.mem.removeIf(x -> x.getId().equals(id));
    }

    /**
     * Returns element by id.
     *
     * @param id Id of returning element.
     * @return T element.
     */
    @Override
    public T findById(String id) {
        return this.mem.stream()
                .filter(x -> x.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
