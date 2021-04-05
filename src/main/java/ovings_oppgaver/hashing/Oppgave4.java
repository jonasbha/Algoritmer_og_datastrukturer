package ovings_oppgaver.hashing;

import java.util.Arrays;

public class Oppgave4 {

    class node
    {
        long foedselsnummer; // Datafelt
        node venstre;        // Peker til venstre barn
        node hoyre;          // Peker til høyre barn
    }

    public static node hashtabell[];

    public static void hash_soeketre(node rot, long n) {
        if (rot == null)
            return;

        int index = simpleFolding((int) rot.foedselsnummer, (int) (n / 0.75));
        insertToTable(rot, n, index);

        hash_soeketre(rot.venstre, n);
        hash_soeketre(rot.hoyre, n);
    }

    private static int neste_primtall(int i) {
        return i;
    }

    private static void insertToTable(node rot, long n, int index) {

        while (hashtabell[index] != null) {
            index++;
            if (index == hashtabell.length)
                index = 0;
        }
        hashtabell[index] = rot;
    }

    public static int simpleFolding(int value, int hashLength) {
        return value % hashLength;
    }

    public static void main(String[] args) {
        int n = 1000;

        int primtall = neste_primtall(n);
        hashtabell = new node[primtall];

        //node Node = new node();

        //hash_soeketre(Node, primtall);
    }
}
