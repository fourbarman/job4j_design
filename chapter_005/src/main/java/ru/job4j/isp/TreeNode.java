package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

/**
 * TreeNode.
 * <p>
 * Class represents tree node.
 * Tree can contain any amount of children nodes.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 02.11.2021.
 */
public class TreeNode {
    private String parentName;
    private String name;
    private Action item;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode(String name, Action item) {
        this.name = name;
        this.item = item;
    }

    /**
     * Adds Action to tree.
     *
     * @param name Node name.
     * @param item Action.
     */
    public void add(String name, Action item) {
        TreeNode treeNode = new TreeNode(name, item);
        this.children.add(treeNode);
    }

    /**
     * Adds tree node to tree.
     *
     * @param treeNode TreeNode.
     */
    public void add(TreeNode treeNode) {
        treeNode.setParentName(this.name);
        this.children.add(treeNode);
    }

    /**
     * Recursively searches tree node by name.
     *
     * @param name TreeNode name.
     * @param root TreeNode.
     * @return TreeNode.
     */
    public TreeNode findNode(String name, TreeNode root) {
        if (name.equals(root.getName())) {
            return root;
        }
        List<TreeNode> children = root.getChildren();
        TreeNode found = null;
        for (TreeNode tn : children) {
            found = tn.findNode(name, tn);
            if (found != null) {
                return found;
            }
        }
        return found;
    }

    /**
     * Recursively prints all TreeNodes names.
     * String prefix can be specified if needed to print it on each new line.
     *
     * @param pref String prefix.
     */
    private void printNodes(String pref) {
        System.out.print(pref);
        pref += "---";
        System.out.println(getItem().getActionName() + " " + name);
        for (int i = 0; i < children.size(); i++) {
            children.get(i).printNodes(pref);
        }
    }

    public void print() {
        this.printNodes("");
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Action getItem() {
        return item;
    }

    public List<TreeNode> getChildren() {
        return children;
    }
}
