package ovings_oppgaver.backtracking;

public class QueenMultipleSolutions
{
    int n;           // ProblemstÃ¸rrelse
    int p[];         // Lagrer permutasjonene
    boolean brukt[]; // Merker av brukte tall
    int antLoes;     // Antall lÃ¸sninger

    // KonstruktÃ¸r
    public QueenMultipleSolutions(int n)
    {
        this.n = n;
        p = new int[n];
        brukt = new boolean[n];
        for (int i = 0; i < n; i++)
            brukt[i] = false;
        antLoes = 0;
    }

    void lagLoesning(int rad)
    {
        // Lager alle mulige lÃ¸sninger pÃ¥ dronningproblemet fra og med
        // denne raden og ned til rad n-1. Det er allerede satt ut
        // dronninger pÃ¥ radene 0, 2, ..., rad-1

        if (rad == n)
        {
            // Laget ferdig en lÃ¸sning
            antLoes++;
            skrivLoesning();
        }
        else
        {
            // Setter etter tur dronninger inn pÃ¥ alle ledige
            // kolonner i denne raden, sjekker om den kan
            // slÃ¥s av dronningene ovenfor, hvis ikke gÃ¥r vi
            // rekursivt videre til neste rad

            for (int kol = 0; kol < n; kol++)
            {
                if (!brukt[kol] && lovligPlassering(rad, kol))
                {
                    p[rad] = kol;
                    brukt[kol] = true;

                    lagLoesning(rad + 1);

                    brukt[kol] = false;
                }
            }
        }
    }

    boolean lovligPlassering(int rad, int kol)
    {
        // Sjekker om dronningene i ovenstÃ¥ende rader kan
        // slÃ¥ utplassert dronning diagonalt

        int k1 = kol, k2 = kol;
        for (int r = rad-1; r >= 0; r--)
        {
            k1--;
            k2++;
            if (k1 >= 0 && p[r] == k1)
                return false;
            if (k2 < n && p[r] == k2)
                return false;
        }
        return true;
    }

    void skrivLoesning()
    {
        for (int i = 0; i < n; i++)
            System.out.print((p[i]+1) + " ");
        System.out.println();
    }

    public static void main(String args[])
    {
        // Leser n fra kommandolinjen
        int n = 9;

        // Skriver ut alle lÃ¸sninger pÃ¥ dronningproblemet
        if (n > 0 && n < 16)
        {
            QueenMultipleSolutions D = new QueenMultipleSolutions(n);
            D.lagLoesning(0);
            System.out.println("Antall lÃ¸sninger: " + D.antLoes);
        }
        else
            System.out.println("Bruk 0 < n < 16");
    }
}