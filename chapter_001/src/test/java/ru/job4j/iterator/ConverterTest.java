package ru.job4j.iterator;

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
 * @since 07.05.2020.
 */
public class ConverterTest {

    Iterator<Integer> it;

    /**
     * Set variables.
     */
    @Before
    public void setUp() {
        Iterator<Integer> it1 = Arrays.asList(1, 2, 3).iterator();
        Iterator<Integer> it2 = Arrays.asList(4, 5, 6).iterator();
        Iterator<Integer> it3 = Arrays.asList(7, 8, 9).iterator();
        Iterator<Iterator<Integer>> its = Arrays.asList(it1, it2, it3).iterator();
        Converter iteratorOfIterators = new Converter();
        it = iteratorOfIterators.convert(its);
    }

    /**
     * Test when has next next sequential invocation.
     */
    @Test
    public void hasNextNextSequentialInvocation() {
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
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(9));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Test that next method doesnt depend on prior has next invocation.
     */
    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(8));
        assertThat(it.next(), is(9));
    }

    /**
     * Test sequential has next invocation doesnt affect retrieval order.
     */
    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(8));
        assertThat(it.next(), is(9));
    }

    /**
     * Test when has next should return false in case of empty iterators.
     */
    @Test
    public void hasNextShouldReturnFalseInCaseOfEmptyIterators() {
        Iterator<Integer> it1 = (new ArrayList<Integer>()).iterator();
        Iterator<Integer> it2 = (new ArrayList<Integer>()).iterator();
        Iterator<Integer> it3 = (new ArrayList<Integer>()).iterator();
        Iterator<Iterator<Integer>> its = Arrays.asList(it1, it2, it3).iterator();
        Converter iteratorOfIterators = new Converter();
        it = iteratorOfIterators.convert(its);
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Test when throw NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void invocationOfNextMethodShouldThrowNoSuchElementException() {
        Iterator<Integer> it1 = Arrays.asList(1, 2, 3).iterator();
        Iterator<Iterator<Integer>> its = Arrays.asList(it1).iterator();
        Converter iteratorOfIterators = new Converter();
        it = iteratorOfIterators.convert(its);
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        it.next();
    }

    /**
     * Test when need skip empty iterators.
     */
    @Test
    public void whenNeedSkipEmptyIterators() {
        Iterator<Integer> it1 = Collections.emptyIterator();
        Iterator<Integer> it2 = Collections.emptyIterator();
        Iterator<Integer> it3 = Arrays.asList(1).iterator();
        Iterator<Iterator<Integer>> its = Arrays.asList(it1, it2, it3).iterator();
        Converter iteratorOfIterators = new Converter();
        it = iteratorOfIterators.convert(its);
        assertThat(it.next(), is(1));
    }
}
