package io.github.dearrudam;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AppTest {

    @Test
    @DisplayName("não deve lançar exceção caso argumentos forem nulos")
    public void test01() {
        assertDoesNotThrow(() -> {
            QuickSort.sort(null, null);
        });
    }

    @Test
    @DisplayName("não deve lançar exceção caso array for nulo, mas comparator não")
    public void test02() {
        assertDoesNotThrow(() -> {
            QuickSort.sort(null, (o1, o2) -> -1);
        });
    }

    @Test
    @DisplayName("não deve lançar exceção caso array não for nulo, mas comparator sim")
    public void test03() {
        assertDoesNotThrow(() -> {
            QuickSort.sort(new Integer[]{}, null);
        });
    }

    @ParameterizedTest(name = "{index} - quando ordenar o array: {0}, o ordem deve ser {1}")
    @MethodSource("test04Args")
    public void test04(Integer[] array, Integer[] arrayOrdenado) {
            QuickSort.sort(array, Integer::compareTo);
            assertArrayEquals(arrayOrdenado,array);
    }

    public static Stream<Arguments> test04Args(){
        return Stream.of(
          Arguments.arguments(new Integer[]{5,4,3,2,1},new Integer[]{1,2,3,4,5})
        );
    }


}
