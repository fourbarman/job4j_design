package ru.job4j.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * SimpleTree.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 01.06.2020.
 */
public interface SimpleTree<E> {
    /**
     * Adds child value to parent.
     *
     * @param parent E Parent value.
     * @param child  E Child value.
     * @return boolean Result.
     */
    boolean add(E parent, E child);

    /**
     * Searches Node with E value.
     *
     * @param value E value.
     * @return boolean Result.
     */
    Optional<Node<E>> findBy(E value);

    /**
     * Check if tree is binary.
     * @return boolean result.
     */
    public boolean isBinary();

    /**
     * Node.
     *
     * @author fourbarman (maks.java@yandex.ru).
     * @version %I%, %G%.
     * @since 01.06.2020.
     */
    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        /**
         * Constructor.
         *
         * @param value E value.
         */
        public Node(E value) {
            this.value = value;
        }
    }
}
