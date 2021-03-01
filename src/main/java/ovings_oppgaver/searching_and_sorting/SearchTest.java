package ovings_oppgaver.searching_and_sorting;
import java.util.*;

public class SearchTest
{
    // Performance comparison of sequential, binary and
    // interpolation search, by counting average number of
    // comparisons of search key with array elements.

    long n_comparisons;  // Used for counting comparisons

    // Sequential search, counting # of comparisons
    boolean seqSearch(int A[], int x)
    {
        int n = A.length;
        n_comparisons = 0;

        for (int i = 0; i < n; i++, n_comparisons++)
            if (A[i] == x)
                return true;
        return false;
    }

    // Iterative binary search, counting # of comparisons
    boolean binSearch(int A[], int x)
    {
        int n = A.length;
        int min = 0, max = n - 1, mid = 0;
        n_comparisons = 0;

        while (max >= min)
        {
            mid = (min + max) / 2;
            n_comparisons++;
            if (A[mid] == x) //
                return true;
            n_comparisons++;
            if (x < A[mid])
                max = mid - 1;
            else
                min = mid + 1;

        }
        return false;
    }

    // Interpolation search, counting # of comparisons
    boolean interSearch(int A[], int x)
    {
        int n = A.length;
        int min = 0, max = n - 1, mid = 0;
        double percent, step;

        n_comparisons = 0;
        while (A[min] < x && x <= A[max] && max > min)
        {
            n_comparisons =+ 2;
            percent = ((double) (x - A[min]))/ ((double) (A[max] - A[min]));
            step = (max - min) * percent;
            mid = min + (int) step;
            n_comparisons++;
            if (A[mid] == x)
                return true;
            n_comparisons++;
            if (x < A[mid])
                max = mid - 1;
            else {
                min = mid + 1;
            }
        }
        n_comparisons++;
        if (A[min] == x)
            return true;
        return false;
    }


    // Test program
    public static void main(String args[])
    {
        final int MAX_N   = 50000000; // Max. length of search array
        final int N_TESTS = 100;      // No. of tests for computing averages

        // Read n from command line
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

        // Searching the array using random keys, several times with
        // each method. Computing average number of comparisons of
        // the search key with array elements.

        int x, test;
        long n_comps;
        boolean found;
        SearchTest sT = new SearchTest();

        System.out.println("\nAverage # of comparisions searching n="
                + n + " sorted numbers " + N_TESTS + " times\n");

        n_comps = 0;
        for (test = 0; test < N_TESTS; test++)
        {
            x = r.nextInt(n);
            found = sT.seqSearch(A, x);
            n_comps += sT.n_comparisons;
        }
        System.out.println("Sequential   : " + n_comps / N_TESTS);

        n_comps = 0;
        for (test = 0; test < N_TESTS; test++)
        {
            x = r.nextInt(n);
            found = sT.binSearch(A, x);
            n_comps += sT.n_comparisons;
        }
        System.out.println("Binary       : " + n_comps / N_TESTS);

        n_comps = 0;
        for (test = 0; test < N_TESTS; test++)
        {
            x = r.nextInt(n);
            found = sT.interSearch(A, x);
            n_comps += sT.n_comparisons;
        }
        System.out.println("Interpolation: " + n_comps / N_TESTS + "\n");
    }
}