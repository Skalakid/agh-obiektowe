package agh.ics.oop;

import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap {
    protected Vector2d bottomBoundary = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected Vector2d topBoundary = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
    protected final ArrayList<AbstractMapElement> objects = new ArrayList<>();


    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.objects.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (AbstractMapElement object: this.objects) {
            if (object.isAt(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (AbstractMapElement object: this.objects) {
            if (object.isAt(position)) {
                return object;
            }
        }
        return null;
    }

    public String toString() {
        return new MapVisualizer(this).draw(bottomBoundary, topBoundary);
    }
}