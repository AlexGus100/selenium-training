import org.example.SimpleTestClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTest {

    @Test
    public void test0() {
        SimpleTestClass sum = new SimpleTestClass();
        int result = sum.testMethod(2, 3);
        assertEquals(5, result);
    }

}
