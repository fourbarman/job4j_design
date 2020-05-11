package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author fourbarman (mailto:maks.java@yandex.ru)
 * @version %I%, %G%.
 * @since 08.05.2020.
 */
public class SimpleArrayTest {
    public SimpleArray<Integer> simpleArray;

    /**
     * Set variables.
     */
    @Before
    public void setVar() {
        simpleArray = new SimpleArray<>(10);
    }

    /**
     * Test when has no elements.
     */
    @Test
    public void whenHasNoElements() {
        assertThat(simpleArray.toString(), is("[]"));
    }

    /**
     * Test add when add one element than array has one element.
     */
    @Test
    public void whenAddOneElementThanHasOneElement() {
        simpleArray.add(1);
        assertThat(simpleArray.toString(), is("[1]"));
    }

    /**
     * Test add when add five elements than array has five elements.
     */
    @Test
    public void whenAddFiveElementsThanHasFiveElements() {
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(5);
        assertThat(simpleArray.toString(), is("[1, 2, 3, 4, 5]"));
    }

    /**
     * Test get when add one element than get the same element.
     */
    @Test
    public void whenAddOneElementThanReturnOneElement() {
        simpleArray.add(1);
        assertThat(simpleArray.get(0), is(1));
    }

    /**
     * Test get when no elements in array than throw NoSuchElementException.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenHasNoElementsThanThrowNoSuchElementException() {
        simpleArray.get(0);
    }

    /**
     * Test get when add multiple elements than get same elements.
     */
    @Test
    public void whenHasMultipleElementsThanReturn() {
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(5);
        assertThat(simpleArray.get(0), is(1));
        assertThat(simpleArray.get(1), is(2));
        assertThat(simpleArray.get(2), is(3));
        assertThat(simpleArray.get(3), is(4));
        assertThat(simpleArray.get(4), is(5));
    }

    /**
     * Test when set new element to empty array than throw ArrayStoreException.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetElementToEmptyArray() {
        simpleArray.set(0, 1);
    }

    /**
     * Test when set new element than array has that element.
     */
    @Test
    public void whenSetOneElementThanHasThatElementInArray() {
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(5);
        simpleArray.set(2, 123);
        assertThat(simpleArray.get(2), is(123));
    }

    /**
     * Test when set new element out of index than throw ArrayStoreException.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetElementWithIndexMoreArrayHasElementsThanArrayStoreException() {
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(5);
        simpleArray.set(5, 123);
    }

    /**
     * Test when remove element from empty array than throw ArrayStoreException.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveFromEmptyArrayThanArrayStoreException() {
        simpleArray.remove(0);
    }

    /**
     * Test when remove element from array than array has next element on its index.
     */
    @Test
    public void whenRemoveOneElementThanHasNextElementOnItsIndex() {
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(5);
        simpleArray.remove(2);
        assertThat(simpleArray.get(2), is(4));
    }

    /**
     * Test when remove element from array than array length is less by one element.
     */
    @Test
    public void whenRemoveOneElementThanArrayHasLessLength() {
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(5);
        simpleArray.remove(2);
        assertThat(simpleArray.toString(), is("[1, 2, 4, 5]"));
    }

    /**
     * Test when has next next sequential invocation.
     */
    @Test
    public void hasNextNextSequentialInvocation() {
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(5);
        Iterator<Integer> it = simpleArray.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Test that next method doesnt depend on prior has next invocation.
     */
    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(5);
        Iterator<Integer> it = simpleArray.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
    }

    /**
     * Test when throw NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void invocationOfNextMethodShouldThrowNoSuchElementException() {
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(5);
        Iterator<Integer> it = simpleArray.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        it.next();
    }
}
