package ovings_oppgaver.binary_trees;

import java.util.Arrays;
import java.util.Random;

public class Oppgave5 {

    private static class BinaryTree {
        TreeNode root;

        public void insert(TreeNode node) {

            if (root == null) {
                root = node;
                return;
            }

            boolean inserted = false;
            TreeNode current = root;

            while (!inserted) {
                if (current.data > node.data) {
                    if (current.left != null)
                        current = current.left;
                    else {
                        current.left = node;
                        inserted = true;
                    }
                } else {
                    if (current.right != null)
                        current = current.right;
                    else {
                        current.right = node;
                        inserted = true;
                    }
                }

            }
        }

        public void insertRek(TreeNode node, TreeNode current) {
            if (current == null)
                current = node;

            if (node.data < current.data) {
                if (current.left != null)
                    insertRek(node, current.left);
                else
                    current.left = node;
            } else {
                if (current.right != null)
                    insertRek(node, current.right);
                else
                    current.right = node;
            }
        }


        public int inorderCopy(TreeNode root, int[] A, int index)
        {
            // Copy (subtree) rooted at root into A, sorted, starting
            // at given index. Returns first free index not yet used.

            if (root == null)
                return index;

            int i = inorderCopy(root.left, A, index);
            A[i] = root.data;
            return inorderCopy(root.right, A, i + 1);
        }

    }

    private static class TreeNode
    {

        public TreeNode(int data) {
            this.data = data;
        }

        public int data;
        public TreeNode left;
        public TreeNode right;

    }

    public static int[] treeSort(int[] arr) {

        BinaryTree tree = new BinaryTree();
        for (int j : arr) tree.insert(new TreeNode(j));

        tree.inorderCopy(tree.root, arr, 0);
        return arr;
    }

    public static void main(String[] args) {

        int[] arr = {5, 4, 9, 0, 8, 2, 7, 3, 1, 6};
        System.out.print(Arrays.toString(treeSort(arr)) + " ");
    }

}
