package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.Arrays;

public class World {
    public static void main(String[] args) {

        try {
            Application.launch(App.class, args);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }

    }

    public static void runAnimal(Animal animal, MoveDirection[] movesArray) {
        for(MoveDirection move: movesArray) {
            animal.move(move);
        }
        System.out.println(animal);
    }

}
