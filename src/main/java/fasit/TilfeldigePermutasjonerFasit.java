package fasit;
// Tilfeldige permutasjoner

import java.util.Random;

public class TilfeldigePermutasjonerFasit
{
    private static Random r = new Random();



    public static void stokk(int p[])
    {
        // Fyller vektoren p, med en tilfeldig permutasjon av tallene
        // fra 1 til n = p.length. Denne metoden passer glimrende til
        // f.eks.Ã¥ stokke kortene for et bridge-program, der kortstokken
        // kan representeres med n=52 tall.

        int i, tilf, tmp, n = p.length;

        // Fyller p med tallene 1,2,3,...,n

        for (i = 0; i < n; i ++)
            p[i] = i + 1;

        // Stokker (bytter om) de n tallene tilfeldig n ganger

        for (i = 0; i < n - 1; i ++)
        {
            // Bytter om tallet i posisjon i med et tilfeldig element
            // lenger ut i p
            tilf = (i + 1) + r.nextInt(n - i - 1);
            tmp = p[i];
            p[i] = p[tilf];
            p[tilf] = tmp;
        }
    }

    public static void main(String args[])
    {
        // Enkelt testprogram

        int n = 3;

        int p[] = new int[n];

        System.out.println("Tilfeldige permutasjoner av 1 - 9:");
        for (int i = 0; i <= n; i++)
        {
            stokk(p);

            for (int j = 0; j < n; j++)
                System.out.print(p[j] + " ");
            System.out.println();
        }
    }
}