package usecases;

public class PotensBeregning {

    public static void main(String[] args) {
        System.out.println(potens1(2, 5)); // simpel algoritme
        System.out.println(potens2(2, 5)); // effektiv algoritme
        System.out.println(potens3(2, 5)); // rekursiv algoritme
    }

    // operasjoner:
    static double potens1(double x, long n) {// 2 parameteroverføringer
        double result = 1.0; //2 tilordninger
        for (long i = 1; i <= n; i++)//1 tilording, 1 sammenligning, 1 addisjon
            result *= x;//1 tilordning, 1 multiplikasjon
        return result;//1 retur av verdi
    }

    public static double potens2(double x, long n)
    {
        // Beregner x^n med log_n multiplikasjoner iterativt

        double result = 1.0;

        if (n == 0)
            return result;

        result = x;
        while (n > 1)
        {
            result = result * result;
            if (n % 2 != 0)
                result = result * x;
            n = n / 2;
        }
        return result;
    }

    public static double potens3(double x, long n)
    {
        // Beregner x^n med log_n multiplikasjoner rekursivt

        if (n == 0)
            return 1.0;
        else
        {
            if (n % 2 == 0)
                return potens3(x*x, n/2);
            else
                return potens3(x*x, n/2) * x;
        }
    }
}
