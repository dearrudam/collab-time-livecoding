package io.github.dearrudam;

import java.util.Comparator;
import java.util.Objects;

public class QuickSort {
    public static <T> void sort(T[] array, Comparator<T> comparator) {
        if(Objects.isNull(array))
            return;
        if(Objects.isNull(comparator))
            return;
        // [5,4,3,2,1]
        quickSort(array, comparator, 0, array.length - 1);
    }

    private static <T> void quickSort(T[] array, Comparator<T> comparator, int left, int right) {
        if(left < right) {
            int pivo = partitioning(array, comparator, left, right);
            quickSort(array, comparator, left, pivo - 1);
            quickSort(array, comparator, pivo, right);
        }
    }

    private static <T> int partitioning(T[] array, Comparator<T> comparator, int left, int right) {
        if(left < right) {
            int pivo = right / 2;
            while (left < right) {
                while (comparator.compare(array[left], array[pivo]) <= 0) {
                    left++;
                }
                while (comparator.compare(array[right], array[pivo]) > 0) {
                    right--;
                }
                if (left <= right) {
                    var temp = array[left];
                    array[left] = array[right];
                    array[right] = temp;
                    left++;
                    right--;
                }
            }
        }
        return left;
    }
}
