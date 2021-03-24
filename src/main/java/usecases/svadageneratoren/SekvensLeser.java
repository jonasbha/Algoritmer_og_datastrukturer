package usecases.svadageneratoren;

import java.io.IOException;

public class SekvensLeser
{
    // Lengde av tegnsekvenser
    private int n;

    // Siste leste sekvens
    private char sekvens[];

    // Buffer for Ã¥ lagre et tegn som allerede er lest men ikke lagt
    // inn i sekvens
    private char buffer;

    // Boolsk flagg for Ã¥ sjekke om det allerede ligger et ubehandlet
    // tegn i bufferet
    private boolean iBuffer;

    // Boolsk flagg for Ã¥ sjekke om det er slutt pÃ¥ input
    private static boolean lestEOF;


    // KonstruktÃ¸r som leser/setter opp fÃ¸rste sekvens
    //
    public SekvensLeser(int length)
    {
        if (length < 2)
        {
            System.err.println("sekvensLeser: Feil sekvenslengde");
            System.exit(1);
        }

        n = length;
        sekvens = new char[n];
        iBuffer = false;
        lestEOF = false;

        // Lite triks: Antar alltid at teksten starter med ". "
        sekvens[0] = '.';
        sekvens[1] = ' ';
        // Leser de n-2 fÃ¸rste tegnene fra input
        for (int i = 2; i < n; i++)
            sekvens[i] = nesteTegn();
    }

    // Leverer neste n-sekvens fra input, returnerer null hvis det er
    // slutt pÃ¥ input
    //
    public char[] nesteSekvens()
    {
        if (lestEOF)
            return null;

        char S[] = new char[n];

        // Kopierer nÃ¥vÃ¦rende sekvens
        for (int i = 0; i < n; i++)
            S[i] = sekvens[i];

        // Skifter nÃ¥vÃ¦rende sekvens et tegn til venstre
        for (int i = 0; i < n-1; i++)
            sekvens[i] = sekvens[i+1];

        // Leser neste tegn fra input, neste sekvens klar til levering
        sekvens[n-1] = nesteTegn();

        return S;
    }

    // Leser ett tegn om gangen fra stdin, Konverterer pÃ¥fÃ¸lgende
    // whitespace til Ã©n blank, mÃ¥ da bufre fÃ¸rste ikke-blanke
    // tegn som leses.
    //
    private char nesteTegn()
    {
        // Hvis det ligger et tegn i bufferet, returneres dette
        if (iBuffer)
        {
            iBuffer = false;
            return buffer;
        }

        int c = 0;
        char neste = ' ';
        try
        {
            // Leser neste tegn fra stdin, inkludert linjeskift
            c = System.in.read();
            lestEOF = (c == -1);
            neste = (char) c;

            // Leser forbi all pÃ¥fÃ¸lgende whitespace
            if (!lestEOF && Character.isSpace(neste))
            {
                buffer = neste;
                iBuffer = true;
                neste = ' ';
                while (!lestEOF && Character.isSpace(buffer))
                {
                    c = System.in.read();
                    lestEOF = (c == -1);;
                    buffer = (char) c;
                }
            }
        }
        catch (IOException e)
        {
            lestEOF = true;
        }
        return neste;
    }


    // Testprogram for innlesning av n-sekvenser
    //
    public static void main(String args[])
    {
        int n = 0;

        // Leser lengden pÃ¥ tegnsekvensene fra kommandolinjen
        try
        {
            if (args.length != 1)
                throw new IOException("Angi et heltall");
            n = Integer.parseInt(args[0]);
        }
        catch (Exception e)
        {
            System.err.println(e);
            System.exit(1);
        }

        SekvensLeser sL = new SekvensLeser(n);
        char S[] = new char[n];

        S = sL.nesteSekvens();
        while (S != null)
        {
            System.out.print("'");
            for (int i = 0; i < n; i++)
                System.out.print(S[i]);
            System.out.println("'");;
            S = sL.nesteSekvens();
        }
    }
}
