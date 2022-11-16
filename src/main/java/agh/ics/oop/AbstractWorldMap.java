package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Vector2d bottomBoundary = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected Vector2d topBoundary = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

    protected final Map<Vector2d, AbstractMapElement> objects = new HashMap<>();


    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.objects.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
       return this.objects.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.objects.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractMapElement animal = this.objects.get(oldPosition);
        this.objects.remove(oldPosition);
        this.objects.put(newPosition, animal);
    }

    public String toString() {


        for(Map.Entry<Vector2d, AbstractMapElement> entry: this.objects.entrySet()) {
            this.bottomBoundary = this.bottomBoundary.lowerLeft(entry.getKey());
            this.topBoundary = this.topBoundary.upperRight(entry.getKey());
        }


        return new MapVisualizer(this).draw(bottomBoundary, topBoundary);
    }
}