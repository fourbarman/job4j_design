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

    /**
     * Test when delete first is only element than throw NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        SimpleLinkedList<Integer> sll = new SimpleLinkedList<>();
        sll.add(1);
        sll.deleteFirst();
        sll.iterator().next();
    }

    /**
     * Test when delete first element in empty list than throw NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        SimpleLinkedList<Integer> sll = new SimpleLinkedList<>();
        sll.deleteFirst();
    }

    /**
     * Test when delete first element than next becomes first.
     */
    @Test
    public void whenMultiDelete() {
        SimpleLinkedList<Integer> sll = new SimpleLinkedList<>();
        sll.add(1);
        sll.add(2);
        sll.deleteFirst();
        Iterator<Integer> it = sll.iterator();
        assertThat(it.next(), is(2));
    }

    /**
     * Test when delete last, than iterator hasNext is false.
     */
    @Test
    public void whenLastDelete() {
        SimpleLinkedList<Integer> sll = new SimpleLinkedList<>();
        sll.add(1);
        sll.add(2);
        sll.deleteLast();
        Iterator<Integer> it = sll.iterator();
        it.next();
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Test when get first element from empty list than throws NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenGetFirstFromEmpty() {
        SimpleLinkedList<Integer> sll = new SimpleLinkedList<>();
        sll.getFirst();
    }

    /**
     * Test when get first element than return first element.
     */
    @Test
    public void whenGetFirst() {
        SimpleLinkedList<Integer> sll = new SimpleLinkedList<>();
        sll.add(1);
        sll.add(2);
        assertThat(sll.getFirst().data, is(1));
    }

    /**
     * Test when get last element from empty list than throws NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenGetLastFromEmpty() {
        SimpleLinkedList<Integer> sll = new SimpleLinkedList<>();
        sll.getLast();
    }

    /**
     * Test when get last element from list than return last element.
     */
    @Test
    public void whenGetLastWhenHasOne() {
        SimpleLinkedList<Integer> sll = new SimpleLinkedList<>();
        sll.add(1);
        assertThat(sll.getLast().data, is(1));
    }

    /**
     * Test when get last element from list than return last element.
     */
    @Test
    public void whenGetLast() {
        SimpleLinkedList<Integer> sll = new SimpleLinkedList<>();
        sll.add(1);
        sll.add(2);
        assertThat(sll.getLast().data, is(2));
    }

    /**
     * Test when add than iterate.
     */
    @Test
    public void whenAddThenIter() {
        SimpleLinkedList<Integer> linked = new SimpleLinkedList<>();
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    /**
     * Test when add than revert and get reverted list.
     */
    @Test
    public void whenAddAndRevertThenIter() {
        SimpleLinkedList<Integer> linked = new SimpleLinkedList<>();
        linked.add(1);
        linked.add(2);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }
}
