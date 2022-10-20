package io.github.dearrudam;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class TestArguments implements ArgumentsProvider {

    private static Integer[] randomList;

    private static Integer[] orderedRandomList;

    static {
        int size = 1000;
        Stream<Integer> randomStream = IntStream.range(0, size).boxed().map(i -> new Random().nextInt(size - 1) + 1);
        TestArguments.randomList = randomStream.toArray(Integer[]::new);
        TestArguments.orderedRandomList = Arrays.stream(TestArguments.randomList).sorted().toArray(Integer[]::new);
    }

    private static Stream<Arguments> getArgs() {
        return Stream.of(
                arguments(new Integer[]{5, 4, 3, 2, 1}, new Integer[]{1, 2, 3, 4, 5}),
                arguments(new Integer[]{2, 1}, new Integer[]{1, 2}),
                arguments(new Integer[]{1}, new Integer[]{1}),
                arguments(Arrays.copyOf(randomList,randomList.length), orderedRandomList)
        );
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return getArgs();
    }
}
