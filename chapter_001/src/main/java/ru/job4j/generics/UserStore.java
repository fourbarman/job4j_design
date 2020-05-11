package ru.job4j.generics;

/**
 * UserStore.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 11.05.2020.
 */
public class UserStore implements Store<User> {

    private final Store<User> store = new MemStore<>();

    /**
     * Adds element.
     *
     * @param model Model to add.
     */
    @Override
    public void add(User model) {
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
    public boolean replace(String id, User model) {
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
    public User findById(String id) {
        return this.store.findById(id);
    }
}
