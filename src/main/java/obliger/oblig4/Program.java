package obliger.oblig4;

import java.util.ArrayList;

public class Program {

    // deler teksten opp i ord
    public static void split(ArrayList<String> words, String tekst) {

        int start = 0;
        for (int i = 0; i < tekst.length(); i++)
            if (!Character.isLetter(tekst.charAt(i))) {
                if (Character.isLetter(tekst.charAt(i-1)))
                    words.add(tekst.substring(start, i).toUpperCase());
                start = i + 1;
            }
    }

    public static void main(String[] args) {

        // obs. bokstaver i et akronym vil telles som ord. Jeg har ikke gjort noe med dette,
        //ettersom det ikke er et krav. I praksis vil det si at bokstavene i B.E.F
        // være hvert sitt ord.

        ArrayList<String> words = new ArrayList<>();

        split(words, Text.content);

        BinaryTree tree = new BinaryTree(null);
        tree.insertWords(words);

        tree.inorderTraverse(tree.root);
    }
}
