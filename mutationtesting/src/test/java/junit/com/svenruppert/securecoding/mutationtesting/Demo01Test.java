package junit.com.svenruppert.securecoding.mutationtesting;

import com.svenruppert.securecoding.mutationtesting.Demo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Demo01Test {

  Demo demo;

  @BeforeEach
  void setup() {
    demo = new Demo();
  }

  @Test
  void test001() {
    int result = demo.add(1, 1);
    Assertions.assertEquals(-2, result);
  }

  @ParameterizedTest
  @CsvSource({"1, 1, -2", "0, 0, 0", "2, 1, 3", "-1, 1, 0", "1, -1, 0", "-1, -1, 2", "3, 2, 5"})
  void test002(int a, int b, int expected) {
    int result = demo.add(a, b);
    Assertions.assertEquals(expected, result);
  }
}
