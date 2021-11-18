package ru.job4j.isp;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 17.11.2021.
 */
public class TreeNodeTest {
    Item task0;
    Item task1;
    Item task2;
    Item task11;
    Item task12;
    Item task21;
    Item task22;
    Item task23;
    Item task24;
    TreeNode root;
    TreeNode n1;
    TreeNode n2;
    TreeNode n11;
    TreeNode n12;
    TreeNode n21;
    TreeNode n22;
    TreeNode n23;
    TreeNode n24;

    /**
     * Set variables for tests.
     */
    @Before
    public void setVars() {
        task0 = new Item("Task0");
        task1 = new Item("Task1");
        task2 = new Item("Task2");
        task11 = new Item("Task11");
        task12 = new Item("Task12");
        task21 = new Item("Task21");
        task22 = new Item("Task22");
        task23 = new Item("Task23");
        task24 = new Item("Task24");
        root = new TreeNode("0", task0);
        n1 = new TreeNode("1", task1);
        n2 = new TreeNode("2", task2);
        n11 = new TreeNode("1.1", task11);
        n12 = new TreeNode("1.2", task12);
        n21 = new TreeNode("2.1", task21);
        n22 = new TreeNode("2.2", task22);
        n23 = new TreeNode("2.3", task23);
        n24 = new TreeNode("2.4", task24);
    }

    /**
     * Test add(TreeNode) when add new TreeNode as a child than parent should have that node as a child node.
     */
    @Test
    public void testAddWhenAddOneTreeNodeAsAChildThanParentShouldHaveThatNodeAsAChild() {
        root.add(n1);
        assertThat(root.getChildren().get(0), is(n1));
    }

    /**
     * Test add(TreeNode) when add four treeNodes as a child than parent should have that nodes As children.
     */
    @Test
    public void testAddWhenAddFourTreeNodesAsAChildThanParentShouldHaveThatNodesAsChildren() {
        List<TreeNode> nodes = List.of(n1, n2, n11, n12);
        root.add(n1);
        root.add(n2);
        root.add(n11);
        root.add(n12);
        assertTrue(root.getChildren().containsAll(nodes));
    }

    /**
     * Test add(String name, Action item) when add new node than has that node.
     */
    @Test
    public void testAddWhenAddNewNodeThanHasThatNode() {
        root.add("1", task1);
        assertThat(root.getChildren().get(0).getItem(), is(task1));
    }

    /**
     * Test FindNode() when not found than return null.
     */
    @Test
    public void testFindNodeWhenNotFoundThanReturnNull() {
        root.add(n1);
        assertNull(root.findNode("wrong name", root));
    }

    /**
     * Test FindNode() when add one TreeNode than found that node.
     */
    @Test
    public void testFindNodeWhenAddOneNodeThanFoundThatNode() {
        root.add(n1);
        assertThat(root.findNode("1", root), is(n1));
    }

    /**
     * Test FindNode() when 4 TreeNodes than should find correct node.
     */
    @Test
    public void testFindNode() {
        root.add(n1);
        n1.add(n2);
        n2.add(n11);
        n11.add(n12);
        assertThat(root.findNode("1.2", root), is(n12));
    }

    /**
     * Test print() when TreeNode has only one node than print its name.
     */
    @Test
    public void testPrintWhenTreeNodeHasOneNodeThanPrintThatNodeItemNameAndNodeName() {
//        root.add(n1);
//        root.add(n2);
//        n1.add(n11);
//        n1.add(n12);
//        n2.add(n21);
//        n2.add(n22);
//        n2.add(n23);
//        n2.add(n24);
//        root.print();
//        root.add(n1);
//        n1.add(n2);
//        n2.add(n11);
//        n11.add(n12);
//        System.out.println("====================");
        String expected = "Task0 0" + System.lineSeparator();
        assertEquals(root.print().toString(), expected);
    }

    @Test
    public void testPrintWhenTreeNodeHasMultipleNodesThanPrintThatNodeInTreeStyle() {
        root.add(n1);
        root.add(n2);
        n1.add(n11);
        n1.add(n12);
        n2.add(n21);
        n2.add(n22);
        n2.add(n23);
        n2.add(n24);
        StringBuilder sb = new StringBuilder();
        sb.append("Task0 0").append(System.lineSeparator())
                .append("---Task1 1").append(System.lineSeparator())
                .append("------Task11 1.1").append(System.lineSeparator())
                .append("------Task12 1.2").append(System.lineSeparator())
                .append("---Task2 2").append(System.lineSeparator())
                .append("------Task21 2.1").append(System.lineSeparator())
                .append("------Task22 2.2").append(System.lineSeparator())
                .append("------Task23 2.3").append(System.lineSeparator())
                .append("------Task24 2.4").append(System.lineSeparator());
        assertEquals(root.print().toString(), sb.toString());
    }
}
