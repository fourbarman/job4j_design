package ru.job4j.collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Test.
 *
 * @author fourbarman (mailto:maks.java@yandex.ru)
 * @version %I%, %G%.
 * @since 13.05.2020.
 */
public class SimpleArrayTest {
    /**
     * Test when add than get same element.
     */
    @Test
    public void whenAddThenGet() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    /**
     * Test when add than call iterator.
     */
    @Test
    public void whenAddThenIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    /**
     * Test when get from empty than get IndexOutOfBoundsException.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleArray<String> array = new SimpleArray<>();
        array.get(0);
    }

    /**
     * Test when get non existing element than get IndexOutOfBoundsException.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        array.get(1);
    }

    /**
     * Test when get next from empty.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.iterator().next();
    }

    /**
     * Test when array changes during iteration than get ConcurrentModificationException.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }

}
