package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parse(String[] moves) {
        MoveDirection[] convertedMoves = new MoveDirection[moves.length];
        int i = 0;
        for(String move: moves) {
            if(move == "f" || move =="forward") {
                convertedMoves[i] = MoveDirection.FORWARD;
                i+=1;
            } else  if(move == "b" || move =="backward") {
                convertedMoves[i] = MoveDirection.BACKWARD;
                i+=1;
            } else  if(move == "r" || move =="right") {
                convertedMoves[i] = MoveDirection.RIGHT;
                i+=1;
            } else  if(move == "l" || move =="left") {
                convertedMoves[i] = MoveDirection.LEFT;
                i+=1;
            }
        }

        return Arrays.copyOfRange(convertedMoves, 0, i);
    }
}
