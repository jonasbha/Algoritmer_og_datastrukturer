package ovings_oppgaver.searching_and_sorting;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SearchAlgorithms {

    long n_comparisonss;

    boolean binSearch(int[] arr, int x) {
        int min = 0, max = arr.length - 1;

        n_comparisonss = 0;
        while (max >= min) {
            int mid = (max + min) / 2;

            n_comparisonss++;
            if (arr[mid] == x)
                return true;

            n_comparisonss++;
            if (x < arr[mid])
                max = mid - 1;
            else
                min = mid + 1;
        }
        return false;
    }

    public boolean ternSearch(int[] arr, int x) {
        int min = 0, max = arr.length - 1;

        n_comparisonss = 0;
        while (max >= min) {

            int mid1 = (int) Math.round(((max - min) * 0.333) + min);
            int mid2 = (int) Math.round(((max - min) * 0.666) + min);

            n_comparisonss+=2;
            if (arr[mid1] == x || arr[mid2] == x) {
                return true;
            }

            if (x < arr[mid1]) {
                n_comparisonss++;
                max = mid1 - 1;
            }
            else if (x > arr[mid2]) {
                n_comparisonss+=2;
                min = mid2 + 1;
            }
            else {
                max = mid2 - 1;
                min = mid1 + 1;
            }
        }
        return false;
    }

    public boolean recTernSearch(int[] arr, int x, int min, int max) {
        int mid1 = (int) Math.round(((max - min) * 0.333) + min);
        int mid2 = (int) Math.round(((max - min) * 0.666) + min);

        n_comparisonss++;
        if (max <= min)
            return false;
        n_comparisonss++;
        if (arr[mid1] == x || arr[mid2] == x)
            return true;

        if (x < arr[mid1]) {
            n_comparisonss++;
            return recTernSearch(arr, x, min, mid1 - 1);
        }
        else if (x > arr[mid2]) {
            n_comparisonss++;
            return recTernSearch(arr, x, mid2+1, max);
        }
        else
            return recTernSearch(arr, x, mid1 + 1, mid2 - 1);
    }


    boolean recBinSearch(int A[], int x, int min, int max)
    {
        if (min >= max)
            return false;

        int mid = (min + max) / 2;

        n_comparisonss++;
        if (A[mid] == x)
            return true;

        n_comparisonss++;
        if (x < A[mid])
            return recBinSearch(A, x, min, mid - 1);

        return recBinSearch(A, x, mid + 1, max);
    }


    public static void main(String[] args) {
        final int MAX_N   = 50000000; // Max. length of search array
        final int N_TESTS = 100;      // No. of tests for computing averages

        Scanner scanner = new Scanner(System.in);
        System.out.print("n: ");
        int n = scanner.nextInt();
        if (n < 1 || n > MAX_N)
        {
            System.out.println("Use  1 <= n <= " + MAX_N);
            System.exit(1);
        }

        // Creating test array with sorted random numbers
        int A[] = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++)
            A[i] = r.nextInt(n);
        Arrays.sort(A, 0, n-1);

        SearchAlgorithms algo = new SearchAlgorithms();


        System.out.println("\nAverage # of comparisions searching n="
                + n + " sorted numbers " + N_TESTS + " times\n");

        int avgComps = 0;
        for (int i = 0; i < N_TESTS; i++) {
            algo.ternSearch(A, r.nextInt(n));
            avgComps += algo.n_comparisonss;
        }
        System.out.println("normal ternsearch = "  + avgComps / N_TESTS);

        avgComps = 0;
        for (int i = 0; i < N_TESTS; i++) {
            algo.binSearch(A, r.nextInt(n));
            avgComps += algo.n_comparisonss;
        }
        System.out.println("normal binsearch = "  + avgComps / N_TESTS);
    }
}
