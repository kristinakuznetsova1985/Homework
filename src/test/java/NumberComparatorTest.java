import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class NumberComparatorTest {

    @Test
    public void testCompareFirstGreater() {
        assertEquals(NumberComparator.compare(5, 3), "Первое число больше."); // 5 > 3
    }

    @Test
    public void testCompareSecondGreater() {
        assertEquals(NumberComparator.compare(2, 4), "Второе число больше."); // 2 < 4
    }

    @Test
    public void testCompareEqual() {
        assertEquals(NumberComparator.compare(7, 7), "Числа равны."); // 7 == 7
    }
}