package io.github.dearrudam;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class QuickSort {
    public static <T> void sort(T[] array, Comparator<T> comparator) {
        if (Objects.isNull(array))
            return;
        if (Objects.isNull(comparator))
            return;
        quickSort(array, comparator, 0, array.length - 1);
    }

    private static <T> void quickSort(T[] array, Comparator<T> comparator, int left, int right) {
        if (left >= right) {
            return;
        }
        //System.out.print("[%s...%s] ".formatted(left, right));

        var pivo = partition(array, comparator, left, right);
        quickSort(array, comparator, left, pivo - 1);
        quickSort(array, comparator, pivo, right);
        //System.out.println();
    }


    private static <T> int partition(T[] array, Comparator<T> comparator, int left, int right) {
        T pivo = array[(left + right) / 2];
        while (left <= right) {

            while (comparator.compare(array[left], pivo) < 0) {
                left++;
            }

            while (comparator.compare(array[right], pivo) > 0) {
                right--;
            }
            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    private static <T> void swap(T[] array, int left, int right) {
        var temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
