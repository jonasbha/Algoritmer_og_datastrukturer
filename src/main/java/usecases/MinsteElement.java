package usecases;

public class MinsteElement {

    public static void main(String[] args) {

    }

    public static int finnMin(int[] tab, int n)
    {
        int min = tab[0];            // 1
        for (int i = 1; i < n ; i++) // 2, 3, 3.2
        {
            if (tab[i] < min )       // 3.1
            {
                min = tab[i];        // 3.1.1
            }
        }
        return min;                  // 4
    }
}
