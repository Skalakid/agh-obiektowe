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
                Vector2d newPosition = new Vector2d(x, y);
                objects.put(newPosition, new Grass(newPosition));
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
        return super.toString();
    }

}
