package ru.job4j.isp;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 17.11.2021.
 */
public class MenuTest {
    /**
     * Test select.
     * When add item to menu than should return it.
     */
    @Test
    public void whenAddOneItemToMenuAndSelectByItsNameThanShouldReturnThatItem() {
        Menu menu = new Menu();
        Action actionOne = new Item("actionOne");
        menu.add(null, "1", actionOne);
        assertThat(menu.select("1").getItem(), is(actionOne));
    }

    /**
     * Test select.
     * When add item to menu and select it with wrong name than should return null.
     */
    @Test
    public void whenAddItemToMenuAndSelectWrongNameThanShouldReturnNull() {
        Menu menu = new Menu();
        Action actionOne = new Item("actionOne");
        menu.add(null, "1", actionOne);
        assertNull(menu.select("wrongName"));
    }

    /**
     * Test select.
     * When add 3 items to menu than should return them.
     */
    @Test
    public void whenAddThreeItemsToMenuAndSelectOneByItsNameThanShouldReturnThatItem() {
        Menu menu = new Menu();
        Action actionOne = new Item("actionOne");
        Action actionTwo = new Item("actionTwo");
        Action actionThree = new Item("actionThree");

        menu.add(null, "1", actionOne);
        menu.add("1", "2", actionTwo);
        menu.add("2", "3", actionThree);

        assertThat(menu.select("1").getItem(), is(actionOne));
        assertThat(menu.select("2").getItem(), is(actionTwo));
        assertThat(menu.select("3").getItem(), is(actionThree));
    }
}
