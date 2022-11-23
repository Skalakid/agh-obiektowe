package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parse(String[] moves) {
        MoveDirection[] convertedMoves = new MoveDirection[moves.length];
        int i = 0;
        for(String move: moves) {
            if(move.equals("f")  || move.equals("forward")) {
                convertedMoves[i] = MoveDirection.FORWARD;
                i+=1;
            } else if(move.equals("b") || move.equals("backward")) {
                convertedMoves[i] = MoveDirection.BACKWARD;
                i+=1;
            } else if(move.equals("r") || move.equals("right")) {
                convertedMoves[i] = MoveDirection.RIGHT;
                i+=1;
            } else if(move.equals("l") || move.equals("left")) {
                convertedMoves[i] = MoveDirection.LEFT;
                i+=1;
            } else {
                throw new IllegalArgumentException(move + " is not legal move specification");
            }
        }

        return Arrays.copyOfRange(convertedMoves, 0, i);
    }
}
