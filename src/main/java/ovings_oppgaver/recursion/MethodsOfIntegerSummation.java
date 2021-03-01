package ovings_oppgaver.recursion;

public class MethodsOfIntegerSummation {

    public static int sumWithRecursion(int n) {
        if (n == 0)
            return 0;
        return (n + sumWithRecursion(n - 1));
    }

    public static int sumWithLoop(int n) {
        for (int i = 1; i <= n; i++)
            sum+=i;
        return sum;
    }

    // Bruk formelen S(n) = n · (n+1)/2
    public static int sumWeird(int n) {
          return  n * (n + 1) / 2;
    }


    static int sum;
    public static void main(String[] args) {
        sum = 0;
        System.out.println(sumWithLoop(4)); // 10
        sum = 0;
        System.out.println(sumWithRecursion(4));
        sum = 0;
        System.out.println(sumWeird(4));
    }
}
