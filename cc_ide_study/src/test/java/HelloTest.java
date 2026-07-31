// toey forever
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HelloTest {

    @Test
    void greeting_returnsHello() {
        assertEquals("hello", new hello().greeting());
    }
}
