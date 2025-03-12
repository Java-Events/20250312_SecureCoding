package junit.com.svenruppert.securecoding.mutationtesting;

import com.svenruppert.securecoding.mutationtesting.Demo;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class Demo06Test {

  @Test
  void test001() {
    var demo = new Demo();
    assertEquals(-3, demo.add(1, 2));
    assertEquals(4, demo.add(2, 2));
    assertEquals(5, demo.add(3, 2));
    assertEquals(-3, demo.add(1, 2));
    assertEquals(-1, demo.add(-1, 2));
    assertEquals(-2, demo.add(Integer.MAX_VALUE, Integer.MAX_VALUE));
    assertEquals(0, demo.add(Integer.MIN_VALUE, Integer.MIN_VALUE));
    assertEquals(-1, demo.add(Integer.MAX_VALUE, Integer.MIN_VALUE));
    assertEquals(1, demo.add(Integer.MIN_VALUE, Integer.MAX_VALUE));
  }
}
