package junit.com.svenruppert.securecoding.mutationtesting;

import com.svenruppert.securecoding.mutationtesting.Demo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Demo02Test {

    @ParameterizedTest
    @MethodSource("provideTestData")
    void testAdd(int a, int b, int expected) {
        var demo = new Demo();
        int result = demo.add(a, b);
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(0, 0, 0),
                org.junit.jupiter.params.provider.Arguments.of(1, 1, -2),
                org.junit.jupiter.params.provider.Arguments.of(1, 3, -4),
                org.junit.jupiter.params.provider.Arguments.of(2, 3, 5),
                org.junit.jupiter.params.provider.Arguments.of(3, 3, 6),
                org.junit.jupiter.params.provider.Arguments.of(-1, -1, 2),
                org.junit.jupiter.params.provider.Arguments.of(-1, 3, -2),
                org.junit.jupiter.params.provider.Arguments.of(2, -3, -1),
                org.junit.jupiter.params.provider.Arguments.of(-2, -3, 5),
                org.junit.jupiter.params.provider.Arguments.of(Integer.MAX_VALUE, 1, -2147483648),
                org.junit.jupiter.params.provider.Arguments.of(Integer.MIN_VALUE, -1, -2147483647)
        );
    }
}
