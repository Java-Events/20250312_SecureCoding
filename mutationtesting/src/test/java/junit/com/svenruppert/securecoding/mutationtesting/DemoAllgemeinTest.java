package junit.com.svenruppert.securecoding.mutationtesting;

import com.svenruppert.securecoding.mutationtesting.Demo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class DemoAllgemeinTest {

	@ParameterizedTest
	@MethodSource("provideTestData")
	void testAdd(int a, int b, int expected) {
		var demo = new Demo();
		int result = demo.add(a, b);
		Assertions.assertEquals(expected, result);
	}

	private static Stream<Arguments> provideTestData() {
		return Stream.of(
				// Fall 0
				Arguments.of(0, 0, 0),
				// a < 2
				Arguments.of(1, 1, -2),
				Arguments.of(1, 3, -4),
				Arguments.of(-1, -1, 2),
				Arguments.of(-1, 3, -2),
				Arguments.of(-2, -3, 5),
				// a == 2
				Arguments.of(2, 3, 5),
				Arguments.of(2, -3, -1),
				// a > 2
				Arguments.of(3, 3, 6),
				// (a+b) == 0
				Arguments.of(-1, 1, 0),
				Arguments.of(1, -1, 0),
				// Over/Underflow
				Arguments.of(Integer.MAX_VALUE, 1, -2147483648),
				Arguments.of(Integer.MAX_VALUE, -1, 2147483646),
				Arguments.of(Integer.MIN_VALUE, 1, 2147483647),
				Arguments.of(Integer.MIN_VALUE, -1, -2147483647),
				Arguments.of(1, Integer.MAX_VALUE, -2147483648),
				Arguments.of(-1, Integer.MAX_VALUE, -2147483646),
				Arguments.of(1, Integer.MIN_VALUE, 2147483647),
				Arguments.of(-1, Integer.MIN_VALUE, -2147483647),
				Arguments.of(Integer.MAX_VALUE, Integer.MAX_VALUE, -2),
				Arguments.of(Integer.MIN_VALUE, Integer.MIN_VALUE, 0),
				Arguments.of(Integer.MAX_VALUE, Integer.MIN_VALUE, -1),
				Arguments.of(Integer.MIN_VALUE, Integer.MAX_VALUE, 1)
		);
	}
}
