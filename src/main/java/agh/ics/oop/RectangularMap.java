package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap implements IWorldMap {
    private Integer width = 4;
    private Integer height = 4;

    private final ArrayList<Animal> animals = new ArrayList<>();

    public RectangularMap(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return new MapVisualizer(this).draw(new Vector2d(0, 0), new Vector2d(this.width - 1, this.height - 1));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return  !isOccupied(position) && position.follows(new Vector2d(0, 0)) &&
                position.precedes(new Vector2d(this.width - 1, this.height - 1));
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(Animal animal: this.animals) {
            if(animal.isAt(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal animal: this.animals) {
            if(animal.isAt(position)) {
                return animal;
            }
        }
        return null;
    }
}
