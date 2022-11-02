package agh.ics.oop;

import java.util.Map;

public class Animal {

    private IWorldMap map;
    private MapDirection orientation;
    private Vector2d position;
    private Integer boundValue = 4;


    public MapDirection getOrientation() {
        return orientation;
    }
    public Vector2d getPosition() {
        return position;
    }

    public Animal(IWorldMap map) {
        this.map = map;
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.orientation = MapDirection.NORTH;
        this.position = initialPosition;
    }

    @Override
    public String toString() {
        if(orientation == MapDirection.NORTH) {
            return "N";
        } else if(orientation == MapDirection.WEST) {
            return "W";
        }
        else if(orientation == MapDirection.SOUTH) {
            return "S";
        }
        else {
            return "E";
        }
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
                if( map.canMoveTo(new Vector2d(this.position.x,this.position.y + move))) {
                    this.position = this.position.add(new Vector2d(0, move));
                }
                break;
            case EAST:
                if(map.canMoveTo(new Vector2d(this.position.x + move, this.position.y))) {
                    this.position = this.position.add(new Vector2d(move, 0));
                }
                break;
            case SOUTH:
                if(map.canMoveTo(new Vector2d(this.position.x,this.position.y - move))) {
                    this.position = this.position.add(new Vector2d(0, -move));
                }
                break;
            case WEST:
                if(map.canMoveTo(new Vector2d(this.position.x - move, this.position.y))) {
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
