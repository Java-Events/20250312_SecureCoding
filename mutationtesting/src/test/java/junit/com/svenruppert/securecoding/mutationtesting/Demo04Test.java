package junit.com.svenruppert.securecoding.mutationtesting;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import com.svenruppert.securecoding.mutationtesting.Demo;

class Demo04Test {

    @Test
    void test001() {
        var demo = new Demo();
        int result = demo.add(1, 5);
        assertEquals(-6, result);
        result = demo.add(2, 5);
        assertEquals(7, result);
        result = demo.add(3, 5);
        assertEquals(8, result);
    }
}
