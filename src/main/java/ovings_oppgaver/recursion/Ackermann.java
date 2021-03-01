package ovings_oppgaver.recursion;

// tester maskinens evne til rekursjon
public class Ackermann {

    public static int A(int m, int n) {
        if (m == 0)
            return n + 1;
        if (n == 0)
            return A(m-1, 1);
        return A(m-1, A(m, n-1));
    }

    public static void main(String[] args) {

        // maks før programmet kræsjer: (den kræsjer hvis m > 3 eller m = 3 og n > 12)
        //System.out.println(A(3,12));
    }
}
