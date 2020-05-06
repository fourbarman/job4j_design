package ru.job4j.iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.Before;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Test.
 *
 * @author fourbarman (mailto:maks.java@yandex.ru)
 * @version %I%, %G%.
 * @since 06.05.2020.
 */
public class EvenItTest {
    private Iterator<Integer> it;

    /**
     * Set variables.
     */
    @Before
    public void setUp() {
        it = new EvenIt(new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    /**
     * Test when throw NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    /**
     * Test when sequential hasNext invocation doesnt affect retrieval order.
     */
    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(6));
    }

    /**
     * Test when return false if has no any even elements.
     */
    @Test
    public void shouldReturnFalseIfNoAnyEvenNumbers() {
        it = new EvenIt(new int[]{1});
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Test when all numbers are even.
     */
    @Test
    public void allNumbersAreEven() {
        it = new EvenIt(new int[]{2, 4, 6, 8});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
    }
}
