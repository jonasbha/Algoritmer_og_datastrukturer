package obliger.oblig3;

import java.util.Scanner;

public class Program {

    private static final int MAX_SEQUENTIAL = 50000;
    private static final int MAX_SIZE = 5000000;
    private static final int AVG_OF = 100;

    public static void main(String[] args) {

        /*
        * prøvde meg på en del sorteringsmetoder før jeg tok i bruk de eksisterende,
        * disse ligger med de andre metodene.
        * De fleste av metodene jeg lagde selv benyttes ikke fordi de ikke er effektive nok.
        * */

        System.out.println("Enter the size of the array n: ");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();

        while (size > MAX_SIZE) {
            System.out.println("Max size is " + MAX_SIZE + ".\n Enter a new size: ");
            size = scanner.nextInt();
        }

        showMethods();
        int method = scanner.nextInt();

        while (method < 0 || method > 4) {
            System.out.println("Enter a number between 1 and 4.");
            method = scanner.nextInt();
        }

        showOptions();
        int option = scanner.nextInt();

        while (option < 0 || option > 2) {
            System.out.println("Enter a number between 1 and 2.");
            option = scanner.nextInt();
        }

        start(size, method, option);
    }

    private static void start(int n, int method, int option) {
        switch (method) {
            case 1:
                if (option == 1)
                    if (n <= MAX_SEQUENTIAL) {
                        System.out.printf("Insertion sort runtime (avg. of " + AVG_OF + ")\t: %6.3f s\n", new RuntimeTest(AVG_OF).insertionSort(n));
                    }
                    else
                        System.out.println("\nO(n^2) sorting too slow for large n");
                else
                    System.out.printf("Insertion sort constant (C) (avg. of " + AVG_OF + ")\t: %6.2f \n", new ConstantEstimation(AVG_OF).insertionSort(n));
                break;
            case 2:
                if (option == 1) {
                    System.out.printf("Quick sort runtime (avg. of " + AVG_OF + ")\t: %6.3f s\n", new RuntimeTest(AVG_OF).quickSort(n));
                    System.out.printf("Quick sort (efficient imp.) runtime (avg. of " + AVG_OF + ")\t: %6.3f s\n", new RuntimeTest(AVG_OF).quickSort_efficient(n));
                    break;
                }
            case 3:
                if (option == 1) {
                    System.out.printf("Merge sort runtime (avg. of " + AVG_OF + ")\t: %6.3f s\n", new RuntimeTest(AVG_OF).mergeSort(n));
                    break;
                }
            case 4:
                if (option == 1)
                    if (n <= MAX_SEQUENTIAL) {
                        System.out.printf("My Radix sort with list imp. runtime (avg. of " + AVG_OF + ")\t: %6.3f s\n", new RuntimeTest(AVG_OF).myRadixSortList(n));
                        System.out.printf("My Radix sort with stack imp. runtime (avg. of " + AVG_OF + ")\t: %6.3f s\n", new RuntimeTest(AVG_OF).myRadixSortStack(n));
                    }
                    else
                        System.out.println("\nO(n^2) sorting too slow for large n");
                else
                    System.out.println("needs imp");
                break;
        }
    }

    private static void showMethods() {
        System.out.println("""
                                         
                Choose the sorting method:
                1. Insertion sort
                2. Quick sort
                3. Merge sort
                4. Radix sort""");
        System.out.println("Enter the number of the sorting method.");
    }

    private static void showOptions() {
        System.out.println("""
                                         
                Choose between the following options:
                1. Test runtime of the chosen method.
                2. Estimate the constant of  the worst case order (growth rate) of the chosen method.""");
        System.out.println("Enter the number of the option.");
    }
}