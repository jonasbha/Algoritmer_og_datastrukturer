package ovings_oppgaver.recursion;

public class ImplementBinomial {

    public static int C(int n, int m) {
        if (m == 0 || m == n)
            return 1;
        else
            return  C(n-1, m) + C(n-1, m-1);
    }

    public static void main(String[] args) {
        System.out.println(C(6, 4));
    }
}
// m! / n!(m-n)!
// return m-1 / (n-1 * (m-n)-1)