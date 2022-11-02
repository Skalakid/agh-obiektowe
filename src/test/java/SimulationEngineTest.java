import agh.ics.oop.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class SimulationEngineTest {

    @Test
    public void testRun() {
        String[] moves1 = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions1 = new OptionsParser().parse(moves1);
        IWorldMap map1 = new RectangularMap(10, 5);
        Vector2d[] positions1 = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine1 = new SimulationEngine(directions1, map1, positions1);
        engine1.run();

        Vector2d[] testPositionResult1 = new Vector2d[]{new Vector2d(2, 0), new Vector2d(3, 4) };
        // test animals
        for (int i = 0; i < 2; i++) {
            Animal animal = (Animal)(map1.objectAt(testPositionResult1[i]));
            Assertions.assertEquals(animal.getPosition(), testPositionResult1[i]);
        }

        Assertions.assertNull(map1.objectAt(new Vector2d(2, 2)));
        Assertions.assertNull(map1.objectAt(new Vector2d(100, 2)));
        Assertions.assertNull(map1.objectAt(new Vector2d(2, 100)));
        Assertions.assertNull(map1.objectAt(new Vector2d(-100, -100)));


        String[] moves2 = new String[]{"r", "l", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions2 = new OptionsParser().parse(moves2);
        IWorldMap map2 = new RectangularMap(10, 5);
        Vector2d[] positions2 = { new Vector2d(2,2), new Vector2d(3,4)  };
        IEngine engine2 = new SimulationEngine(directions2, map2, positions2);
        engine2.run();

        Vector2d[] testPositionResult2 = new Vector2d[]{new Vector2d(9, 2), new Vector2d(0, 4) };
        // test animals
        for (int i = 0; i < testPositionResult2.length; i++) {
            Animal animal = (Animal)(map2.objectAt(testPositionResult2[i]));
            Assertions.assertEquals(animal.getPosition(), testPositionResult2[i]);
        }


    }
}
