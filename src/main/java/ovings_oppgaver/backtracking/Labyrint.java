package ovings_oppgaver.backtracking;

import java.util.Random;
import java.util.Scanner;

public class Labyrint
{
    // Verdiene som kan lagres i labyrinten
    int HØYRE = 1;
    int VENSTRE = 2;
    int NED = 3;
    int OPP = 4;
    int STENGT = 0, FRI = 1, BRUKT = 2, VEI = 3;

    // StÃ¸rrelse pÃ¥ kvadratisk labyrint og 2D-tabell som lagrer
    // labyrinten
    int n;
    int L[][];


    // KonstruktÃ¸r som oppretter en tilfeldig labyrint med en gitt
    // prosentandel blokkerte ruter
    public Labyrint(int n, int prosentBlokkert)
    {
        this.n = n;
        L = new int[n][n];
        Random R = new Random();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (R.nextInt(100) < prosentBlokkert)
                    L[i][j] = STENGT;
                else
                    L[i][j] = FRI;
    }


    // Funksjonen finnVei leter rekursivt etter en vei gjennom
    // labyrinten fra rute (i,j) til rute (n-1,n-1).
    // Returnerer true hvis vi fant en slik vei, false ellers
    boolean finnVei(int i, int j)
    {
        // Bunn i rekursjonen: Ferdig hvis vi er i nedre hÃ¸yre hjÃ¸rne
        if (i == n-1 && j == n-1)
        {
            // Markerer at siste rute i labyrinten ligger pÃ¥ veien som
            // er funnet, og returnerer true
            L[i][j] = VEI;
            return true;
        }

        // Markerer at rute (i,j) nÃ¥ er oppsÃ¸kt
        L[i][j] = BRUKT;

        // PrÃ¸ver alle fire mulige lovlige veier videre fra rute (i,j)
        // i denne rekkefÃ¸lgen: 1. HÃ¸yre, 2. Ned , 3. Venstre, 4. Opp


        int dI[] = {  0,  1,  0, -1};
        int dJ[] = {  1,  0, -1,  0};

        for (int k = 0; k < 4; k++)
        {
            int nyI = i + dI[k];
            int nyJ = j + dJ[k];

            // Sjekker om det er lovlig Ã¥ gÃ¥ til ny posisjon
            if (nyI >=0 && nyI < n && nyJ >=0 && nyJ < n && L[nyI][nyJ] == FRI)
            {
                // PrÃ¸ver Ã¥ finne vei videre rekursivt
                if (finnVei(nyI, nyJ))
                {
                    // Her vet vi at det ble funnet en vei gjennom
                    // labyrinten fra rute (i,j). Merker av at (i,j)
                    // ligger pÃ¥ denne veien og stopper deretter videre
                    // leting etter vei ved Ã¥ returnere true
                    if (k == 0)
                        L[i][j] = VEI + HØYRE;
                    else if (k == 1)
                        L[i][j] = VEI + NED;
                    else if (k == 2)
                        L[i][j] = VEI + VENSTRE;
                    else L[i][j] = VEI + OPP;

                    return true;
                }
            }
        }
        // Hvis vi kommer hit i koden, fantes det ingen vei gjennom
        // labyrinten fra rute (i,j), returnerer false
        return false;
    }


    // GjÃ¸r om labyrinten til en tekststreng for utskrift, markerer
    // evt. funnet vei med stjerner ('*')
    public String toString()
    {
        StringBuilder result = new StringBuilder("\n");
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
                if (L[i][j] == VEI)
                        result.append("* ");
                else if (L[i][j] == VEI + HØYRE)
                    result.append("> ");
                else if (L[i][j] == VEI + VENSTRE)
                    result.append("< ");
                else if (L[i][j] == VEI + OPP)
                    result.append("^ ");
                else if (L[i][j] == VEI + NED)
                    result.append("v ");
                else
                    result.append(".").append(" ");
            result.append("\n");
            // evt +/- for prøvd/uprøvd
        }
        return result.toString();
    }


    // Testprogram
    public static void main(String argv[])
    {
        // Leser stÃ¸rrelsen pÃ¥ labyrinten og prosentandel blokkerte ruter
        Scanner scanner = new Scanner(System.in);
        System.out.print("n?: ");
        int n = scanner.nextInt();
        System.out.print("% blokkerte ruter?: ");
        int prosentBlokkert = scanner.nextInt();

        // Oppretter ny labyrint
        Labyrint lab = new Labyrint(n, prosentBlokkert);

        // Leter etter vei fra Ã¸vre venstre hjÃ¸rne
        boolean funnetVei = lab.finnVei(0, 0);

        // Skriver ut labyrinten (og evt. funnet vei)
        System.out.println(lab);
        if (!funnetVei)
            System.out.println("Fant ingen vei gjennom labyrinten");
    }
}
