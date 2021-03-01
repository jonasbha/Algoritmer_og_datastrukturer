package ovings_oppgaver.recursion;

/*
Anta at Java ikke har noen innebygget multiplikasjonsoperator (*), men kun addisjon (+).
Skriv en rekursiv metode som har som argumenter to ikke-negative heltall m og n,
 og som returnerer produktet m · n beregnet ved rekursiv addisjon.
Anta at Java ikke har noen innebygget addisjonsoperator (+), men kun inkrement (++).
Skriv en rekursiv metode som har som argumenter to ikke-negative heltall m og n,
 og som returnerer summen m + n beregnet ved rekursivt inkrement.
 */

import java.util.Random;

public class ReplacingOperators {

    static int multiply(int m, int n) {
        if (m == 0 || n == 0 || n < 0)
            return 0;
        return m + multiply(m, n - 1);
    }

    static int add(int m, int n) {
        if (m < 0 || n < 0)
            return 0;
        m++;
        n--;
        if (n == 0)
            return m;
        else
            return add(m, n);
    }

    public static void main(String[] args) {
        System.out.println(multiply(6, 4));
        System.out.println(add(6, 14));

        Random rand = new Random();
        System.out.println(rand.nextInt(1));
    }

}
