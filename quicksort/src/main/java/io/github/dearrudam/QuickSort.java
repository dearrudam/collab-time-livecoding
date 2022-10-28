package io.github.dearrudam;

import java.util.Arrays;
import java.util.Comparator;

public class QuickSort {

    public static <T> void sort(T[] array, Comparator<T> comparator) {
        quicksort(array, comparator, 0, array.length - 1);
    }

    private static <T> void quicksort(T[] array,
                                      Comparator<T> comparator,
                                      int lower, int higher) {
        System.out.println("QUICKSORT[%s,%s] = %s".formatted(lower, higher, Arrays.toString(array)));
        if (lower >= higher) {
            return;
        }

        // 4 - encontrando a posição correta do pivo;
        int pivot = partition(array, comparator, lower, higher);
        System.out.println("PARTITION[%s,%s] = %s".formatted(lower, higher, pivot));

        // 5 - executa o mesmo processo para os itens à esquerda do pivo se lower > higher;
        quicksort(array, comparator, lower, pivot - 1);

        // 6 - executa o mesmo processo para os itens à direita do pivo lower > higher;
        quicksort(array, comparator, pivot, higher);

    }

    private static <T> int partition(T[] array, Comparator<T> comparator, int lower, int higher) {
        System.out.println("PARTITION[%s,%s] FROM %s".formatted(lower, higher, Arrays.toString(array)));

        // 1 - escolher o pivo (é a metade do array)
        var pivo = array[(lower + higher) / 2];

        while (lower <= higher) {

            // 2 - separa os valores menores que o pivo à esquerda;
            while (comparator.compare(array[lower], pivo) < 0)
                lower++;
            // 3 - separa os valores maiores que o pivo à direita;
            while (comparator.compare(array[higher], pivo) > 0)
                higher--;

            if (lower <= higher) {
                swap(array, lower, higher);
                lower++;
                higher--;
            }
        }
        return lower;
    }

    private static <T> void swap(T[] array, int lower, int higher) {
        System.out.print("SWAP[%s,%s] FROM %s".formatted(lower, higher, Arrays.toString(array)));
        var temp = array[lower];
        array[lower] = array[higher];
        array[higher] = temp;
        System.out.println(" -> %s".formatted(Arrays.toString(array)));
    }
}
