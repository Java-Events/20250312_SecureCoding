package junit.com.svenruppert.securecoding.mutationtesting;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.svenruppert.securecoding.mutationtesting.Demo;

class Demo03Test {

  @Test
  void test001() {
    var demo = new Demo();
    int result = demo.add(1, 2);
    assertEquals(-3, result);
  }

  @Test
  void test002() {
	  var demo = new Demo();
	  int result = demo.add(2, 2);
	  assertEquals(4, result);
  }
  
}
