package ovings_oppgaver.binary_trees;

public class Oppgave4 {

    class tre_node
    {
        public tre_node() {}   // Konstruktør, skal ikke programmeres
        int verdi;              // Heltallsverdi som lagres i hver node
        char bokstav;	      // Bokstav/tegn som lagres i hver node
        int sum; 		      // Brukes bare i oppgave c)
        tre_node v, h;	      // Venstre og høyre barn
        tre_node forelder;      // Brukes bare i oppgave d)
    }

    void skriv_bokstav_rek(tre_node rot, int x) {

        if (rot != null) {
            if (x == rot.verdi)
                System.out.println(rot.verdi);

            if (x < rot.verdi)
                skriv_bokstav_rek(rot.v, x);
            else
                skriv_bokstav_rek(rot.h, x);
        }
    }

    void skriv_bokstav_itt(tre_node rot, int x) {

        while (rot != null) {
            if (rot.verdi == x)
                System.out.println(rot.verdi);

            if (x < rot.verdi)
                rot = rot.v;

            if (x > rot.verdi)
                rot = rot.h;
        }
    }

    //b) O(log n) hvis balansert
    // O(n)


    void sett_sum(tre_node rot) {


        if (rot != null) {

            sett_sum(rot.v);
            sett_sum(rot.h);

            rot.sum += rot.verdi;
            if (rot.v != null) {
                rot.sum += rot.v.sum;
            }
            if (rot.h != null) {
                rot.sum += rot.h.sum;
            }
        }
    }

    void sett_forelder(tre_node rot) {


        if (rot != null) {

            if (rot.v != null) {
                rot.v.forelder = rot;
            }
            if (rot.h != null) {
                rot.h.forelder = rot;
            }

            sett_forelder(rot.v);
            sett_forelder(rot.h);

            rot.forelder = null;
        }
    }

    boolean erAVL(tre_node rot) {

        if (rot == null)
            return true;

        if (rot.v != null) {
            if (rot.v.verdi > rot.verdi)
                return false;
            if (!erAVL(rot.v))
                return false;
        }
        if (rot.h != null) {
            if (rot.h.verdi < rot.verdi)
                return false;
            if (!erAVL(rot.h))
                return false;
        }
        return true;
    }
}
