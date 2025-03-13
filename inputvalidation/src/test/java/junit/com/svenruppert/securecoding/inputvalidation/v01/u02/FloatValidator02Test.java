package junit.com.svenruppert.securecoding.inputvalidation.v01.u02;

import com.svenruppert.securecoding.inputvalidation.v01.u02.FloatValidator02;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FloatValidator02Test {

    @Test
    void test_is_float() {
        var validator = new FloatValidator02();
        assertTrue(validator.isFloat("1"));
    }

    @Test
    void test_is_not_a_float() {
        var validator = new FloatValidator02();
        assertFalse(validator.isFloat("KEINFLOAT"));
    }

}
