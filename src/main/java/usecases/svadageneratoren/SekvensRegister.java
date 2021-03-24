package usecases.svadageneratoren;

public class SekvensRegister
{
    // Lengde av tegnsekvenser
    private int n;

    // Roten i sekvenstreet
    private sekvensNode rot;

    // KonstruktÃ¸r for sekvensRegister
    public SekvensRegister(int sekvensLengde)
    {
        rot = null;
        n = sekvensLengde;
    }

    // Indre klasse:
    // Node i binÃ¦rt sÃ¸ketre for lagring av tegnsekvenser
    //
    private class sekvensNode
    {
        // Tegnsekvens
        char sekvens[];

        // Register som brukes til Ã¥ lagre alle forekomster av
        // etterfÃ¸lgertegn til denne sekvensen
        EtterfolgerRegister eR;

        // HÃ¸yre og venstre barn i sekvenstreet
        sekvensNode venstre, hoyre;

        // KonstruktÃ¸r, legger inn ny sekvens og fÃ¸rste etterfÃ¸lger
        //
        public sekvensNode(char S[], char e)
        {
            // Kopierer innholdet i sekvensen
            sekvens = new char[n];
            for (int i = 0; i < n; i++)
                sekvens[i] = S[i];

            // Oppretter nytt etterfÃ¸lgerregister og setter inn fÃ¸rste
            // etterfÃ¸lgertegn i dette
            eR = new EtterfolgerRegister();
            eR.registrer(e);

            venstre = hoyre = null;
        }
    }

    // Legger inn ny forekomst av en n-sekvens med etterfÃ¸lgertegn i
    // sekvenstreet
    //
    public void registrer(char[] S, char e)
    {

        if (rot == null)
        // Treet er tomt, setter inn fÃ¸rste sekvensnode
        {
            rot = new sekvensNode(S, e);
            return;
        }

        sekvensNode denne = rot;
        boolean ferdig = false;
        int resultat = sammenlignSekvens(S, denne.sekvens);

        // LÃ¸kke som enten setter inn ny sekvensnode og etterfÃ¸lger i
        // sÃ¸ketreet, eller registrerer ny forekomst av etterfÃ¸lger
        // for en allerede eksisterende sekvens
        while (!ferdig)
        {
            if (resultat == -1)
            {
                if (denne.venstre == null)
                // Setter inn ny sekvens og etterfÃ¸lger
                {
                    denne.venstre = new sekvensNode(S, e);
                    ferdig = true;
                }
                else
                    // Fortsetter til venstre i sÃ¸ketreet
                    denne = denne.venstre;
            }
            else if (resultat == 1)
            {
                if (denne.hoyre == null)
                // Setter inn ny sekvens og etterfÃ¸lger
                {
                    denne.hoyre = new sekvensNode(S, e);
                    ferdig = true;
                }
                else
                    // Fortsetter til hÃ¸yre i sÃ¸ketreet
                    denne = denne.hoyre;
            }
            else
            // Sekvensen finnes fra fÃ¸r, legger inn ny forekomst av
            // etterfÃ¸lger
            {
                denne.eR.registrer(e);
                ferdig = true;
            }
            if (!ferdig)
                resultat = sammenlignSekvens(S, denne.sekvens);
        }
    }

    // Sammenligning av to n-sekvenser
    //
    private int sammenlignSekvens(char[] S1, char[] S2)
    {
        for (int i = 0; i < n; i++)
        {
            if (S1[i] < S2[i])
                return -1;
            if (S1[i] > S2[i])
                return 1;
        }
        return 0;
    }

    // Trekker tilfeldig et etterfÃ¸lgertegn til en sekvens som er
    // registrert i sekvenstreet
    //
    public char trekkEtterfolger(char[] S)
    {
        sekvensNode denne = rot;
        int resultat = sammenlignSekvens(S, denne.sekvens);
        boolean ferdig = false;

        // LÃ¸kke som finner den gitte sekvensen i treet
        while (!ferdig)
        {
            if (resultat == -1)
            {
                if (denne.venstre == null)
                // Sekvensen vi leter etter finnes ikke i treet, dette
                // kan skje hvis en tegnsekvens bare forekommer en gang
                // helt pÃ¥ slutten av input, slik at den ikke har blitt
                // registrert med noen etterfÃ¸lger
                {
                    System.err.println("\nFinner ikke sekvensen: '"
                            + new String(S) + "'");
                    System.exit(0);
                }
                else
                    // Sekvensen vi leter etter ligger til venstre
                    denne = denne.venstre;
            }
            else if (resultat == 1)
            {
                if (denne.hoyre == null)
                // Sekvensen vi leter etter finnes ikke i treet, dette
                // kan skje hvis en tegnsekvens bare forekommer en gang
                // helt pÃ¥ slutten av input, slik at den ikke har blitt
                // registrert med noen etterfÃ¸lger
                {
                    System.err.println("\nFinner ikke sekvensen: '"
                            + new String(S) + "'");
                    System.exit(0);
                }
                // Sekvensen vi leter etter ligger til hÃ¸yre
                else
                    denne = denne.hoyre;
            }
            else
                // Funnet sekvensen i treet
                ferdig = true;

            if (!ferdig)
                resultat = sammenlignSekvens(S, denne.sekvens);
        }

        // Trekker og returnerer et etterfÃ¸lgertegn
        return denne.eR.trekk();
    }


    // Testprogram for registrering av sekvenser og trekking av
    // etterfÃ¸lgere. Leser en tekst og registrerer alle 3-sekvenser
    //
    public static void main(String args[])
    {
        int n = 3;
        SekvensLeser sL = new SekvensLeser(n);
        SekvensRegister sR = new SekvensRegister(n);

        // Leser de to fÃ¸rste sekvensene fra input
        char S1[] = sL.nesteSekvens();
        char S2[] = sL.nesteSekvens();

        // Leser og registrerer alle sekvenser og etterfÃ¸lgere i input
        while (S2 != null)
        {
            // Siste tegn i andre sekvens er etterfÃ¸lgertegnet til
            // fÃ¸rste sekvens
            char e = S2[n-1];

            // Registrerer ny forekomst av sekvens med etterfÃ¸lger
            sR.registrer(S1, e);

            // Leser inn neste sekvens
            S1 = S2;
            S2 = sL.nesteSekvens();
        }

        // Inorder utskrift av treet
        sR.skriv();
    }

    // Utskrift av sekvenstre, for testing
    //
    public void skriv()
    {
        skriv(rot);
    }
    private void skriv(sekvensNode rot)
    {
        if (rot != null)
        {
            skriv(rot.venstre);

            for (int i = 0; i < n; i++)
                System.out.print(rot.sekvens[i]);
            System.out.print(": ");
            rot.eR.skriv();

            skriv(rot.hoyre);
        }
    }
}
