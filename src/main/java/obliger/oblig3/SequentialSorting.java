package obliger.oblig3;

import tools.jsjf.ArrayStack;

import java.util.LinkedList;
import java.util.Stack;

public class SequentialSorting {

    // ubrukelig siden det gjøres for mange assignments i indre løkke.
    public int[] myInsertionSort(int[] A) {

        for (int i = 1; i < A.length; i++) {
            int tmp = A[i];
            while (i > 0 && A[i] < A[i-1]) {
                A[i] = A[i-1];
                A[i-1] = tmp;
                i--;
            }
        }
        return A;
    }

    public int[] insertionSort(int A[]) {
        int n = A.length;
        int key;

        for (int i = 1; i < n; i++)
        {
            key = A[i];
            int j = i;
            while (j > 0 && A[j-1] > key)
            {
                A[j] = A[j-1];
                j--;
            }
            A[j] = key;
        }
        return A;
    }

    public int[] myRadixSortStack(int maksInt, int[] A) {
        Stack<Integer>[] B = new Stack[maksInt];
        for (int i = 0; i < maksInt; i++)
            B[i] = new Stack<>();

        for (int k : A)
            for (int j = 0; j < B.length; j++)
                if (k == j)
                    B[j].push(k);

        int a = 0;
        for (Stack<Integer> stack : B)
            while (!stack.isEmpty())
                A[a++] = stack.pop();
        return A;
    }

    public int[] myRadixSortList(int[] A, int maksInt) {

        LinkedList<Integer>[] B = new LinkedList[maksInt];

        for (int i = 0; i < maksInt; i++)
            B[i] = new LinkedList<>();

        for (int k : A)
            for (int j = 0; j < B.length; j++)
                if (k == j)
                    B[j].addFirst(k);

        // reverse sorting order. a = 0, a++
        int a = A.length-1;
        for (LinkedList<Integer> list : B)
            while (!list.isEmpty())
                A[a--] = list.removeFirst();
        return A;
    }

}
