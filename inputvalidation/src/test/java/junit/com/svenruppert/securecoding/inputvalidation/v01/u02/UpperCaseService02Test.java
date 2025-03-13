package junit.com.svenruppert.securecoding.inputvalidation.v01.u02;

import com.svenruppert.securecoding.inputvalidation.v01.u02.UpperCaseService02;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpperCaseService02Test {

  @Test
  void test001() {
    String hello = new UpperCaseService02()
        .toUpperCase("hello");
    assertEquals("HELLO", hello);
  }

  @Test
  void test002() {
    String hello = new UpperCaseService02()
        .toUpperCase("333");
    assertEquals("333", hello);
  }

}
