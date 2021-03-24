package usecases.svadageneratoren;

import java.util.Random;

public class EtterfolgerRegister
{
    // Roten i etterfÃ¸lgertreet
    private etterfolgerNode rot;

    // Brukes til tilfeldig trekking av tegn fra treet
    private Random R;

    // KonstruktÃ¸r for etterfÃ¸lgertre
    public EtterfolgerRegister()
    {
        rot = null;
        R = new Random();
    }

    // Indre klasse:
    // Node i binÃ¦rt sÃ¸ketre for lagring av etterfÃ¸lgertegnene til en
    // n-sekvens
    //
    private class etterfolgerNode
    {
        // EtterfÃ¸lgertegnet
        char tegn;

        // Totalt antall forekomster av tegn i subtreet der denne
        // noden er rot
        int totAntall;

        // HÃ¸yre og venstre barn i etterfÃ¸lgertreet
        etterfolgerNode venstre, hoyre;

        // KonstruktÃ¸r, legger inn nytt tegn og en forekomst
        public etterfolgerNode(char c)
        {
            tegn = c;
            totAntall = 1;
            venstre = hoyre = null;
        }
    }

    // Legger inn ny forekomst av et tegn som har etterfulgt en
    // n-sekvens.
    //
    public void registrer(char c)
    {
        // Tomt tre?
        if (rot == null)
            rot = new etterfolgerNode(c);
        else
        {
            etterfolgerNode denne = rot;
            boolean ferdig = false;

            while (!ferdig)
            {
                // Antall forekomster av tegn i subtreet med rot i
                // "denne" Ã¸kes med 1
                denne.totAntall++;

                if (c < denne.tegn)
                // Tegnet ligger til venstre i etterfÃ¸lgertreet
                {
                    if (denne.venstre == null)
                    // Tegnet er ikke registrert tidligere, opprett ny
                    // node
                    {
                        denne.venstre = new etterfolgerNode(c);
                        ferdig = true;
                    }
                    else
                        denne = denne.venstre;
                }
                else if (c > denne.tegn)
                // Tegnet ligger til hÃ¸yre i etterfÃ¸lgertreet
                {
                    if (denne.hoyre == null)
                    // Tegnet er ikke registrert tidligere, opprett ny
                    // node
                    {
                        denne.hoyre = new etterfolgerNode(c);
                        ferdig = true;
                    }
                    else
                        denne = denne.hoyre;
                }
                else
                    // Tegnet er lagret i denne noden
                    ferdig = true;
            }
        }
    }

    // Trekker tilfeldig et tegn fra treet, slik at frekvensene fra
    // lest tekst bevares
    //
    public char trekk()
    {
        int tall = R.nextInt(rot.totAntall) + 1;
        return trekkTegn(rot, tall);
    }

    // Intern rekursiv metode som trekker et tegn fra treet
    //
    private char trekkTegn(etterfolgerNode rot, int tall)
    {
        if (rot.venstre != null)
            if (tall <= rot.venstre.totAntall)
                return trekkTegn(rot.venstre, tall);

        if (rot.hoyre != null)
            if (tall > rot.totAntall - rot.hoyre.totAntall)
                return trekkTegn(rot.hoyre,
                        tall - (rot.totAntall - rot.hoyre.totAntall));
        return rot.tegn;
    }


    // Testprogram for registrering og trekking av etterfÃ¸lgertegn
    //
    public static void main(String args[])
    {
        EtterfolgerRegister eR = new EtterfolgerRegister();

        /* Lager dette treet:
         *
         *           d 14 (2)
         *          /    \
         *         /      \
         *        /        \
         *       /          \
         *     b 5 (1)    f 7 (1)
         *     /    \      /   \
         *   a 3    c 1  e 2   g 4 (3)
         *                       \
         *                       h 1
         */

        // Bygger opp treet
        eR.registrer('d');
        eR.registrer('b');
        eR.registrer('a');
        eR.registrer('c');
        eR.registrer('f');
        eR.registrer('e');
        eR.registrer('g');
        eR.registrer('h');
        eR.registrer('a');
        eR.registrer('a');
        eR.registrer('d');
        eR.registrer('e');
        eR.registrer('g');
        eR.registrer('g');

        // Inorder utskrift av treet
        eR.skriv();

        // Skriver ut 50 tilfeldige tegn fra treet
        for (int i = 0; i < 50; i++)
            System.out.print(eR.trekk());
        System.out.println();
    }

    // Utskrift av etterfÃ¸lgertre, for testing
    //
    public void skriv()
    {
        skriv(rot);
        System.out.println();
    }
    private void skriv(etterfolgerNode rot)
    {
        if (rot != null)
        {
            skriv(rot.venstre);

            int antallVenstre = (rot.venstre == null) ? 0 : rot.venstre.totAntall;
            int antallHoyre = (rot.hoyre == null) ? 0 : rot.hoyre.totAntall;
            System.out.print(rot.tegn + "-" + rot.totAntall + "(" +
                    (rot.totAntall - antallVenstre - antallHoyre) + ") ");

            skriv(rot.hoyre);
        }
    }
}
