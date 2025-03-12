package junit.com.svenruppert.securecoding.inputvalidation.v01;

import com.svenruppert.securecoding.inputvalidation.v01.DivideService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DivideServiceTest {

  @Test
  void test001() {
    var service = new DivideService();
    float result = service.divide(1, 2);
    Assertions.assertEquals(0.5, result);
  }

  @Test
  void test002() {
    var service = new DivideService();
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      service.divide(1, 0);
    });
  }

  @Test
  void test003() {
    var service = new DivideService();
    Optional<Float> v = service.divideNull(0, 0);
    assertTrue(v.isEmpty());
  }

  @Test
  void test004() {
    var service = new DivideService();
    Optional<Float> v = service.divideNull(4, 2);
    assertEquals(Optional.of(2f), v);
  }
}
