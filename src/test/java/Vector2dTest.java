import agh.ics.oop.MapDirection;
import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vector2dTest {
    Vector2d v1 = new Vector2d(1000000, 1000000);
    Vector2d v2 = new Vector2d(1000000, 1000000);
    Vector2d v3 = new Vector2d(2000000, 2000000);
    Vector2d v4 = new Vector2d(2000000, 3000000);
    Vector2d v5 = new Vector2d(3000000, 2000000);
    Vector2d v6 = new Vector2d(3000000, 3000000);
    @Test
    public void testEquals() {
        Assertions.assertEquals(v1, v1);
        Assertions.assertEquals(v2, v2);
        Assertions.assertEquals(v1, v2);
        Assertions.assertNotEquals(v1, v3);
    }

    @Test
    public void testToString() {
        Assertions.assertEquals(v1.toString(),"(1000000, 1000000)");
        Assertions.assertNotEquals(v3.toString(),"(12345678, 87654321)");
    }

    @Test
    public void testPrecedes() {
        Assertions.assertTrue(v1.precedes(v3));
        Assertions.assertTrue(v1.precedes(v1));
        Assertions.assertFalse(v3.precedes(v1));
    }

    @Test
    public void testFollows() {
        Assertions.assertTrue(v3.follows(v1));
        Assertions.assertTrue(v4.follows(v3));
        Assertions.assertTrue(v4.follows(v4));
        Assertions.assertFalse(v1.follows(v3));
    }

    @Test
    public void testUpperRight() {
        Assertions.assertEquals(v4.upperRight(v6), new Vector2d(3000000, 3000000));
        Assertions.assertEquals(v6.upperRight(v4), new Vector2d(3000000, 3000000));
        Assertions.assertEquals(v4.upperRight(v4), v4);
    }

    @Test
    public void testLoweLeft() {
        Assertions.assertEquals(v4.lowerLeft(v6), new Vector2d(2000000, 3000000));
        Assertions.assertEquals(v4.lowerLeft(v4), v4);
    }

    @Test
    public void testAdd() {
        Assertions.assertEquals(v1.add(v3), v6);
        Assertions.assertEquals(v1.add(v1), v3);
    }

    @Test
    public void testSubtract() {
        Assertions.assertEquals(v6.subtract(v1), new Vector2d(2000000, 2000000));
        Assertions.assertEquals(v1.subtract(v1), new Vector2d(0, 0));
    }

    @Test
    public void testOpposite() {
        Assertions.assertEquals(v5.opposite(), new Vector2d(v5.x*(-1),  v5.y*(-1)));
    }
}
