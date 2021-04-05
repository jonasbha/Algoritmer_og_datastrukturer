package ovings_oppgaver.hashing.testing;

import java.io.*;
import java.util.Random;

// Hashing av heltall med kvadratisk probing
//
// Enkel og begrenset implementasjon:
//
// - Ingen rehashing ved full tabell
// - Tilbyr bare innsetting og sÃ¸king

public class IntHashQuadratic
{
    // Hashlengde
    private int hashLengde;

    // Hashtabell med Integer-objekter
    private Integer hashTabell[];

    // Antall elementer lagret i tabellen
    private int n;

    // KonstruktÃ¸r
    // Sjekker ikke for fornuftig verdi av hashlengden
    //
    public IntHashQuadratic(int lengde)
    {
        hashLengde = lengde;
        hashTabell = new Integer[lengde];
        n = 0;
    }

    // Returnerer load factor
    public float loadFactor()
    {
        return ((float) n)/((float) hashLengde);
    }

    // Returnerer antall data i tabellen
    public int antData()
    {
        return n;
    }

    // Hashfunksjon som bruker Javas innebygde beregning av hashverdier
    int hash(int i)
    {
        Integer I = new Integer(i);
        int h = Math.abs(I.hashCode());
        return h % hashLengde;
    }

    // Innsetting av heltall med kvadratisk probing
    // Avbryter med feilmelding hvis ledig plass ikke finnes
    //
    void insert(int i)
    {
        // Beregner hashverdien
        int h = hash(i);

        // Kvadratisk probing
        int add = 0;
        int neste = h;

        while (hashTabell[neste] != null)
        {
            // Denne indeksen er opptatt, prÃ¸ver neste
            add++;
            neste = h + (add * add);

            // Wrap-around
            if (neste >= hashLengde)
                neste = neste % hashLengde;

            // Hvis vi er kommet tilbake til opprinnelig hashverdi, er
            // tabellen full og vi gir opp (her ville man normalt
            // doblet lengden pÃ¥ hashtabellen og gjort en rehashing)
            if (neste == h)
            {
                System.err.println("\nHashtabell full, avbryter");
                System.exit(0);
            }
        }

        // Lagrer heltallet pÃ¥ funnet indeks
        hashTabell[neste] = new Integer(i);

        // Ã˜ker antall elementer som er lagret
        n++;
    }

    // SÃ¸king etter et heltall med kvadratisk probing
    // Returnerer true hvis tallet er lagret, false ellers
    //
    boolean search(int i)
    {
        // Beregner hashverdien
        int h = hash(i);

        // Kvadratisk probing
        int neste = h, add = 0;

        while (hashTabell[neste] != null)
        {
            // Har vi funnet tallet?
            if (hashTabell[neste].intValue() == i)
                return true;

            // PrÃ¸ver neste mulige
            add++;
            neste = h + (add * add);

            // Wrap-around
            if (neste >= hashLengde)
                neste = neste % hashLengde;

            // Hvis vi er kommet tilbake til opprinnelig hashverdi,
            // finnes ikke tallet i tabellen
            if (neste == h)
                return false;
        }

        // Finner ikke tallet, har kommet til en probe som er null
        return false;
    }

}