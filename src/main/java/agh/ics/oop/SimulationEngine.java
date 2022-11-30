package agh.ics.oop;

import java.util.ArrayList;
import agh.ics.oop.gui.App;
import javafx.application.Platform;

public class SimulationEngine implements IEngine, Runnable {
    private MoveDirection[] directions = new MoveDirection[] {};
    private final IWorldMap map;
    private final ArrayList<Animal> animals = new ArrayList<>();
    private App app = null;
    private int appDelay = 500;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;

        for(Vector2d position: positions) {
            Animal animal = new Animal(this.map, position);
            if(this.map.place(animal)) {
                this.animals.add(animal);
            }
        }
    }

    public SimulationEngine(IWorldMap map, Vector2d[] positions, App app, int appDelay) {
        this.map = map;
        this.app = app;
        this.appDelay = appDelay;

        for(Vector2d position: positions) {
            Animal animal = new Animal(this.map, position);
            if(this.map.place(animal)) {
                this.animals.add(animal);
            }
        }
    }

    public void setDirections(MoveDirection[] directions) {
        this.directions = directions;
    }
    @Override
    public void run() {
        int numberOfAnimals = this.animals.size();
        if(this.app != null) {
            try {
                Platform.runLater(app::renderGridPane);
                for (int i = 0; i < directions.length; i++) {
                    animals.get(i % numberOfAnimals).move(directions[i]);
                    Platform.runLater(app::renderGridPane);
                    Thread.sleep(this.appDelay);
                    System.out.println(this.map);
                }
            } catch (InterruptedException exception) {
                System.out.println("The application has stopped " + exception);
                exception.printStackTrace();
            }
        }
        else {
            for(int i = 0; i < directions.length; i++) {
                animals.get(i % numberOfAnimals).move(directions[i]);
                System.out.println(this.map);
            }
        }
    }
}
