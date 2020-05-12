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
        int index = indexOf(id);
        if (index != -1) {
            this.mem.set(index, model);
            return true;
        }
        return false;
    }

    /**
     * Removes element from list.
     *
     * @param id Id of deleting element.
     * @return boolean If success.
     */
    @Override
    public boolean delete(String id) {
        int index = indexOf(id);
        if (index != -1) {
            this.mem.remove(indexOf(id));
            return true;
        }
        return false;
    }

    /**
     * Returns element by id.
     *
     * @param id Id of returning element.
     * @return T element.
     */
    @Override
    public T findById(String id) {
        int index = indexOf(id);
        if (index != -1) {
            return this.mem.get(indexOf(id));
        }
        return null;
    }

    /**
     * Returns index of element by id or -1 if not found.
     *
     * @param id Id of the element.
     * @return int Index.
     */
    private int indexOf(String id) {
        for (T t : this.mem) {
            if (t.getId().equals(id)) {
                return this.mem.indexOf(t);
            }
        }
        return -1;
    }
}
