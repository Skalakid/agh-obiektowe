import agh.ics.oop.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnimalTest {
    @Test
    public void testOrientation() {
        RectangularMap map = new RectangularMap(5,5);
        Animal animal = new Animal(map);
        String[] moves = new String[]{"r", "r", "r", "l", "r", "r", "left", "l", "r", "l", "r", "l", "r", "r"};
        World.runAnimal(animal, OptionsParser.parse(moves));
        Assertions.assertEquals(animal.getOrientation(), MapDirection.NORTH);
    }

    @Test
    public void testPosition() {
        RectangularMap map = new RectangularMap(5,5);
        Animal animal = new Animal(map);
        String[] moves = new String[]{"f", "r", "f", "r", "f", "f", "r", "f", "f", "l", "l", "l", "f", "f", "f", "r", "f", "f", "f", "r", "f", "f", "f", "f", "r", "f", "f", "f", "f","l", "b", "b", "b", "b"};
        World.runAnimal(animal, OptionsParser.parse(moves));
        Assertions.assertEquals(animal.getPosition(), new Vector2d(0, 4));
    }

    @Test
    public void testMapLimitation() {
        RectangularMap map = new RectangularMap(5,5);
        Animal animal = new Animal(map);
        String[] moves = new String[]{"f", "f", "f", "f", "f", "f", "r","f", "f", "f", "f", "r", "f", "f" , "f", "f", "f", "f", "l", "l", "b", "b", "b"};
        World.runAnimal(animal, OptionsParser.parse(moves));
        Assertions.assertEquals(animal.getPosition(), new Vector2d(4, 0));
    }

    @Test
    public void testDataParse() {
        RectangularMap map = new RectangularMap(5,5);
        Animal animal = new Animal(map);
        String[] moves = new String[]{"r", "right", "l", "left", "f", "forward", "b", "backward"};
        World.runAnimal(animal, OptionsParser.parse(moves));
        Assertions.assertEquals(animal.getPosition(), new Vector2d(2, 2));

        String[] moves2 = new String[]{"f", "r", "b", "l", "asds"};
        Assertions.assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(moves2));



    }

    @Test
    public void tesIsAt() {
        RectangularMap map = new RectangularMap(5,5);
        Animal animal = new Animal(map);
        animal.move(MoveDirection.FORWARD);
        Assertions.assertTrue(animal.isAt(new Vector2d(2,3)));
        Assertions.assertFalse(animal.isAt(new Vector2d(2,2)));

    }
}
