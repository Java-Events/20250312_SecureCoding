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
                Arguments.of(0, 0, 0),
                Arguments.of(1, 1, -2),
                Arguments.of(1, 3, -4),
                Arguments.of(2, 3, 5),
                Arguments.of(3, 3, 6),
                Arguments.of(-1, -1, 2),
                Arguments.of(-1, 3, -2),
                Arguments.of(2, -3, -1),
                Arguments.of(-2, -3, 5),
                Arguments.of(Integer.MAX_VALUE, 1, -2147483648),
                Arguments.of(Integer.MIN_VALUE, -1, -2147483647)
        );
    }
}
