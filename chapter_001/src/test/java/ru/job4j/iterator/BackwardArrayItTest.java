package ru.job4j.iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * Test.
 *
 * @author fourbarman (mailto:maks.java@yandex.ru)
 * @version %I%, %G%.
 * @since 03.05.2020.
 */
public class BackwardArrayItTest {
    /**
     * Test when call has next.
     */
    @Test
    public void whenMultiCallHasNextThenTrue() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[]{1, 2, 3}
        );
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    /**
     * Test when read sequence.
     */
    @Test
    public void whenReadSequence() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[]{1, 2, 3}
        );
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    /**
     * Test when next from empty.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[]{}
        );
        it.next();
    }
}
