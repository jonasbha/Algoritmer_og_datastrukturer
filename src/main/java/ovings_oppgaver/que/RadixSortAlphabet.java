package ovings_oppgaver.que;

import tools.jsjf.ArrayStack;

import java.util.Random;

public class RadixSortAlphabet {

    public static void main(String[] args) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        int maksAntall = alphabet.length();
        int arraySize = 20;

        Random rand = new Random();
        char[] array = new char[arraySize];
        for (int i = 0; i < arraySize; i++)
            array[i] = alphabet.charAt(rand.nextInt(maksAntall));

        String sArray[] = {"Arne", "Reidar", "Ottar", "Harry", "Cashmere",
                "Cat", "Peder", "Bjarne", "Christian", "Robert",
                "Garth", "Rick", "Levon", "Richard", "Robbie",
                "Ronnie", "Bob", "Frank", "Zappa", "Aaron",
                "Bjartmar", "Adolf", "Hermann", "Vidkun"};

        printArray(sArray);
        sortStrings(maksAntall, sArray);
        printArray(sArray);

        /*
        printArray(array);
        sortChars(maksAntall, array);
        printArray(array);
        */
    }

    private static void printArray(char[] array) {
        System.out.println("\n");
        for (char j : array) System.out.print(j + ", ");
    }

    private static void printArray(String[] array) {
        System.out.println("\n");
        for (String j : array) System.out.print(j + ", ");
    }

    public static void sortChars(int maksInt, char[] A) {
        ArrayStack[] B = new ArrayStack[maksInt];
        for (int i = 0; i < maksInt; i++)
            B[i] = new ArrayStack();

        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] == alphabet.charAt(j))
                    B[j].push(A[i]);
            }
        }

        int a = 0;
        for (int j = 0; j < B.length; j++)
            while (!B[j].isEmpty())
                A[a++] = (char) B[j].pop();
    }

    //egen
    public static void sortStrings(int maksInt, String[] A) {

        //setup
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        ArrayStack[] B = new ArrayStack[maksInt];
        for (int i = 0; i < maksInt; i++)
            B[i] = new ArrayStack();

        for (int i = 0; i < A.length; i++)
            A[i] = A[i].toLowerCase();

        //algo
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < B.length; j++)
                if (A[i].charAt(0) == alphabet.charAt(j))
                    B[j].push(A[i]);

        int a = 0;
        for (int j = 0; j < B.length; j++)
            while (!B[j].isEmpty())
                A[a++] = (String) B[j].pop();
    }

    //lærer sin
    public static void sortStrings2(int maksInt, String[] A, int maksStringLengde) {

        ArrayStack[] B = new ArrayStack[maksInt];
        for (int i = 0; i < maksInt; i++)
            B[i] = new ArrayStack();

        for (int i = 0; i < A.length; i++)
            A[i] = A[i].toLowerCase();



        int n = A.length;

        for (int m = maksStringLengde; m > 0; m--)
        {
            for (int i = 0; i < n; i++)
            {
                int index;
                if (A[i].length() < m)
                    index = 0;
                else
                    index = A[i].charAt(m-1) - 'a';
                B[index].push(A[i]);
            }

            int j = 0;
            for (int i = 0; i < maksInt; i++)
                while (!B[i].isEmpty())
                    A[j++] = (String) B[i].pop();
        }
    }

}
