package fasit;

import java.io.*;
import java.util.*;

public class measureExecutionTime
{

    public static void main(String args[])
    {
        int n_min = 10, n_max = 100, n_step = 10;
        double n, i;
        double n_log_n, n_i_annen, n_i_tredje, n_i_femte;
        double to_i_nte = 1.0, n_fak = 1.0;
        double T;

        for (n = n_min; n <= n_max; n += n_step)
        {
            System.out.println("--------");
            System.out.println("n = " + n);
            System.out.println("--------");

            // Beregner de forskjellige funksjonene av n

            n_log_n = n * Math.log(n);
            n_i_annen = n * n;
            n_i_tredje = n * n * n;
            n_i_femte = n_i_annen * n_i_annen * n;

            for (i = 0; i < n_step; i++)
            {
                to_i_nte *= 2;
                n_fak *= (n - i);
            }

            // UtfÃ¸rer det antall addisjoner som er gitt av
            // funksjonsverdiene og tar tiden. Egentlig tar vi
            // her tiden pÃ¥ alt som skjer i hver iterasjon
            // i for-lÃ¸kkene

            System.out.println("n log(n) = " + n_log_n);
            T = System.currentTimeMillis();
            for (i = 0; i < n_log_n; i++);
            T = System.currentTimeMillis() - T;
            System.out.println("T        = " + T + " ms");

            System.out.println("n^2      = " + n_i_annen);
            T = System.currentTimeMillis();
            for (i = 0; i < n_i_annen; i++);
            T = System.currentTimeMillis() - T;
            System.out.println("T        = " + T + " ms");

            System.out.println("n^3      = " + n_i_tredje);
            T = System.currentTimeMillis();
            for (i = 0; i < n_i_tredje; i++);
            T = System.currentTimeMillis() - T;
            System.out.println("T        = " + T + " ms");

            System.out.println("n^5      = " + n_i_femte);
            T = System.currentTimeMillis();
            for (i = 0; i < n_i_femte; i++);
            T = System.currentTimeMillis() - T;
            System.out.println("T        = " + T + " ms");

            System.out.println("2^n      = " + to_i_nte);
            if (n <= 30)
            {
                T = System.currentTimeMillis();
                for (i = 0; i < to_i_nte; i++);
                T = System.currentTimeMillis() - T;
                System.out.println("T        = " + T + " ms");
            }
            else
                System.out.println("T        = " + "Alt for mye");

            System.out.println("n!       = " + n_fak);
            if (n <= 10)
            {
                T = System.currentTimeMillis();
                for (i = 0; i < n_fak; i++);
                T = System.currentTimeMillis() - T;
                System.out.println("T        = " + T + " ms");
            }
            else
                System.out.println("T        = " + "Alt for mye");
        }
    }
}
