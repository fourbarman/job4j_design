package ru.job4j.generics;

/**
 * RoleStore.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 11.05.2020.
 */
public class RoleStore implements Store<Role> {

    private final Store<Role> store = new MemStore<>();

    /**
     * Adds element.
     *
     * @param model Model to add.
     */
    @Override
    public void add(Role model) {
        this.store.add(model);
    }

    /**
     * Replaces element in list with new element by id.
     *
     * @param id    Id of element to replace.
     * @param model Replacing element.
     * @return boolean If success.
     */
    @Override
    public boolean replace(String id, Role model) {
        return this.store.replace(id, model);
    }

    /**
     * Removes element from list.
     *
     * @param id Id of deleting element.
     * @return boolean If success.
     */
    @Override
    public boolean delete(String id) {
        return this.store.delete(id);
    }

    /**
     * Returns element by id.
     *
     * @param id Id of returning element.
     * @return T element.
     */
    @Override
    public Role findById(String id) {
        return this.store.findById(id);
    }
}
