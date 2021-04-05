package obliger.oblig2;

import java.util.Scanner;


public class Sjakkbrett
{
    int LEDIG = 0;
    static int antallTrekk, n;
    static int[][] S;

    public Sjakkbrett(int n) {
        Sjakkbrett.n = n;
        S = new int[n][n];
        antallTrekk = 1;
    }

    boolean finnVei(int i, int j) {

        S[i][j] = antallTrekk;

        //det finnes en vei
        if (antallTrekk == n * n)
            return true;

        // x og y kordinater, tilfeldig rekkefølge på retning
        int[] dI = {-1, 1, -2, 2, -2, -1, 1, 2};
        int[] dJ = {2, 2, 1, 1, -1, -2, -2, -1};

        for (int k = 0; k < 8; k++) {
            int nyI = i + dI[k];
            int nyJ = j + dJ[k];

            // er trekket tillat?
            if (nyI >= 0 && nyI < n && nyJ >= 0 && nyJ < n && S[nyI][nyJ] == LEDIG) {

                antallTrekk++;
                //backtracking
                if (finnVei(nyI, nyJ))
                    return true;
                antallTrekk--;
                S[nyI][nyJ] = LEDIG;
            }
        }

        //det finnes ingen vei
        return false;
    }

    private static void print2dArr(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("[");
            for (int j = 0; j < n; j++)
                System.out.print(S[i][j] + ", ");
            System.out.println("],");
        }
    }

    public static void main(String argv[]) {
        System.out.println("Welcome to the backtracking algorithm of the famous Knight Tour.\n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("size of chess board n? (n x n): ");
        int n = scanner.nextInt();
        System.out.print("position on x-axis? (0-" + n + "): ");
        int x = scanner.nextInt();
        System.out.print("position on y-axis? (0-" + n + "): ");
        int y = scanner.nextInt();

        System.out.println("\nTour possible? " + new Sjakkbrett(n).finnVei(x, y));
        print2dArr(n);
    }
}
