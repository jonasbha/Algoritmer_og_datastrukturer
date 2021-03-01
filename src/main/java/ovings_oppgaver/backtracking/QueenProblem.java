package ovings_oppgaver.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class QueenProblem {

    int[] løsning;
    static ArrayList<int[]> løsninger;
    boolean[] brukt;
    int n;

    public QueenProblem(int n) {
        this.n = n;
        løsninger = new ArrayList<>();
        løsning = new int[n];

        brukt = new boolean[n];
        Arrays.fill(brukt, false);
    }

    public void lagLøsning(int rad) {

        if (rad == n) {
            int[] nyLøsning = new int[n];
            System.arraycopy(løsning, 0, nyLøsning, 0, løsning.length);
            løsninger.add(nyLøsning);
        }
        else {
            for (int kol = 0; kol < n; kol++) {
                if (!brukt[kol] && lovligPlassering(rad, kol)) {
                    løsning[rad] = kol;
                    brukt[kol] = true;
                    lagLøsning(rad + 1);
                    brukt[kol] = false;
                }
            }
        }
    }

    boolean lovligPlassering(int rad, int kol)
    {
        // Sjekker om dronningene i ovenstÃ¥ende rader kan
        // slÃ¥ utplassert dronning diagonalt

        int k1 = kol, k2 = kol;
        for (int r = rad-1; r >= 0; r--)
        {
            k1--;
            k2++;
            if (k1 >= 0 && løsning[r] == k1)
                return false;
            if (k2 < n && løsning[r] == k2)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("n: ");
        int n = scanner.nextInt();

        if (n > 0 && n < 9) {
            new QueenProblem(n).lagLøsning(0);
            for (int[] ints : løsninger) {
                System.out.println(Arrays.toString(ints));
            }
        }
        else
            System.out.println("Velg et lavere nummer av n.");

    }

}
