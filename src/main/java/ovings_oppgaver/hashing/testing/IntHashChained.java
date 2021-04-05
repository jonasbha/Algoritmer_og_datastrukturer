package ovings_oppgaver.hashing.testing;

// Hashing av heltall med kjeding i lenket liste
// Bruker Javas innebygde hashfunksjon for wrapper-klassen Integer
//
// Enkel og begrenset implementasjon:
//
// - Ingen rehashing ved full tabell/lange lister
// - Tilbyr bare innsetting og sÃ¸king

public class IntHashChained
{

    // Indre klasse:
    // Node med data, kjedes sammen i lenkede lister
    private class hashNode
    {
        int data;        // Data, et heltall
        hashNode neste;  // Neste node i listen

        // KonstruktÃ¸r for listenoder
        public hashNode(int i, hashNode hN)
        {
            data = i;
            neste = hN;
        }
    }

    private int hashLengde;        // Hashlengde
    private hashNode hashTabell[]; // Hashtabell, pekere til lister
    private int n;                 // Antall elementer lagret i tabellen

    // KonstruktÃ¸r
    // Sjekker ikke for fornuftig verdi av hashlengden
    public IntHashChained(int lengde)
    {
        hashLengde = lengde;
        hashTabell = new hashNode[lengde];
        n = 0;
    }

    // Returnerer load factor
    public float loadFactor()
    {
        return ((float) n)/hashLengde;
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

    // Innsetting av heltall med kjeding
    void insert(int i)
    {
        // Beregner hashverdien
        int h = hash(i);
        // Ã˜ker antall elementer som er lagret
        n++;
        // Setter inn ny node fÃ¸rst i listen
        hashTabell[h] = new hashNode(i, hashTabell[h]);
    }


    // SÃ¸king etter et heltall i hashtabell med kjeding
    // Returnerer true hvis tallet er lagret, false ellers

    boolean search(int i)
    {
        // Finner listen som tallet skal ligge i
        hashNode hN = hashTabell[hash(i)];
        // Leter gjennom listen
        while (hN != null)
        {
            // Har vi funnet tallet?
            if (hN.data == i)
                return true;
            // PrÃ¸ver neste
            hN = hN.neste;
        }
        // Finner ikke tallet, har kommet til slutten av listen
        return false;
    }

}
