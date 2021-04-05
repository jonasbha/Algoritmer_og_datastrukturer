package ovings_oppgaver.hashing.testing;

import java.io.*;
import java.util.Scanner;

// Hashing av heltall med lineÃ¦r probing
// Bruker Javas innebygde hashfunksjon for wrapper-klassen Integer
//
// Enkel og begrenset implementasjon:
//
// - Ingen rehashing ved full tabell
// - Tilbyr bare innsetting og sÃ¸king

public class IntHashLinear
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
    public IntHashLinear(int lengde)
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

    // Hashfunksjon
    int hash(int i)
    {
        Integer I = new Integer(i);
        int h = Math.abs(I.hashCode());
        return h % hashLengde;
    }

    // Innsetting av heltall med lineÃ¦r probing
    // Avbryter med feilmelding hvis ledig plass ikke finnes
    //
    void insert(int i)
    {
        // Beregner hashverdien
        int h = hash(i);

        // LineÃ¦r probing
        int neste = h;

        while (hashTabell[neste] != null)
        {
            // Denne indeksen er opptatt, prÃ¸ver neste
            neste++;

            // Wrap-around
            if (neste >= hashLengde)
                neste = 0;

            // Hvis vi er kommet tilbake til opprinnelig hashverdi, er
            // tabellen full og vi gir opp (her ville man normalt
            // doblet lengden pÃ¥ hashtabellen og gjort en rehashing)
            if (neste == h)
            {
                System.err.println("\nHashtabell full, avbryter");
                System.exit(0);
            }
        }

        // Lagrer tallet pÃ¥ funnet indeks
        hashTabell[neste] = new Integer(i);

        // Ã˜ker antall elementer som er lagret
        n++;
    }


    // SÃ¸king etter heltall med lineÃ¦r probing
    // Returnerer true hvis tallet er lagret, false ellers
    //
    boolean search(int i)
    {
        // Beregner hashverdien
        int h = hash(i);

        // LineÃ¦r probing
        int neste = h;

        while (hashTabell[neste] != null)
        {
            // Har vi funnet tallet?
            if (hashTabell[neste].intValue() == i)
                return true;

            // PrÃ¸ver neste mulige
            neste++;

            // Wrap-around
            if (neste >= hashLengde)
                neste = 0;

            // Hvis vi er kommet tilbake til opprinnelig hashverdi,
            // finnes ikke tallet i tabellen
            if (neste == h)
                return false;
        }

        // Finner ikke tallet, har kommet til en probe som er null
        return false;
    }

}
