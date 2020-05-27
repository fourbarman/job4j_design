package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author fourbarman (mailto:maks.java@yandex.ru)
 * @version %I%, %G%.
 * @since 26.05.2020.
 */
public class SimpleHashMapTest {
    SimpleHashMap<Integer, String> map;

    /**
     * Set vars.
     */
    @Before
    public void setVar() {
        map = new SimpleHashMap<>();
    }

    /**
     * Test when getSize from empty storage than return 0.
     */
    @Test
    public void whenGetSizeEmptyStorage() {
        assertThat(map.getSize(), is(0));
    }

    /**
     * Test when getSize from storage with 1 element than return 1.
     */
    @Test
    public void whenGetSize() {
        map.insert(1, "one");
        assertThat(map.getSize(), is(1));
    }

    /**
     * Test when when insert second object with equal key than size is the same.
     */
    @Test
    public void whenInsertEqualKeysThanGetSizeIsTheSame() {
        map.insert(1, "one");
        map.insert(1, "another one");
        assertThat(map.getSize(), is(1));
    }

    /**
     * Test when getSize from storage after insert 1 element and delete that element than return 0.
     */
    @Test
    public void whenGetSizeAfterDelete() {
        map.insert(1, "one");
        map.delete(1);
        assertThat(map.getSize(), is(0));
    }

    /**
     * Test when insert one object than return true.
     */
    @Test
    public void whenInsertOneThanSuccess() {
        assertThat(map.insert(1, "one"), is(true));
    }

    /**
     * Test when insert second object with equal key than return true.
     */
    @Test
    public void whenInsertEqualKeysThanFalse() {
        map.insert(1, "one");
        assertThat(map.insert(1, "another one"), is(true));
    }

    /**
     * Test when insert second object with equal key than storage has new value.
     */
    @Test
    public void whenInsertEqualKeysThanHasNewValue() {
        map.insert(1, "one");
        map.insert(1, "another one");
        assertThat(map.get(1), is("another one"));
    }

    /**
     * Test when insert null key than success.
     */
    @Test
    public void whenInsertNullKey() {
        assertThat(map.insert(null, "zero"), is(true));
    }

    /**
     * Test when insert second object with null key than return false.
     */
    @Test
    public void whenInsertTwoNullKeys() {
        map.insert(null, "zero");
        assertThat(map.insert(null, "z"), is(true));
    }

    /**
     * Test when get from empty storage or storage has no such key than return null.
     */
    @Test
    public void whenGetFromEmptyThanReturnNull() {
        assertNull(map.get(1));
    }

    /**
     * Test when insert one object than return that object value.
     */
    @Test
    public void whenInsertOneThanGetValue() {
        map.insert(1, "one");
        assertThat(map.get(1), is("one"));
    }

    /**
     * Test when delete from empty storage than return false.
     */
    @Test
    public void whenDeleteFromEmptyStorageOrKeyNotFound() {
        assertThat(map.delete(3), is(false));
    }

    /**
     * Test when delete than success.
     */
    @Test
    public void whenDeleteThanSuccess() {
        map.insert(3, "three");
        assertThat(map.delete(3), is(true));
    }

    /**
     * Test when delete than storage has no element with such key.
     */
    @Test
    public void whenDeleteThanHasNoSuchKey() {
        map.insert(3, "three");
        map.delete(3);
        assertNull(map.get(3));
    }

    /**
     * Test when multi insert, than multi delete, than multi get.
     */
    @Test
    public void whenMultiInsertAndMultiDelete() {
        assertTrue(map.insert(1, "one"));
        assertTrue(map.insert(2, "two"));
        assertTrue(map.insert(3, "three"));
        assertTrue(map.insert(4, "four"));
        assertTrue(map.insert(5, "five"));
        assertTrue(map.delete(1));
        assertTrue(map.delete(2));
        assertTrue(map.delete(3));
        assertTrue(map.delete(4));
        assertTrue(map.delete(5));
        assertNull(map.get(1));
        assertNull(map.get(2));
        assertNull(map.get(3));
        assertNull(map.get(4));
        assertNull(map.get(5));
    }

    /**
     * Test when iterate through empty storage than throw NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenIteratorInEmpty() {
        Iterator<SimpleHashMap.Node<Integer, String>> it = map.iterator();
        it.next();
    }

    /**
     * Test when only one element than iterator next() return that element.
     */
    @Test
    public void whenInsertOneThanIteratorNext() {
        map.insert(1, "one");
        Iterator<SimpleHashMap.Node<Integer, String>> it = map.iterator();
        assertThat(it.next().getValue(), is("one"));
    }

    /**
     * Test when change storage during iteration.
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenInsertDuringIteration() {
        map.insert(1, "one");
        Iterator<SimpleHashMap.Node<Integer, String>> it = map.iterator();
        it.next();
        map.insert(2, "two");
        it.next();
    }

    /**
     * Test when has only one element than hasNext return false.
     */
    @Test
    public void whenHasNextAfterOne() {
        map.insert(1, "one");
        Iterator<SimpleHashMap.Node<Integer, String>> it = map.iterator();
        it.next();
        assertFalse(it.hasNext());
    }

    /**
     * Test resize.
     */
    @Test
    public void testResize() {
        for (int i = 1; i < 20; i++) {
            assertTrue(map.insert(i, "" + i));
        }
    }
}
