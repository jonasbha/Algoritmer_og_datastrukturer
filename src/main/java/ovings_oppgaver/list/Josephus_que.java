package ovings_oppgaver.list;

import java.util.*;

/**
 * Demonstrates the use of an indexed list to solve the Josephus problem.
 *
 * @author Lewis and Chase 
 * @author Jan H.
 * @version 5.0
 */
public class Josephus_que
{
    /**
     * Continue around the circle eliminating every d'th number 
     * until all but one number have been eliminated.
     */
    public static void main(String[] args)
    {
        // n: number of elements
        // d: skip size
        // f: first number to be removed
        int n, d, f;

        Scanner in = new Scanner(System.in);
        System.out.print("n: ");
        n = in.nextInt();
        in.nextLine();
        System.out.print("d: ");
        d = in.nextInt();
        in.nextLine();
        System.out.print("f: ");
        f = in.nextInt();

        // Indexed list storing remaining elements during removal
        Queue<Integer> Q = new LinkedList<>();

        // Load the initial list of numbers
        for (int i = f; i <= n; i++)
            Q.add(i);
        for (int i = 1; i < f; i++)
            Q.add(i);

        System.out.print("The order of removal is:\n");

        /*
        int i = 0;
        while (Q.size() > 1)
        {
            if (i % d == 0)
                System.out.println(Q.remove());
            else
                Q.add(Q.remove());

            if (i == n)
                i = 0;
            i++;
        }
        */


        while (Q.size() > 1)
        {
            System.out.println(Q.remove());

            for (int i = 1; i < d; i++) {
                Q.add(Q.remove());
            }
        }


        // Print last number remaining in list
        System.out.println("\nNumber not removed: " + Q.peek());
    }
}