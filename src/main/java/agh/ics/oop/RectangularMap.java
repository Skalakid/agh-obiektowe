package agh.ics.oop;

import java.util.ArrayList;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {

    private final ArrayList<Animal> animals = new ArrayList<>();

    public RectangularMap(Integer width, Integer height) {
        this.topBoundary = new Vector2d(width - 1, height - 1);
        this.bottomBoundary = new Vector2d(0, 0);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return  !isOccupied(position) && position.follows(bottomBoundary) &&
                position.precedes(topBoundary);
    }

    @Override
    public Vector2d[] getMinBorders() {
        return new Vector2d[]{this.bottomBoundary, this.topBoundary};
    }


}
