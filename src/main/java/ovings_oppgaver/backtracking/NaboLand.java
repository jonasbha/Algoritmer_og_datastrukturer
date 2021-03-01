package ovings_oppgaver.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NaboLand {
    int[] farge;
    boolean[] brukt;
    static ArrayList<int[]> farger;
    int n;
    boolean[][] felles;

    public NaboLand(int n) {
        this.n = n;
        farger = new ArrayList<>();
        farge = new int[n];
        brukt = new boolean[n];
    }

    public void rekursjon(int index) {

        if (index == n) {
            int[] nyFarge = new int[n];
            System.arraycopy(farge, 0, nyFarge, 0, farge.length);
            farger.add(nyFarge);
        }
        else {
            for (int i = 0; i < n ; i++) {
                if (felles[i][index])
                farge[index] = i + 1;
                rekursjon(index + 1);
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("n: ");
        int n = scanner.nextInt();

        if (n > 0 && n < 9) {
            new NaboLand(n).rekursjon(0);
            for (int[] ints : farger) {
                System.out.println(Arrays.toString(ints));
            }
        }
        else
            System.out.println("Velg et lavere nummer av n.");

    }

}
