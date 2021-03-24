package usecases.svadageneratoren;

import java.io.IOException;

public class Svada
{
    // Maks. lengde pÃ¥ n-sekvensene
    public static final int maxN = 6;

    // Antall linjer med svada som skal skrives ut
    public static final int antLinjer = 30;

    // Linjebredde for svada som skrives ut, angir maks. antall tegn
    // per linje
    public static final int linjeBredde = 80;

    // Hovedprogram som leser input, registrerer n-sekvensene og
    // skriver ut svada
    //
    public static void main(String args[])
    {
        // Lengde pÃ¥ tegnsekvensene
        int n = 0;

        // Leser lengden pÃ¥ tegnsekvensene fra kommandolinjen
        try
        {
            if (args.length != 1)
                throw new IOException("Brukes slik: svada [heltall]");
            n = Integer.parseInt(args[0]);
            if (n < 2 || n > maxN)
                throw new IOException("Ulovlig verdi av sekvenslengden n = "
                        + n + ", bruk 2 <= n <= " + maxN);
        }
        catch (Exception e)
        {
            System.err.println(e);
            System.exit(1);
        }

        // Oppretter ny sekvensleser og nytt sekvensregister
        SekvensLeser sL = new SekvensLeser(n);
        SekvensRegister sR = new SekvensRegister(n);

        // Leser de to fÃ¸rste sekvensene fra input
        char forsteS[] = sL.nesteSekvens();
        char S1[] = forsteS;
        char S2[] = sL.nesteSekvens();

        // Leser og registrerer alle sekvenser og etterfÃ¸lgere i input
        while (S2 != null)
        {
            // Siste tegn i neste sekvens er etterfÃ¸lgertegnet til
            // forrige sekvens
            char e = S2[n-1];

            // Registrerer ny forekomst av sekvens med etterfÃ¸lger
            sR.registrer(S1, e);

            // Leser inn neste sekvens
            S1 = S2;
            S2 = sL.nesteSekvens();
        }

        // Alle sekvenser lest og lagret i sekvensregisteret, skriv ut
        // noen linjer med svada
        skrivSvada(sR, forsteS, n);
    }

    // Skriver ut linjer med tilfeldig svada, uten deling av "ord"
    // Antall linjer som skrives ut, og maks antall tegn per linje, er
    // gitt av de to konstantene antLinjer og linjebredde
    //
    private static void skrivSvada(SekvensRegister sR, char[] forsteS, int n)
    {
        // sR: Registeret med n-sekvenser
        //
        // forsteS: FÃ¸rste sekvens som er lest, brukes som
        // utgangspunkt for Ã¥ starte den tilfeldige trekkingen av
        // etterfÃ¸lgertegn
        //
        // n: Lengden pÃ¥ tegnsekvensene

        // Begynner med Ã¥ skrive ut de n-2 siste tegnene i fÃ¸rste
        // sekvens
        for (int i = 2; i < n; i++)
            System.out.print(forsteS[i]);

        // S inneholder hele tiden siste sekvens som er skrevet ut
        char S[] = forsteS;

        // Antall linjer med svada som er skrevet ut
        int linjeNr = 0;

        // Tre hjelpevariable som brukes fÃ¥r Ã¥ unngÃ¥ at et "ord",
        // dvs. en sekvens av pÃ¥fÃ¸lgende ikke-blanke tegn, deles
        // over to linjer:

        // Buffer som brukes lagre et "ord" fÃ¸r det skrives ut
        char ord[] = new char[linjeBredde];

        // Antall tegn i ordet som er lagret i buffer
        int ordLengde = 0;

        // Antall tegn som er skrevet ut pÃ¥ nÃ¥vÃ¦rende linje
        int linjePos = n - 2;

        while (linjeNr < antLinjer)
        {
            // Trekker tilfeldig et av etterfÃ¸lgertegnene til siste
            // sekvens som er skrevet ut
            char e = sR.trekkEtterfolger(S);

            // Oppdaterer sist utskrevne sekvens
            for (int i = 1; i < n; i++)
                S[i-1] = S[i];
            S[n-1] = e;

            // Utskrift av tegnet som er trukket, med litt triksing
            // for Ã¥ unngÃ¥ deling av et "ord" over to linjer. Bufrer
            // hvert ord i en array og skriver fÃ¸rst ut nÃ¥r det kommer
            // en ny space.

            if (!Character.isSpace(e))
            // Tegnet som er trukket er ikke en space, bufrer bare
            // tegnet for senere utskrift av helt "ord"
            {
                ord[ordLengde] = e;
                ordLengde++;
            }
            else
            // Har trukket en space, skriver ut bufret "ord"
            {
                if ((linjePos + ordLengde) > linjeBredde)
                // Ordet fÃ¥r ikke plass pÃ¥ resten av linjen, skriv ut
                // et linjeskift fÃ¸r ordet
                {
                    linjeNr++;
                    System.out.print("\n");
                    linjePos = 0;
                }

                // Skriv ut ordet som er lagret i buffer
                if (linjeNr < antLinjer)
                {
                    for (int i = 0; i < ordLengde; i++)
                        System.out.print(ord[i]);
                    System.out.print(' ');
                }

                linjePos += ordLengde + 1;
                ordLengde = 0;
            }
        }
    }
}
