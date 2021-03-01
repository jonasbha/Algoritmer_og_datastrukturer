package ovings_oppgaver.list;

import tools.jsjf.ArrayUnorderedList;

import java.util.Random;

public class RadixSortWithUList
{
    /*
    * Skriv om koden for radixsortering slik at programmet sorterer en uordnet liste med
    * heltall i stedet for en array. Bruk lærebokas listemodul.
    * */

    // counting sorting også kalt radix sortering
    // counting sort O(n)? how: A.length vil alltid være mye større enn B.length
    // Denne sorteringsmetoden baseres på swapping, noe som gjør den treg selv om den er lineær.
    // I tilegg må alle verdier bestå av positive heltall og maks antall siffer må vites på forhånd.

    public static void sort(int maksInt, int[] A) {

        ArrayUnorderedList<Integer>[] B = new ArrayUnorderedList[maksInt];

        for (int i = 0; i < maksInt; i++)
            B[i] = new ArrayUnorderedList<>();

        for (int k : A)
            for (int j = 0; j < B.length; j++)
                if (k == j)
                    B[j].addToFront(k);

        // decide sorting order. a = 0, a++
        int a = A.length-1;
        for (ArrayUnorderedList<Integer> integers : B)
            while (!integers.isEmpty())
                A[a--] = integers.removeFirst();
    }

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

}