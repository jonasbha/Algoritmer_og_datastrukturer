package ovings_oppgaver.binary_trees;

public class CopyTree
{

    private static class treeNode
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

    static treeNode copy(treeNode root)
    {
        // Returnerer en kopi av et binÃ¦rt tre

        if (root == null)
            return null;

        // Lager ny rot og kopierer hele treet med to rekursive kall
        return(new treeNode(root.data, copy(root.left), copy(root.right)));
    }

    public static void main(String[] args)
    {
        // Testprogram

        // Lager et lite søketre
        treeNode root = new treeNode('D',
                new treeNode('B',
                        new treeNode('A', null, null),
                        new treeNode('C', null, null)),
                new treeNode('F',
                        new treeNode('E', null, null),
                        new treeNode('G', null, null)));

        // Lager en kopi
        treeNode root2 = copy(root);

        // Skriver ut begge trÃ¦rne for Ã¥ se at de er like

        inorder(root);
        System.out.println();
        inorder(root2);
        System.out.println();
    }

    static void inorder(treeNode root)
    {
        // Skriver ut et tre i inorder rekkefÃ¸ge
        if (root != null)
        {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }
}