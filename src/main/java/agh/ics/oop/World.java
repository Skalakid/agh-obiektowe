package agh.ics.oop;

import java.util.Arrays;

public class World {
    public static void main(String[] args) {
        String[] moves = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(moves);
        IWorldMap map = new GrassField(5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
    }

    // zad 10
    // utworzyłbym ogolną osobną klasę Map w której przy każdym ruchu zapisywałbym położenie każdego utworzonego zwierzątka
    // i w metodzie move odpowiednio bym sprawdzał

    public static void runAnimal(Animal animal, MoveDirection[] movesArray) {
        for(MoveDirection move: movesArray) {
            animal.move(move);
        }
        System.out.println(animal);
    }

}
