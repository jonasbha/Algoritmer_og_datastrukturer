package ovings_oppgaver.recursion;

public class Permutations {

    /*
    som skriver ut tegnene i tegnstrengen s i omvendt rekkefølge til standard output (skjermen).
    Parameteren n angir lengden (eller antall tegn) i s.
    Hvis f.eks. s inneholder "grebiøH", skal metoden skrive ut "Høiberg".
     */
    public static void snu(String s, int n) {
        for (int i = s.length()-1; i >= 0; i--)
            System.out.print(s.charAt(i));
    };


    /*Skriv en rekursiv metode med samme argumenter som metoden i punkt 1,
    som skriver ut alle mulige uordnete utvalg (eller kombinasjoner) av 2 tegn fra den gitte tegnstrengen.
    For f.eks. strengen ACEG skal det skrives ut: AC AE AG CE CG EG */

    static int p;

    public static void perm_recursion(String s) {

        for (int i = 0; i < s.length(); i++)
            if (s.charAt(0) != s.charAt(i)) {
                System.out.print(s.charAt(0) + "" + s.charAt(i) + " ");
                System.out.print(s.charAt(i) + "" + s.charAt(0) + " ");
                p+=2;
            }
        if (s.length() > 1)
            perm_recursion(s.substring(1));
    }

    public static void perm_iteration(String s) {
        for (int i = 0; i < s.length(); i++)
            for (int j = i; j < s.length(); j++)
                if (s.charAt(j) != s.charAt(i)) {
                    System.out.print(s.charAt(j) + "" + s.charAt(i) + " ");
                    System.out.print(s.charAt(i) + "" + s.charAt(j) + " ");
                    p+=2;
                }
    }


    public static void main(String[] args) {
        String s = "Jonas Borge Halvorsen";
        //snu(s, s.length());
        String ss = "Joha";
        p = 0;
        perm_iteration(ss);
        System.out.println("\nPermutasjoner: " + p);
    }
}
