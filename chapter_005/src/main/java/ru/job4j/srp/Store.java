package ru.job4j.srp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Store.
 * <p>
 * Interface represents Employee objects store.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 29.09.2021.
 */
public interface Store {

    /**
     * Returns list of Employees from store with filter.
     *
     * @param filter Predicate for filtering objects.
     * @return List of Employees.
     */
    List<Employee> findBy(Predicate<Employee> filter);
}
