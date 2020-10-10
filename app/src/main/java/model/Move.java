package model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Move {

    public enum Action {LEFT, RIGHT, UP, DOWN};

    public final int fromIndex;
    public final Action action;
    private final Board fromBoard;

    public Move(Board frmBoard, Action action) {
        switch (action){
            case UP: fromIndex = frmBoard.blankIndex-frmBoard.size; break;
            case DOWN: fromIndex = frmBoard.blankIndex+frmBoard.size; break;
            case LEFT: fromIndex = frmBoard.blankIndex+1; break;
            case RIGHT: fromIndex = frmBoard.blankIndex-1;break;
            default: fromIndex = -1;
        }
        this.action = action;
        this.fromBoard = frmBoard;
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        Move other = (Move) obj;
        return fromIndex == other.fromIndex && action == other.action;
    }

    @NonNull
    @Override
    public String toString() {
        int row  = fromBoard.getRow((fromIndex));
        int col = fromBoard.getCol(fromIndex);
        return "(" +col+ ", "+ row+") "+ action;
    }
}
