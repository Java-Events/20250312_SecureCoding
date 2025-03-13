package junit.com.svenruppert.securecoding.inputvalidation.v01.u02;

import com.svenruppert.securecoding.inputvalidation.v01.u02.DivideService02;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DivideService02Test {

    @Test
    void test001() {
        var service = new DivideService02();
        float result = service.divide(1, 2);
        assertEquals(0.5f, result);
    }

    @Test
    void test002() {
        var service = new DivideService02();
        assertThrows(IllegalArgumentException.class, () -> service.divide(1, 0));
    }

    @Test
    void test003() {
        var service = new DivideService02();
        Optional<Float> v = service.divideNull(0, 0);
        assertTrue(v.isEmpty());
    }

    @Test
    void test004() {
        var service = new DivideService02();
        Optional<Float> v = service.divideNull(1, 2);
        assertTrue(v.isPresent());
        assertEquals(Float.valueOf(0.5f), v.get());
    }
}
