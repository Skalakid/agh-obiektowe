package agh.ics.oop;

import java.util.Map;

public class Animal {


    private MapDirection orientation;
    private Vector2d position;

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    public Animal() {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
    }

    @Override
    public String toString() {
        return "Position: " + this.position + ", orientation: " + this.orientation;
    }

    public boolean isAt(Vector2d position) {
        return position.x == this.position.x && position.y == this.position.y;
    }

    private void makeMove(MoveDirection currentMove) {
        int move = 1;

        if(currentMove == MoveDirection.BACKWARD) {
            move = -1;
        }

        switch (this.orientation) {
            case NORTH:
                if(0 <= this.position.y + move && this.position.y + move <= 4) {
                    this.position = this.position.add(new Vector2d(0, move));
                }
                break;
            case EAST:
                if(0 <= this.position.x + move && this.position.x + move <= 4) {
                    this.position = this.position.add(new Vector2d(move, 0));
                }
                break;
            case SOUTH:
                if(0 <= this.position.y - move && this.position.y - move <= 4) {
                    this.position = this.position.add(new Vector2d(0, -move));
                }
                break;
            case WEST:
                if(0 <= this.position.x - move && this.position.x - move <= 4) {
                    this.position = this.position.add(new Vector2d(-move, 0));
                }
                break;
        }
    }

    public void move(MoveDirection direction) {
        switch(direction) {
            case RIGHT:
                this.orientation = this.orientation.next();
                break;
            case LEFT:
                this.orientation = this.orientation.previous();
                break;
            case FORWARD:
            case BACKWARD:
                makeMove(direction);
                break;
        }

    }
}
