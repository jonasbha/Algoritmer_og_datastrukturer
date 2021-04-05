package obliger.oblig5;

import java.io.*;
import java.util.Scanner;

// Følgende metoder har blitt lagt til: insert1, insert2 og støttemetode for insert2:
// compareDistance. Det er ikke blitt gjort noe med andre metoder.
//
public class HashLinear
{
    private int hashLengde;

    private String hashTabell[];

    private int n;

    private int antProbes;

    public HashLinear(int lengde) {
        hashLengde = lengde;
        hashTabell = new String[lengde];
        n = 0;
        antProbes = 0;
    }

    public float loadFactor()
    {
        return ((float) n)/hashLengde;
    }

    public int antData()
    {
        return n;
    }

    public int antProbes()
    {
        return antProbes;
    }

    int hash(String S) {
        int h = Math.abs(S.hashCode());
        return h % hashLengde;
    }

    // linear probing (original)
    void insert(String S) {
        int h = hash(S);

        int neste = h;

        while (hashTabell[neste] != null) {
            antProbes++;

            neste++;

            if (neste >= hashLengde)
                neste = 0;


            if (neste == h)
            {
                System.err.println("\nHashtabell full, avbryter");
                System.exit(0);
            }
        }

        // den gamle stringen blir istedet satt inn på neste ledige plass.
        hashTabell[neste] = S;

        n++;
    }

    // Last come, first served
    void insert1(String S) {
        int h = hash(S);
        int index = h;

        if (hashTabell[index] != null)
            antProbes++;

        String current = S;
        String temp;
        while (true) {
            if (hashTabell[index] == null) {
                hashTabell[index] = current;
                return;
            }
            else {
                temp = hashTabell[index];
                hashTabell[index] = current;
                current = temp;

                index++;

                if (index >= hashLengde)
                    index = 0;

                if (index == h) {
                    System.err.println("\nHashtabell full, avbryter");
                    System.exit(0);
                }
            }
        }
    }

    // Robin Hood
    void insert2(String S) {
        int h = hash(S);
        int index = h;

        if (hashTabell[index] != null)
            antProbes++;

        String current = S;
        String temp;
        while (true) {
            if (hashTabell[index] == null) {
                hashTabell[index] = current;
                return;
            }
            else {
                temp = hashTabell[index];

                // if current is further away from original index than temp
                if (compareDistance(current, temp, index)) {
                    hashTabell[index] = current;
                    current = temp;
                }
                index++;

                if (index >= hashLengde)
                    index = 0;

                if (index == h) {
                    System.err.println("\nHashtabell full, avbryter");
                    System.exit(0);
                }
            }
        }
    }

    // support method for insert2
    private boolean compareDistance(String current, String actual, int index) {
        return (hash(current) - index) > hash(actual) - index;
    }

    boolean search(String S) {
        int h = hash(S);

        int neste = h;

        while (hashTabell[neste] != null)
        {
            if (hashTabell[neste].compareTo(S) == 0)
                return true;

            neste++;

            if (neste >= hashLengde)
                neste = 0;

            if (neste == h)
                return false;
        }

        return false;
    }

    public static void main(String argv[]) {
        int hashLengde = 0;

        Scanner input = new Scanner(System.in);
        try
        {
            if (argv.length != 1)
                throw new IOException("Feil: Hashlengde mÃ¥ angis");
            hashLengde = Integer.parseInt(argv[0]);
            if (hashLengde < 1 )
                throw new IOException("Feil: Hashlengde mÃ¥ vÃ¦re stÃ¸rre enn 0");
        }
        catch (Exception e)
        {
            System.err.println(e);
            System.exit(1);
        }


        HashLinear hL = new HashLinear(hashLengde);

        while (input.hasNext())
            hL.insert(input.nextLine());


        System.out.println("Hashlengde  : " + hashLengde);
        System.out.println("Elementer   : " + hL.antData());
        System.out.printf( "Load factor : %5.3f\n",  hL.loadFactor());
        System.out.println("Probes      : " + hL.antProbes());

        String S = "Volkswagen Karmann Ghia";
        if (hL.search(S))
            System.out.println("\"" + S + "\"" + " finnes i hashtabellen");
        S = "Il Tempo Gigante";
        if (!hL.search(S))
            System.out.println("\"" + S + "\"" + " finnes ikke i hashtabellen");
    }
}
