package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Vector2d bottomBoundary = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected Vector2d topBoundary = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

    protected final Map<Vector2d, AbstractMapElement> objects = new HashMap<>();
    protected MapBoundary mapBoundary = new MapBoundary();

    @Override
    public boolean place(Animal animal) {
        System.out.println(animal.getPosition());
        if (canMoveTo(animal.getPosition())) {
            this.objects.put(animal.getPosition(), animal);
            mapBoundary.addCords(animal.getPosition());
            return true;
        }
        throw new IllegalArgumentException("Position " + animal.getPosition() + " is already taken. You can't moce to this position");
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
        this.mapBoundary.removeCords(oldPosition);
        this.mapBoundary.addCords(newPosition);
    }



    public String toString() {
        Vector2d[] borders = mapBoundary.getMinBorders();
        this.bottomBoundary = this.bottomBoundary.lowerLeft(borders[0]);
        this.topBoundary = this.topBoundary.upperRight(borders[1]);

        System.out.println(borders[1]);

        return new MapVisualizer(this).draw(bottomBoundary, topBoundary);
    }
}