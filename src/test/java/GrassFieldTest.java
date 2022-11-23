import agh.ics.oop.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GrassFieldTest {
    @Test
    public void testCanMoveTo() {
        IWorldMap map = new GrassField(5);
        Vector2d[] positions = new Vector2d[]{
                new Vector2d(0, 0), new Vector2d(4, 4),
                new Vector2d(1000, 1000),  new Vector2d(-1000, -1000)
        };

        for (Vector2d vector2d : positions) {
            Assertions.assertTrue(map.canMoveTo(vector2d));
            map.place(new Animal(map, vector2d));
        }


        for (Vector2d position : positions) {
            Assertions.assertFalse(map.canMoveTo(position));
        }
    }

    @Test
    public void testPlace() {
        IWorldMap map = new GrassField(1);
        Assertions.assertTrue( map.place(new Animal(map, new Vector2d(0, 0))));
        Assertions.assertTrue( map.place(new Animal(map, new Vector2d(1, 1))));
        Assertions.assertTrue( map.place(new Animal(map, new Vector2d(2, 2))));


        Assertions.assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, new Vector2d(0, 0))));
        Assertions.assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, new Vector2d(1, 1))));
        Assertions.assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, new Vector2d(2, 2))));


    }

    @Test
    public void testIsOccupied() {
        IWorldMap map = new GrassField(2);
        map.place(new Animal(map, new Vector2d(2, 2)));
        map.place(new Animal(map, new Vector2d(0, 2)));
        map.place(new Animal(map, new Vector2d(2, 0)));

        Assertions.assertTrue(map.isOccupied(new Vector2d(2, 2)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(0, 2)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(2, 0)));

        Assertions.assertFalse(map.isOccupied(new Vector2d(20, 20)));
        map.place(new Animal(map, new Vector2d(20, 20)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(20, 20)));
    }


    @Test
    public void testObjectAt() {
        IWorldMap map = new GrassField(10);

        Assertions.assertNull(map.objectAt(new Vector2d(-1, -1)));
        Animal firstAnimal = new Animal(map, new Vector2d(0, 0));
        map.place(firstAnimal);
        Assertions.assertEquals(firstAnimal, map.objectAt(new Vector2d(0, 0)));

        Assertions.assertFalse(map.objectAt(new Vector2d(2, 2)) instanceof Animal);
        Animal secondAnimal = new Animal(map, new Vector2d(2, 2));
        map.place(secondAnimal);
        Assertions.assertEquals(secondAnimal, map.objectAt(new Vector2d(2, 2)));
        Assertions.assertTrue(map.objectAt(new Vector2d(2, 2)) instanceof Animal);
    }
}
