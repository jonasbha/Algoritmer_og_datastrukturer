package ovings_oppgaver.backtracking;

import java.util.*;

public class Ønskeliste {
    static HashMap<int[], Integer> ps;
    static int[][] ønsker;
    boolean[] brukt;
    int n;
    static int ønskerOppfylt;

    public Ønskeliste(int n) {
        this.n = n;
        Ønskeliste.ønsker = new int[n][3];
        Ønskeliste.ps = new HashMap<>();
        ønskerOppfylt = 0;
        brukt = new boolean[n];
        Arrays.fill(brukt, false);
        lagLister(n);
    }

    public boolean delUtGaver(int barn) {

        if (barn == n)
            return true;

        for (int leke = 0; leke < 3; leke++) {

            if (!brukt[ønsker[barn][leke]]) {

                brukt[ønsker[barn][leke]] = true;
                ps.put(ønsker[barn], ønsker[barn][leke]);
                if (delUtGaver(barn + 1))
                    return true;
                brukt[ønsker[barn][leke]] = false;
            }
        }
        ønskerOppfylt = ps.size();
        return false;
    }

    private static void lagLister(int n) {
        for (int i = 0; i < n; i++)
            ønsker[i] = lagListe(n, 3);
    }

    private static int[] lagListe(int ant,  int maks) {
        int[] arr = new int[maks];
        Random r = new Random();

        for (int i = 0; i < maks; i++)
            arr[i] = r.nextInt(ant);

        for (int i = 0; i < maks; i++)
            for (int j = i + 1; j < maks; j++)
                if (arr[i] == arr[j])
                    return lagListe(ant, maks);
        return arr;
    }

    private static void skrivUt() {
        for (Map.Entry<int[], Integer> entry : ps.entrySet()) {
            int[] key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(Arrays.toString(key) + ": " + value);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("n: ");
        int n = scanner.nextInt();

        Boolean granted = new Ønskeliste(n).delUtGaver(0);
        System.out.println("Alle barna får en gave på ønskelisten:\n" + granted);
        if (!granted)
            System.out.println("Only " + ønskerOppfylt + " wishes were granted");
        skrivUt();
    }
}
