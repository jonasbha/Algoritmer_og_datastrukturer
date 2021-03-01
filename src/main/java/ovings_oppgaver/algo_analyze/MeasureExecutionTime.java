package ovings_oppgaver.algo_analyze;

/*
Skriv et program som tester hvor lang tid maskinen bruker for å utføre hhv.:
n log(n), n2, n3, n5, 2n og n! addisjoner for n = 10, 20, 30,... 90, 100.

O(log(n)) = løkken går like mange ganger som n er delelig med to. Da log(n) menes som log(n)2
* */

public class MeasureExecutionTime {

    public static void main(String[] args) {
        double time, to_i_nte = 1, n_fak = 1;

        for (int n = 10; n <= 100; n += 10)
        {
            // ikke vits i å prøve disse verdiene, tar for lang tid.
            for (int i = 0; i < 10; i++)
            {
                to_i_nte *= 2;
                n_fak *= (n - i);
            }

            time = System.currentTimeMillis();
            measureTime(n * n * n);

            time = System.currentTimeMillis() - time;
            System.out.println("n = " + n + ", Time = " + time + " ms");
        }
    }

    private static void measureTime(double order) {
        int workload = 0;
        for (int i = 0; i < order; i++)
            workload += i * i;
    }
}
