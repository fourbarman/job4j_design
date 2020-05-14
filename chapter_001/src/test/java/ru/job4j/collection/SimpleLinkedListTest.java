package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Test.
 *
 * @author fourbarman (mailto:maks.java@yandex.ru)
 * @version %I%, %G%.
 * @since 14.05.2020.
 */
public class SimpleLinkedListTest {

    /**
     * Test when add than get same element.
     */
    @Test
    public void whenAddThenGet() {
        SimpleLinkedList<String> sll = new SimpleLinkedList<>();
        sll.add("first");
        String rsl = sll.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenHasThatSize() {
        SimpleLinkedList<String> sll = new SimpleLinkedList<>();
        sll.add("first");
        sll.add("second");
        sll.add("third");
        assertThat(sll.getSize(), is(3));
    }

    /**
     * Test when add than call iterator.
     */
    @Test
    public void whenAddThenIt() {
        SimpleLinkedList<String> sll = new SimpleLinkedList<>();
        sll.add("first");
        String rsl = sll.iterator().next();
        assertThat(rsl, is("first"));
    }

    /**
     * Test when get from empty than get IndexOutOfBoundsException.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleLinkedList<String> sll = new SimpleLinkedList<>();
        sll.get(0);
    }

    /**
     * Test when get non existing element than get IndexOutOfBoundsException.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleLinkedList<String> sll = new SimpleLinkedList<>();
        sll.add("first");
        sll.get(1);
    }

    /**
     * Test when get next from empty.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleLinkedList<String> sll = new SimpleLinkedList<>();
        sll.iterator().next();
    }

    /**
     * Test when array changes during iteration than get ConcurrentModificationException.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleLinkedList<String> sll = new SimpleLinkedList<>();
        sll.add("first");
        Iterator<String> it = sll.iterator();
        sll.add("second");
        it.next();
    }
}
