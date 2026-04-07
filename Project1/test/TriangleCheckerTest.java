import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TriangleCheckerTest {

    @Test
    public void testValidTriangle() {
        assertEquals("Equilateral",
                TriangleChecker.checkTriangle(5,5,5));
    }

    @Test
    public void testScaleneTriangle() {
        assertEquals("Scalene",
                TriangleChecker.checkTriangle(4,5,6));
    }

    @Test
    public void testScalenePermutation() {
        assertEquals("Scalene",
                TriangleChecker.checkTriangle(6,4,5));
    }

    @Test
    public void testZeroLengthSide() {
        assertEquals("Invalid",
                TriangleChecker.checkTriangle(0,5,5));
    }
}