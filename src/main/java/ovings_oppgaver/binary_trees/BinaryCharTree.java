package ovings_oppgaver.binary_trees;

// Binary tree nodes with character data
class treeNode
{
    char data;
    treeNode left, right;

    public treeNode(char c, treeNode l, treeNode r)
    {
        data = c;
        left = l;
        right = r;
    }
}


// Unordered binary tree of characters
public class BinaryCharTree
{
    // Root of binary tree
    treeNode root;

    // Contructor
    public BinaryCharTree()
    {
        root = null;
    }


    // Public search method
    public boolean contains(char x)
    {
        return contains(root, x);
    }

    // Recursive method for searching for a value "x" in the tree
    // rooted at "root"
    private boolean contains(treeNode root, char x)
    {
        if (root == null)
            return false;
        if (root.data == x)
            return true;
        return (contains(root.left, x) || contains(root.right, x));
    }

    // returnerer antall noder i treet med rot i noden root.
    private int numNodes(treeNode root) {
        if (root == null)
            return 0;
        return 1 + numNodes(root.left) + numNodes(root.right);
    }

    // returnerer antall bladnoder i treet med rot i noden root.
    private int numLeaves(treeNode root) {
        if (root == null)
            return 0;
        if (root.right == null && root.left == null)
            return 1;
        return numLeaves(root.right) + numLeaves(root.left);
    }

    // returnerer antall noder i treet som har to barn
    private int numTwoChildren(treeNode root) {
        if (root == null)
            return 0;
        if (root.right != null && root.left != null)
            return 1 + numTwoChildren(root.right) + numTwoChildren(root.left);
        return numTwoChildren(root.right) + numTwoChildren(root.left);
    }

    private int numOneChild(treeNode root) {
        if (root == null)
            return 0;

        int children = 0, add = 0;
        if (root.right != null)
            children++;
        if (root.left != null)
            children++;

        if (children == 1)
            add = children;

        return add + numOneChild(root.right) + numOneChild(root.left);
    }

    // returnerer antall nivåer (høyden) i treet mot rot i noden root.
    private int numLevels(treeNode root)
    {
        if (root == null)
            return 0;
        int left = numLevels(root.left);
        int right = numLevels(root.right);
        if (left > right)
            return (1 + left);
        return (1 + right);
    }

    // Test program
    public static void main(String[] args)
    {
    /* Using this binary tree:

                  A
                 / \
                B   C
               / \   \
              /   \   \
             /     \   \
            D       E   F
           / \     / \   \
          G   H   I   J   K
                 / \
                L   M
 */

        BinaryCharTree BCT = new BinaryCharTree();

        // Setting up the tree in the figure above explicitly
        BCT.root = new treeNode('A',
                new treeNode('B',
                        new treeNode('D',
                                new treeNode('G', null, null),
                                new treeNode('H', null, null)),
                        new treeNode('E',
                                new treeNode('I',
                                        new treeNode('L', null, null),
                                        new treeNode('M', null, null)),
                                new treeNode('J', null, null))),
                new treeNode('C', null,
                        new treeNode('F', null,
                                new treeNode('K', null, null))));

        // Printing out the tree used in the test program
        System.out.println("\n           A");
        System.out.println("          / \\");
        System.out.println("         B   C");
        System.out.println("        / \\   \\");
        System.out.println("       /   \\   \\");
        System.out.println("      /     \\   \\");
        System.out.println("     D       E   F");
        System.out.println("    / \\     / \\   \\");
        System.out.println("   G   H   I   J   K");
        System.out.println("          / \\");
        System.out.println("         L   M\n");

        // Testing the tree's methods
        for (char c ='A'; c <= 'Z'; c+=3)
            if (BCT.contains(c))
                System.out.println("Tree contains " + c);
            else
                System.out.println("Tree does not contain " + c);

        System.out.print("\nNo. of nodes     : " + BCT.numNodes(BCT.root));
        System.out.print("\nNo. of leaves    :  " + BCT.numLeaves(BCT.root));
        System.out.print("\nNo. w/2 children :  " + BCT.numTwoChildren(BCT.root));
        System.out.print("\nNo. w/1 children :  " + BCT.numOneChild(BCT.root));
        System.out.print("\nNo. of levels    :  " + BCT.numLevels(BCT.root));
        System.out.println();
    }
}

