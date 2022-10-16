package agh.ics.oop;

import java.util.Arrays;

public class World {
    public static void main(String[] args) {
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));


        System.out.println("\nZad 6:");
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d v2 = new Vector2d(2, 2);
        Vector2d v3 = new Vector2d(2, 3);
        System.out.println(v1.precedes(v2) + " " + v1.precedes(v3));
        System.out.println(v3.follows(v1) + " " + v3.follows(v2));
        System.out.println(v1.follows(v1) + " " +v2.follows(v2)+  " " + v3.follows(v3));

        System.out.println("\nZad 7:");
        Vector2d v4 = new Vector2d(1, 2);
        Vector2d v5 = new Vector2d(2, 1);
        System.out.println(v4.upperRight(v5) + " " + v4.lowerLeft(v5) + "\n");

        System.out.println(MapDirection.NORTH.next());
        System.out.println(MapDirection.NORTH.previous());
        System.out.println(MapDirection.NORTH.next().previous().toUnitVector());



        System.out.println("System wystartował");
        run(convertToDirectionArray(args));
        System.out.println("System zakończył działanie");
    }

    public static void run(Direction [] arguments) {
        int i = 0;
        for(Direction arg: arguments) {
            String message = switch (arg) {
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tyłu";
                case RIGHT -> "Zwierzak skręca w prawo";
                case LEFT -> "Zwierzak skręca w lewo";
                default -> "Błąd";
            };

            System.out.print(message);
            if(i < arguments.length - 1) {
                System.out.print(", ");
            }
            i++;
        }
        System.out.println();
    }

    public static Direction[] convertToDirectionArray(String[] arr) {
        Direction[] directions = new Direction[arr.length];
        int i = 0;
        for(String element: arr) {
            switch(element) {
                case "f":
                    directions[i] = Direction.FORWARD;
                    break;
                case "b":
                    directions[i] = Direction.BACKWARD;
                    break;
                case "r":
                    directions[i] = Direction.RIGHT;
                    break;
                case "l":
                    directions[i] = Direction.LEFT;
                    break;
                default: continue;

            }
            i++;
        }

        return Arrays.copyOfRange(directions, 0, i);
    }
}
