package io.github.dearrudam;

import java.util.Comparator;

public class QuickSort {

    public static <T> void sort(T[] array, Comparator<T> comparator) {
        quicksort(array, comparator, 0, array.length - 1);
    }

    private static <T> void quicksort(T[] array,
                                      Comparator<T> comparator,
                                      int start, int end) {
        if (start >= end) {
            return;
        }
        // 4 - encontrando a posição correta do pivo;
        int pivot = partition(array, comparator, start, end);

        // 5 - executa o mesmo processo para os itens à esquerda do pivo se start > end;
        quicksort(array, comparator, start, pivot - 1);

        // 6 - executa o mesmo processo para os itens à direita do pivo start > end;
        quicksort(array, comparator, pivot + 1, end);

    }

    private static <T> int partition(T[] array, Comparator<T> comparator, int start, int end) {
        // 1 - definir o pivo ( é a metade do array)
        var pivotIndex = (start + end) / 2;
        var pivotValue = array[pivotIndex];
        swap(array, pivotIndex, end); // isolando o pivo

        var lastLargerThanPivot = start;
        var actualIndex = start;

        while (actualIndex < end) {
            if (comparator.compare(array[actualIndex], pivotValue) <= 0) {
                swap(array, actualIndex, lastLargerThanPivot);
                lastLargerThanPivot++;
            }
            actualIndex++;
        }

        swap(array, lastLargerThanPivot, end);

        return lastLargerThanPivot;
    }

    private static <T> void swap(T[] array, int fromIndex, int toIndex) {
        var temp = array[fromIndex];
        array[fromIndex] = array[toIndex];
        array[toIndex] = temp;
    }
}
