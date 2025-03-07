import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberComparatorTest {

    @Test
    void testCompareFirstGreater() {
        assertEquals("Первое число больше.", NumberComparator.compare(5, 3)); // 5 > 3
    }

    @Test
    void testCompareSecondGreater() {
        assertEquals("Второе число больше.", NumberComparator.compare(2, 4)); // 2 < 4
    }

    @Test
    void testCompareEqual() {
        assertEquals("Числа равны.", NumberComparator.compare(7, 7)); // 7 == 7
    }
}