package obliger.oblig4;

import java.util.ArrayList;

public class BinaryTree {

    TreeNode root;

    public BinaryTree(TreeNode root) {
        this.root = root;
    }

    public void insertWord(TreeNode node) {

        if (this.root == null) {
            root = node;
            return;
        }

        TreeNode current = root;
        while (true) {
            if (node.word.compareTo(current.word) > 0)
                if (current.right != null)
                    current = current.right;
                else {
                    current.right = node;
                    return;
                }
            else if (node.word.compareTo(current.word) < 0)
                if (current.left != null)
                    current = current.left;
                else {
                    current.left = node;
                    return;
                }
            else {
                current.occurrence++;
                return;
            }
        }
    }

    public void insertWordRec(TreeNode node, TreeNode current) {

        if (root == null) {
            root = node;
            return;
        }

        if (current == null)
            return;

        if (node.word.compareTo(current.word) > 0) {
            if (current.right != null)
                insertWordRec(node, current.right);
            else
                current.right = node;
        }
        else if (node.word.compareTo(current.word) < 0)
            if (current.left != null)
                insertWordRec(node, current.left);
            else
                current.left = node;
        else
            current.occurrence++;
    }

    public void insertWords(ArrayList<String> words) {
        for (String word : words) insertWord(new TreeNode(word, null, null));
    }

    public void inorderTraverse(TreeNode root) {
        // Skriver ut et tre i inorder rekkefÃ¸ge
        if (root != null)
        {
            inorderTraverse(root.left);
            System.out.println(root.word + ", occurrence: " + root.occurrence);
            inorderTraverse(root.right);
        }
    }
}

class TreeNode {

    String word;
    int occurrence = 1;
    TreeNode left;
    TreeNode right;

    public TreeNode(String word, TreeNode left, TreeNode right) {
        this.word = word;
        this.left = left;
        this.right = right;
    }
}