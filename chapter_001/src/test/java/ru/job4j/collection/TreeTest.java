package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author fourbarman (mailto:maks.java@yandex.ru)
 * @version %I%, %G%.
 * @since 13.05.2020.
 */
public class TreeTest {
    /**
     * Test when 6 elements added, than can find 6th element.
     */
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    /**
     * Test when not found element than return empty option.
     */
    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    /**
     * Test when add new element that Tree already has than return false.
     */
    @Test
    public void whenAddEqualElementThanFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        assertThat(
                tree.add(1, 3),
                is(false)
        );
    }
    /**
     * Test when tree is binary.
     */
    @Test
    public void whenTreeIsBinaryThanReturnTrue() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 3);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.isBinary(),
                is(true)
        );
    }
    /**
     * Test when tree is not binary.
     */
    @Test
    public void whenTreeIsNotBinary() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.isBinary(),
                is(false)
        );
    }
}
