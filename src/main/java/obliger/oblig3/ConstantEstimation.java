package obliger.oblig3;

public class ConstantEstimation {

    public ConstantEstimation(int numTests) {
        this.numTests = numTests;
    }

    private int numTests;
    private float time;

    public float insertionSort(int n) {
        return new RuntimeTest(numTests).insertionSort(n) / (n*n) + n;
    }

    public void quickSort(int n) {

    }

    public void mergeSort(int n) {

    }

    public void radixSort(int max, int n) {

    }
}
