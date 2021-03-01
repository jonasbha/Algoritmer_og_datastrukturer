package tools;

public class Tidsmåling {
    public void execute() {

        for (int n = 10000000; n <= 1000000000; n += 10000000) {

            long start = System.currentTimeMillis();
            //funksjon()
            long end = System.currentTimeMillis();
            long time = end - start;

            System.out.println("n=" + n + " \ntt=" + time + "\tms");
        }
    }
}
