package usecases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Kombinasjon {

    int[] kombinasjon;
    static ArrayList<int[]> kombinasjoner;
    int n;

    public Kombinasjon(int n) {
        this.n = n;
        kombinasjoner = new ArrayList<>();
        kombinasjon = new int[n];
    }

    public void iterasjon_m_3() {
        for (int a = 0; a < n; a++)
            for (int b = 0; b < n; b++)
                for (int c = 0; c < n; c++) {
                    kombinasjon = new int[n];
                    kombinasjon[2] = c+1;
                    kombinasjon[1] = b+1;
                    kombinasjon[0] = a+1;
                    kombinasjoner.add(kombinasjon);
                }
    }

    public void rekursjon(int index) {

        if (index == n) {
            int[] nyKombinasjon = new int[n];
            System.arraycopy(kombinasjon, 0, nyKombinasjon, 0, kombinasjon.length);
            kombinasjoner.add(nyKombinasjon);
        }
        else {
            for (int i = 0; i < n ; i++) {
                // denne indeksen
                kombinasjon[index] = i + 1;
                // for hver indeks
                rekursjon(index + 1);
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("n: ");
        int n = scanner.nextInt();

        if (n > 0 && n < 9) {
            new Kombinasjon(n).rekursjon(0);
            for (int[] ints : kombinasjoner) {
                System.out.println(Arrays.toString(ints));
            }
        }
        else
            System.out.println("Velg et lavere nummer av n.");


        /*
        new Kombinasjon(3).iterasjon_m_3();
        for (int[] ints : kombinasjoner) {
            System.out.println(Arrays.toString(ints));
        }
        */
    }

}
