package agh.ics.oop;

import java.util.Comparator;
import java.util.TreeMap;

public class MapBoundary implements IPositionChangeObserver {
    private final TreeMap<Vector2d, Integer> yCordsSet = new TreeMap<>(Comparator.comparingInt(elem -> elem.y));
    private final TreeMap<Vector2d, Integer> xCordsSet = new TreeMap<>(Comparator.comparingInt(elem -> elem.x));


    public void removeCords(Vector2d position) {
        yCordsSet.remove(position);
        xCordsSet.remove(position);
    }

    public void addCords(Vector2d position) {
        yCordsSet.put(position, position.y);
        xCordsSet.put(position, position.x);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        removeCords(oldPosition);
        addCords(newPosition);
    }

    public Vector2d[] getMinBorders() {
        return new Vector2d[]{new Vector2d(xCordsSet.get(xCordsSet.firstKey()), yCordsSet.get(yCordsSet.firstKey())),
                new Vector2d(xCordsSet.get(xCordsSet.lastKey()), yCordsSet.get(yCordsSet.lastKey()))};
    }

}
