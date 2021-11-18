package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

/**
 * Menu.
 * <p>
 * Represents menu based on tree.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 02.11.2021.
 */
public class Menu implements PrintMenu {
    private List<TreeNode> entries = new ArrayList<>();

    /**
     * Adds Action to menu as new tree node.
     * If parent name == null than tree node will be one of the root nodes.
     * Else - method searches tree node according to parentName and adds Action to that tree node.
     *
     * @param parentName Parent Tree node name.
     * @param childName  Child name for tree node.
     * @param item       Action.
     */
    public void add(String parentName, String childName, Action item) {
        TreeNode newNode = new TreeNode(childName, item);
        if (parentName == null) {
            this.entries.add(newNode);
        } else {
            TreeNode parent = findNodeByName(parentName);
            parent.add(childName, item);
        }
    }

    /**
     * Searches tree node by name and returns it if found.
     *
     * @param name TreeNode name.
     * @return TreeNode.
     */
    private TreeNode findNodeByName(String name) {
        TreeNode found = null;
        for (TreeNode node : entries) {
            found = node.findNode(name, node);
            if (found != null) {
                return found;
            }
        }
        return found;
    }

    /**
     * Searches tree node with Action inside and returns it
     *
     * @param actionName Action name.
     * @return TreeNode.
     */
    public TreeNode select(String actionName) {
        return this.findNodeByName(actionName);
    }

    /**
     * Returns all menu actions in tree style.
     */
    @Override
    public String printMenu() {
        StringBuilder result = new StringBuilder();
        for (TreeNode tn : entries) {
            result.append(tn.print());
        }
        return result.toString();
    }
}
