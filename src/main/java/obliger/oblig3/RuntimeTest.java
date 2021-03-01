package obliger.oblig3;

import java.util.Random;

public class RuntimeTest {

    public RuntimeTest(int numTests) {
        this.numTests = numTests;
    }

    private int numTests;
    private long time;
    private float total = 0;

    public float insertionSort(int n) {

        for (int i = 0; i < numTests; i++) {
            int[] arr = createArray(n);

            time = System.currentTimeMillis();
            new SequentialSorting().insertionSort(arr);
            time = (System.currentTimeMillis() - time);
            total += time;
        }
        return total / numTests / 1000;
    }

    public float quickSort(int n) {

        for (int i = 0; i < numTests; i++) {
            int[] arr = createArray(n);

            time = System.currentTimeMillis();
            new LogarithmicSorting().quickSort(arr, 0, arr.length - 1);
            time = (System.currentTimeMillis() - time);
            total += time;
        }
        return total / numTests / 1000;
    }

    public float quickSort_efficient(int n) {

        for (int i = 0; i < numTests; i++) {
            int[] arr = createArray(n);

            time = System.currentTimeMillis();
            new LogarithmicSorting().quickSort_efficient(arr, 0, arr.length - 1);
            time = (System.currentTimeMillis() - time);
            total += time;
        }
        return total / numTests / 1000;
    }

    public float mergeSort(int n) {

        for (int i = 0; i < numTests; i++) {
            int[] arr = createArray(n);

            time = System.currentTimeMillis();
            new LogarithmicSorting().mergeSort(arr, 0, arr.length - 1);
            time = (System.currentTimeMillis() - time);
            total += time;
        }
        return total / numTests / 1000;
    }

    public float myRadixSortList(int n) {

        for (int i = 0; i < numTests; i++) {
            int[] arr = createArray(n);

            time = System.currentTimeMillis();
            new SequentialSorting().myRadixSortList(arr, n*2);
            time = (System.currentTimeMillis() - time);
            total += time;
        }
        return total / numTests / 1000;
    }

    public float myRadixSortStack(int n) {

        for (int i = 0; i < numTests; i++) {
            int[] arr = createArray(n);

            time = System.currentTimeMillis();
            new SequentialSorting().myRadixSortStack( n*2, arr);
            time = (System.currentTimeMillis() - time);
            total += time;
        }
        return total / numTests / 1000;
    }

    private int[] createArray(int n) {
        Random r = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = r.nextInt(n * 2);

        return arr;
    }

    private void printArray(int[] array) {
        for (int input : array)
            System.out.print(input + " ");
        System.out.println();
    }
}
