package io.github.dearrudam;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class QuickSortWithStructuredConcurrencyTest {

    @Test
    @DisplayName("não deve lançar exceção caso argumentos forem nulos")
    void test01() {
        assertDoesNotThrow(() -> {
            QuickSortWithStructuredConcurrency.sort(null, null);
        });
    }

    @Test
    @DisplayName("não deve lançar exceção caso array for nulo, mas comparator não")
    void test02() {
        assertDoesNotThrow(() -> {
            QuickSortWithStructuredConcurrency.sort(null, (o1, o2) -> -1);
        });
    }

    @Test
    @DisplayName("não deve lançar exceção caso array não for nulo, mas comparator sim")
    void test03() {
        assertDoesNotThrow(() -> {
            QuickSortWithStructuredConcurrency.sort(new Integer[]{}, null);
        });
    }

    @Test
    @DisplayName("não deve lançar exceção caso array não for nulo, mas comparator sim")
    void test04() {
        assertDoesNotThrow(() -> {
            QuickSortWithStructuredConcurrency.sort(new Integer[]{1, 2, 3}, null);
        });
    }

    @ParameterizedTest(name = "{index} - quando ordenar o array: {0}, o resultado deve ser {1}")
    @ArgumentsSource(TestArguments.class)
    public void test05(Integer[] array, Integer[] arrayOrdenado) {
        //Timed.timed(() -> {
        QuickSortWithStructuredConcurrency.sort(array, Integer::compareTo);
        //}, duration -> System.out.println("%s\nspent time: %s - input: %s".formatted(getClass().getSimpleName(), duration, Arrays.toString(array))));
        assertArrayEquals(arrayOrdenado, array);
    }


}
