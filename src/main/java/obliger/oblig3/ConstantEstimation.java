package obliger.oblig3;

import java.util.Random;

public class ConstantEstimation {

    public ConstantEstimation(int numTests) {
        this.numTests = numTests;
    }

    private final int numTests;
    private float time;
    private float total = 0;

    public float insertionSort(int n) {
        for (int i = 0; i < numTests; i++) {
            int[] arr = createArray(n);

            time = System.nanoTime();
            new SequentialSorting().insertionSort(arr);
            time = (System.nanoTime() - time);
            total += time;
        }
        total = total / numTests;

        return total / ((n*n) + n);
    }

    public float quickSort(int n) {
        for (int i = 0; i < numTests; i++) {
            int[] arr = createArray(n);

            time = System.nanoTime();
            new LogarithmicSorting().quickSort(arr, 0, arr.length - 1);
            time = (System.nanoTime() - time);
            total += time;
        }
        total = total / numTests;

        if (n > 1000000)
            return (float) (total / (n * Math.log(n)));
        else
            return (float) (total / ((n * Math.log(n)) + n));
    }

    public float quickSort_efficient(int n) {
        for (int i = 0; i < numTests; i++) {
            int[] arr = createArray(n);

            time = System.nanoTime();
            new LogarithmicSorting().quickSort_efficient(arr, 0, arr.length - 1);
            time = (System.nanoTime() - time);
            total += time;
        }
        total = total / numTests;

        if (n > 1000000)
            return (float) (total / (n * Math.log(n)));
        else
            return (float) (total / ((n * Math.log(n)) + n));
    }

    public float mergeSort(int n) {
        for (int i = 0; i < numTests; i++) {
            int[] arr = createArray(n);

            time = System.nanoTime();
            new LogarithmicSorting().mergeSort(arr, 0, arr.length - 1);
            time = (System.nanoTime() - time);
            total += time;
        }
        total = total / numTests;

        if (n > 1000000)
            return (float) (total / (n * Math.log(n)));
        else
            return (float) (total / ((n * Math.log(n)) + n));
    }

    public float myRadixSortList(int n) {
        for (int i = 0; i < numTests; i++) {
            int[] arr = createArray(n);

            time = System.nanoTime();
            new SequentialSorting().myRadixSortList(arr, n * 2);
            time = (System.nanoTime() - time);
            total += time;
        }
        total = total / numTests;

        return total / (n*n);
    }

    public float myRadixSortStack(int n) {
        for (int i = 0; i < numTests; i++) {
            int[] arr = createArray(n);

            time = System.nanoTime();
            new SequentialSorting().myRadixSortStack(n * 2, arr);
            time = (System.nanoTime() - time);
            total += time;
        }
        total = total / numTests;

        return total / (n*n);
    }

    private int[] createArray(int n) {
        Random r = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = r.nextInt(n * 2);

        return arr;
    }
}
