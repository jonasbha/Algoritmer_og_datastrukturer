package obliger.oblig3;

import java.util.Arrays;

public class LogarithmicSorting {

    public int[] quickSort(int A[], int min, int max) {
        // Quicksort av array med heltall

        int indexofpartition;

        if (max - min  > 0)
        {
            // Partisjonerer array
            indexofpartition = findPartition(A, min, max);

            quickSort(A, min, indexofpartition - 1);

            // Sorterer hÃ¸yre del
            quickSort(A, indexofpartition + 1, max);
        }

        return A;
    }

    public int[] quickSort_efficient(int A[], int min, int max) {

        int indexofpartition;

        if (max - min > 50)
        {
            indexofpartition = findPartition(A, min, max);

            quickSort_efficient(A, min, indexofpartition - 1);

            quickSort_efficient(A, indexofpartition + 1, max);
        } else {
            int n = max - min;
            int gap = n/2;

            while (gap > 0)
            {
                for (int i = gap; i < n; i++)
                {
                    int j = i;
                    int temp = A[i];

                    while (j >= gap && A[j - gap] > temp)
                    {
                        A[j] = A[j - gap];
                        j = j - gap;
                    }
                    A[j] = temp;
                }
                if (gap == 2)
                    gap = 1;
                else
                    gap *= (5.0 / 11);
            }
        }

        return A;

    }

    private int findPartition(int[] A, int min, int max) {
        int left, right;
        int temp, partitionelement;

        // Bruker *fÃ¸rste* element til Ã¥ dele opp

        partitionelement = A[max];

        left = min;
        right = max;

        // GjÃ¸r selve partisjoneringen
        while (left < right)
        {
            // Finn et element som er stÃ¸rre enn part.elementet
            while (A[left] < partitionelement)
                left++;

            // Finn et element som er mindre enn part.elementet
            while (A[right] >= partitionelement && right > left)
                right--;

            // Bytt om de to hvis ikke ferdig
            if (left < right)
            {
                temp = A[left];
                A[left] = A[right];
                A[right] = temp;
            }
        }

        // Sett part.elementet mellom partisjoneringene
        temp = A[max];
        A[max] = A[right];
        A[right] = temp;

        // Returner indeksen til part.elementet
        return right;
    }

    // denne versjonen er kun mer effektiv hvis arrayet allerede er mer eller mindre sortert.
    // det kan testes ved å legge inn en forhåndssortering i tilhørende Runtimetest
    // og endre imp. i quicksort_efficient til denne.
    private int findPartition_median(int[] A, int min, int max) {
        int left, right;
        int temp, partitionelement;

        if (max - min > 3) {

            int mid = max / 2;

            int t = A[min];

            if (A[min] < A[max])
                A[min] = Math.min(A[max], A[mid]);
            else
                A[min] = Math.min(A[min], A[mid]);

            partitionelement = t;
        } else
            partitionelement = A[max];

        partitionelement = A[max];

        left = min;
        right = max;

        // GjÃ¸r selve partisjoneringen
        while (left < right)
        {
            // Finn et element som er stÃ¸rre enn part.elementet
            while (A[left] < partitionelement)
                left++;

            // Finn et element som er mindre enn part.elementet
            while (A[right] >= partitionelement && right > left)
                right--;

            // Bytt om de to hvis ikke ferdig
            if (left < right)
            {
                temp = A[left];
                A[left] = A[right];
                A[right] = temp;
            }
        }

        // Sett part.elementet mellom partisjoneringene
        temp = A[max];
        A[max] = A[right];
        A[right] = temp;

        // Returner indeksen til part.elementet
        return right;
    }

    public int[] mergeSort(int[] A, int min, int max) {

        // Flettesortering av array med heltall

        if (min==max)
            return A;

        int[] temp;
        int index1, left, right;
        int size = max - min + 1;
        int mid = (min + max) / 2;

        temp = new int[size];

        // Flettesorterer de to halvdelene av arrayen
        mergeSort(A, min, mid);
        mergeSort(A, mid + 1,max);

        // Kopierer array over i temp.array
        for (index1 = 0; index1 < size; index1++)
            temp[index1] = A[min + index1];

        // Fletter sammen de to sorterte halvdelene over i A
        left = 0;
        right = mid - min + 1;
        for (index1 = 0; index1 < size; index1++)
        {
            if (right <= max - min)
                if (left <= mid - min)
                    if (temp[left] > temp[right])
                        A[index1 + min] = temp[right++];
                    else
                        A[index1 + min] = temp[left++];
                else
                    A[index1 + min] = temp[right++];
            else
                A[index1 + min] = temp[left++];
        }
        return A;
    }

    // Er ubrukelig ettersom oppdelingen av array er O(n log(n))
    public int[] myMergeSort(int[] A) {

        if (A.length == 1)
            return A;

        int[][] ls = del(A);
        int[] left = ls[0];
        int[] right = ls[1];

        myMergeSort(left);
        myMergeSort(right);

        return flett(A, left, right);
    }

    private int[] flett(int[] A, int[] left, int[] right) {

        int l = 0;
        int r = 0;
        while (l < left.length && r < right.length) {
            if (left[l] < right[r]) {
                A[l + r] = left[l];
                l++;
            }
            else {
                A[l + r] = right[r];
                r++;
            }
        }

        while (r < right.length) {
            A[l + r] = right[r];
            r++;
            if (r == right.length)
                break;
        }
        while (l < left.length) {
            A[l + r] = left[l];
            l++;
            if (l == left.length)
                break;
        }
        return A;
    }

    private int[][] del(int[] A) {
        int[][] ls = new int[A.length][2];

        int midt = Math.round(A.length / 2);

        int[] del1 = new int[midt];
        int[] del2 = new int[A.length-midt];
        for (int i = 0; i < midt; i++)
            del1[i] = A[i];
        for (int i = 0; i < A.length - midt; i++)
            del2[i] = A[midt + i];

        ls[0] = del1;
        ls[1] = del2;
        return ls;
    }

}
