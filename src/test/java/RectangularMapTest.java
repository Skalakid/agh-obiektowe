import agh.ics.oop.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class RectangularMapTest {
    @Test
    public void testCanMoveTo() {
        IWorldMap map = new RectangularMap(5, 5);
        Vector2d[] positions = new Vector2d[]{
                new Vector2d(0, 0), new Vector2d(4, 0), new Vector2d(4, 4),
                new Vector2d(0, 4), new Vector2d(-100, 2), new Vector2d(2, -100), new Vector2d(5, 5),
                new Vector2d(1000, 1000)
        };
        Boolean[] testResults = new Boolean[]{true, true, true, true, false, false, false, false};

        for (int i = 0; i < testResults.length; i++) {
            Assertions.assertEquals( map.canMoveTo(positions[i]), testResults[i]);
        }
    }

    @Test
    public void testIsOccupied() {
        RectangularMap map = new RectangularMap(5, 5);
        map.place(new Animal(map, new Vector2d(2, 2)));
        map.place(new Animal(map, new Vector2d(0, 2)));
        map.place(new Animal(map, new Vector2d(2, 0)));

        Assertions.assertTrue(map.isOccupied(new Vector2d(2, 2)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(0, 2)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(2, 0)));

        Assertions.assertFalse(map.isOccupied(new Vector2d(0, 0)));
        map.place(new Animal(map, new Vector2d(0, 0)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(0, 0)));
    }


    @Test
    public void testObjectAt() {
        IWorldMap map = new RectangularMap(5, 5);

        Assertions.assertNull(map.objectAt(new Vector2d(0, 0)));
        Animal firstAnimal = new Animal(map, new Vector2d(0, 0));
        map.place(firstAnimal);
        Assertions.assertEquals(firstAnimal, map.objectAt(new Vector2d(0, 0)));

        Assertions.assertNull(map.objectAt(new Vector2d(2, 2)));
        Animal secondAnimal = new Animal(map, new Vector2d(2, 2));
        map.place(secondAnimal);
        Assertions.assertEquals(secondAnimal, map.objectAt(new Vector2d(2, 2)));
    }
}
