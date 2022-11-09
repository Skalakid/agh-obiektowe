package agh.ics.oop;

import java.util.Random;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private Integer limit;

    public GrassField(Integer numberOfGrassFields) {
        this.limit = ((int)Math.sqrt(numberOfGrassFields * 10)) + 1;
        this.setRandomGrassFields(numberOfGrassFields);
    }

    private void setRandomGrassFields(int n) {
        Random generator = new Random();
        int i = 0;
        while(i < n) {
            int x = generator.nextInt(this.limit - 1);
            int y = generator.nextInt(this.limit - 1);
            Object objectAtThisArea = this.objectAt(new Vector2d(x, y));
            if(!(objectAtThisArea instanceof Grass) && !(objectAtThisArea instanceof Animal)) {
                objects.add(new Grass(new Vector2d(x, y)));
                i += 1;
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Object object = objectAt(position);
        if(object instanceof Grass) {
            this.objects.remove(object);
            setRandomGrassFields(1);
        } else if(object instanceof Animal) {
            return false;
        }
        return true;
    }

    public String toString() {
        for (AbstractMapElement object: this.objects) {
            this.bottomBoundary = this.bottomBoundary.lowerLeft(object.getPosition());
            this.topBoundary = this.topBoundary.upperRight(object.getPosition());
        }

        return super.toString();
    }

}
