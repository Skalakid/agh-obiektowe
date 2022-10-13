package agh.ics.oop;

import java.util.Arrays;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystartował");
//        String[] arr = {"f", "gfhg", "l", "g"};
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
