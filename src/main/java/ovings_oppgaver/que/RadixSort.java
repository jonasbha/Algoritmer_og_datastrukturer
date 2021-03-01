package ovings_oppgaver.que;

import tools.jsjf.ArrayStack;

import java.util.Random;

public class RadixSort {

    public static void main(String[] args) {
        int maksAntall = 8;
        int arraySize = 20;

        Random rand = new Random();

        int[] array = new int[arraySize];
        for (int i = 0; i < array.length; i++)
            array[i] = rand.nextInt(maksAntall);

        printArray(array);
        sort(maksAntall, array);
        printArray(array);
    }

    private static void printArray(int[] array) {
        System.out.println("\n");
        for (int j : array) System.out.print(j + ", ");
    }

    // counting sorting også kalt radix sortering
    // counting sort O(n)? how: A.length vil alltid være mye større enn B.length
    // Denne sorteringsmetoden baseres på swapping, noe som gjør den treg selv om den er lineær.
    // I tilegg må alle verdier bestå av positive heltall og maks antall siffer må vites på forhånd.
    public static void sort(int maksInt, int[] A) {
        ArrayStack[] B = new ArrayStack[maksInt];
        for (int i = 0; i < maksInt; i++)
            B[i] = new ArrayStack();

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] == j)
                    B[j].push(A[i]);
            }
        }

        int a = 0;
        for (int j = 0; j < B.length; j++)
            while (!B[j].isEmpty())
                A[a++] = (int) B[j].pop();
    }

    public static void sort2(int maksInt, int A[]) {

        int ti_i_m = 1;

        ArrayStack[] B = new ArrayStack[maksInt];
        for (int i = 0; i < maksInt; i++)
            B[i] = new ArrayStack();


        for (int m = 0; m < B.length; m++)
        {
            for (int i = 0; i < A.length; i++) {
                int siffer = (A[i] / ti_i_m) % 10;
                B[siffer].push(A[i]);
            }
            ti_i_m *= 10;

            int j = 0;
            for (int i = 0; i < B.length; i++)
                while (!B[i].isEmpty())
                    A[j++] = (int) B[i].pop();
        }
    }
}
