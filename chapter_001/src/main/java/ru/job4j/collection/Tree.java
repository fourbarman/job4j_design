package ru.job4j.collection;

import java.util.*;

/**
 * Tree.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 01.06.2020.
 */
class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    /**
     * Constructor.
     *
     * @param root Root.
     */
    Tree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Adds child value to parent.
     *
     * @param parent E Parent value.
     * @param child  E Child value.
     * @return boolean Result.
     */
    @Override
    public boolean add(E parent, E child) {
        Node<E> node = findBy(parent).orElse(null);
        if (node != null) {
            for (Node<E> n : node.children) {
                if (n.value.equals(child)) {
                    return false;
                }
            }
            node.children.add(new Node<E>(child));
            return true;
        }
        return false;
    }

    /**
     * Searches Node with E value.
     *
     * @param value E value.
     * @return boolean Result.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
    /**
     * Checks if tree is binary.
     *
     * @return boolean Result.
     */
    @Override
    public boolean isBinary() {
        boolean result = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> node = data.poll();
            if (node.children.size() > 2) {
                result = false;
                break;
            }
        }
        return result;
    }
}
