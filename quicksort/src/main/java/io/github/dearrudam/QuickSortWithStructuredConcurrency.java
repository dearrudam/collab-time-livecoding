package io.github.dearrudam;

import jdk.incubator.concurrent.StructuredTaskScope;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class QuickSortWithStructuredConcurrency {
    public static <T> void sort(T[] array, Comparator<T> comparator) {
        if (Objects.isNull(array))
            return;
        if (Objects.isNull(comparator))
            return;
        // [5,4,3,2,1]
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            try {
                quickSort(scope, array, comparator, 0, array.length - 1);
                scope.throwIfFailed();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private static <T> void quickSort(StructuredTaskScope.ShutdownOnFailure scope, T[] array, Comparator<T> comparator, int left, int right) throws ExecutionException, InterruptedException {
        if (left >= right) {
            return;
        }
        //System.out.print("[%s...%s] ".formatted(left, right));

        var pivoIndex =
                scope.fork(() -> {
                    try (var scope2 = new StructuredTaskScope.ShutdownOnFailure()) {
                        try {
                            return partition(scope2, array, comparator, left, right);
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        scope.join();
        scope.throwIfFailed();
        var pivo = pivoIndex.resultNow();
        quickSort(scope, array, comparator, left, pivo - 1);
        quickSort(scope, array, comparator, pivo, right);

    }


    private static <T> int partition(StructuredTaskScope.ShutdownOnFailure scope, T[]
            array, Comparator<T> comparator, int left, int right) throws InterruptedException, ExecutionException {
        T pivo = array[(left + right) / 2];
        while (left <= right) {

            AtomicInteger leftCount = new AtomicInteger(left);
            AtomicInteger rightCount = new AtomicInteger(right);

            Future<Integer> leftProcess = scope.fork(() -> {
                while (comparator.compare(array[leftCount.get()], pivo) < 0) {
                    leftCount.incrementAndGet();
                }
                return leftCount.get();
            });

            Future<Integer> rightProcess = scope.fork(() -> {
                while (comparator.compare(array[rightCount.get()], pivo) > 0) {
                    rightCount.decrementAndGet();
                }
                return rightCount.get();
            });
            scope.join();
            scope.throwIfFailed();
            left = leftProcess.resultNow();
            right = rightProcess.resultNow();
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
