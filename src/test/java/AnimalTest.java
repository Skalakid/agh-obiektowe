import agh.ics.oop.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnimalTest {
    @Test
    public void testOrientation() {
        Animal animal = new Animal();
        String[] moves = new String[]{"r", "r", "r", "l", "r", "r", "left", "l", "r", "l", "r", "l", "r", "r"};
        World.runAnimal(animal, OptionsParser.parse(moves));
        Assertions.assertEquals(animal.getOrientation(), MapDirection.NORTH);
    }

    @Test
    public void testPosition() {
        Animal animal = new Animal();
        String[] moves = new String[]{"f", "r", "f", "r", "f", "f", "r", "f", "f", "l", "l", "l", "f", "f", "f", "r", "f", "f", "f", "r", "f", "f", "f", "f", "r", "f", "f", "f", "f","l", "b", "b", "b", "b"};
        World.runAnimal(animal, OptionsParser.parse(moves));
        Assertions.assertEquals(animal.getPosition(), new Vector2d(0, 4));
    }

    @Test
    public void testMapLimitation() {
        Animal animal = new Animal();
        String[] moves = new String[]{"f", "f", "f", "f", "f", "f", "r","f", "f", "f", "f", "r", "f", "f" , "f", "f", "f", "f", "l", "l", "b", "b", "b"};
        World.runAnimal(animal, OptionsParser.parse(moves));
        Assertions.assertEquals(animal.getPosition(), new Vector2d(4, 0));
    }

    @Test
    public void testDataParse() {
        Animal animal = new Animal();
        String[] moves = new String[]{"r", "right", "rfgs", "l", "left", "lolol", "f", "forward", "fiaias", "b", "backward", "bhsbdha"};
        World.runAnimal(animal, OptionsParser.parse(moves));
        Assertions.assertEquals(animal.getPosition(), new Vector2d(2, 2));
    }

    @Test
    public void tesIsAt() {
        Animal animal = new Animal();
        animal.move(MoveDirection.FORWARD);
        Assertions.assertTrue(animal.isAt(new Vector2d(2,3)));
        Assertions.assertFalse(animal.isAt(new Vector2d(2,2)));

    }
}
