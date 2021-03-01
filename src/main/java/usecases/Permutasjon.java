package usecases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Permutasjon {

    int[] permutasjon;
    static ArrayList<int[]> permutasjoner;
    boolean[] brukt;
    int n;

    public Permutasjon(int n) {
        this.n = n;
        permutasjoner = new ArrayList<>();
        permutasjon = new int[n];

        brukt = new boolean[n];
        Arrays.fill(brukt, false);
    }

    public void rekursjon(int index) {

        if (index == n) {
            int[] nyPermutasjon = new int[n];
            System.arraycopy(permutasjon, 0, nyPermutasjon, 0, permutasjon.length);
            permutasjoner.add(nyPermutasjon);
        }
        else {
            for (int i = 0; i < n; i++) {
                if (!brukt[i]) {
                    permutasjon[index] = i + 1;
                    brukt[i] = true;
                    rekursjon(index + 1);
                    brukt[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("n: ");
        int n = scanner.nextInt();

        if (n > 0 && n < 9) {
            new Permutasjon(n).rekursjon(0);
            for (int[] ints : permutasjoner) {
                System.out.println(Arrays.toString(ints));
            }
        }
        else
            System.out.println("Velg et lavere nummer av n.");

    }

}
