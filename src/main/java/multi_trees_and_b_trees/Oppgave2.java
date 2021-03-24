package multi_trees_and_b_trees;

public class Oppgave2 {

    private static class treNode
    {
        int antallVerdier; // Antall verdier i noden, enten 1 eller 2
        int v1, v2;        // Verdiene, v1 < v2 hvis antallVerdier == 2
        treNode venstre, midt, hoyre; // Pekere til barn

        public treNode(int hoyde, int antallVerdier, int v1, int v2, treNode venstre, treNode midt, treNode hoyre) {
            this.antallVerdier = antallVerdier;
            this.v1 = v1;
            this.v2 = v2;
            this.venstre = venstre;
            this.midt = midt;
            this.hoyre = hoyre;
            this.hoyde = hoyde;
        }

        int hoyde; // Høyden av subtreet noden er rot i, til deloppgave c)
    }

    public static void skrivSortert(treNode rot) {

        if (rot == null)
            return;

        skrivSortert(rot.venstre);
        System.out.print(rot.v1 + " ");
        skrivSortert(rot.midt);

        if (rot.antallVerdier == 2) {
            System.out.print(rot.v2 + " ");
            skrivSortert(rot.hoyre);
        }

    }

    public static boolean finnes(treNode rot, int x) {

        if (rot == null)
            return false;

        if (rot.v1 == x || rot.v2 == x)
            return true;

        if (finnes(rot.venstre, x))
            return true;

        if (finnes(rot.midt, x))
            return true;

        return finnes(rot.hoyre, x);
    }

    public static boolean finnes_betterRec(treNode rot, int x) {
        if (rot == null)
            return false;

        if (rot.v1 == x)
            return true;
        if (rot.v1 > x)
            return finnes_betterRec(rot.venstre, x);

        if (rot.antallVerdier == 2) {
            if (rot.v2 == x)
                return true;
            if (rot.v2 < x)
                return finnes_betterRec(rot.hoyre, x);
        }

        return  finnes_betterRec(rot.midt, x);
    }

    public static boolean finnes_i(treNode rot, int x) {
        while (true) {
            if (rot.v1 == x)
                return true;

            if (x < rot.v1)
                if (rot.venstre != null)
                    rot = rot.venstre;
                else return false;
            else if (rot.antallVerdier == 2)
                if (x > rot.v2)
                    if (rot.hoyre != null)
                        rot = rot.hoyre;
                    else return false;
            else
                if (rot.midt != null)
                    rot = rot.midt;
                else return false;
        }
    }


    public static boolean erBtre(treNode rot) {
        int barn = 0;

        if (rot.venstre != null) {
            barn++;
            if (rot.hoyre != null)
                if (rot.venstre.hoyde != rot.hoyre.hoyde)
                    return false;
        }
        if (rot.hoyre != null) {
            barn++;
            if (rot.midt != null)
                if (rot.hoyre.hoyde != rot.midt.hoyde)
                    return false;
        }
        if (rot.midt != null) {
            barn++;
            if (rot.venstre != null)
                if (rot.midt.hoyde != rot.venstre.hoyde)
                    return false;
        }

        if (barn == rot.antallVerdier - 1)
            return false;

        if (rot.venstre != null)
            if (erBtre(rot.venstre))
                return false;
        if (rot.midt != null)
            if (erBtre(rot.midt))
                return false;
        if (rot.hoyre != null)
            return !erBtre(rot.hoyre);

        return true;
    }

    public static void main(String[] args) {
        treNode rot = new treNode(1, 2, 50, 60,
                new treNode(2, 2, 30, 35, null, null, null),
                new treNode(2, 2, 58, 59,
                        new treNode(3, 2, 52, 54, null, null,
                                new treNode(4, 1, 57, 0,
                                        new treNode(5, 2, 55, 56, null, null, null), null, null)), null, null),
                new treNode(2,  2, 63, 70,
                        new treNode(3, 2, 61, 62, null, null, null),
                        new treNode(3, 1, 65, 0,
                                new treNode(4, 1, 64, 0, null, null, null), null,
                                new treNode(4, 1, 66, 0, null, null, null)), null));

        //skrivSortert(rot);
        //System.out.println(finnes(rot, 64));
        //System.out.println(finnes_otherRec(rot, 64));
        //System.out.println(finnes_i(rot, 64));
        //System.out.println(erBtre(rot));
    }
}
