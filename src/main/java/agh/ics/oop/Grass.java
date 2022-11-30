package agh.ics.oop;

public class Grass extends AbstractMapElement {
    public Grass(Vector2d position) {
        this.position = position;
    }
    private String imagePath = "src/main/resources/grass.png";
    @Override
    public String toString() {
        return "*";
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public String getLabel() {
        return "Trawa";
    }
}
