package io.github.dearrudam;

import java.util.Comparator;

public class QuickSort {

    public static <T> void sort(T[] array, Comparator<T> comparator) {
        quicksort(array, comparator, 0, array.length - 1);
    }

    private static <T> void quicksort(T[] array,
                                      Comparator<T> comparator,
                                      int lower, int higher) {
        if (lower >= higher) {
            return;
        }
        // 4 - encontrando a posição correta do pivo;
        int pivot = partition(array, comparator, lower, higher);

        // 5 - executa o mesmo processo para os itens à esquerda do pivo se lower > higher;
        quicksort(array, comparator, lower, pivot - 1);

        // 6 - executa o mesmo processo para os itens à direita do pivo lower > higher;
        quicksort(array, comparator, pivot + 1, higher);

    }

    private static <T> int partition(T[] array, Comparator<T> comparator, int lower, int higher) {
        // 1 - definir o pivo ( é a metade do array)
        var pivotValue = array[(lower + higher) / 2];
        // 2 - separa os valores menores que o pivo à esquerda;
        // 3 - separa os valores maiores que o pivo à direita;
        return -1;
    }
}
