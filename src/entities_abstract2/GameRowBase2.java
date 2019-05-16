package entities_abstract2;

import entities_abstract.*;
import entities.Square;
import views.Controller;
import java.util.ArrayList;
import views.MultiController;

public abstract class GameRowBase2 {

    protected SquareBase2[] squareBases;

    protected MultiController playArenaBase;

    protected int indexRow;

    public GameRowBase2(MultiController playArenaBase, int index) {
        this.playArenaBase = playArenaBase;
        this.squareBases = new SquareBase2[10];
        this.indexRow = index;
    }

    public abstract boolean checkFull();

    public abstract void remove();

    public abstract void addSquare(SquareBase2 square,int index);

    public abstract void removeSquare(SquareBase2 square);

    public SquareBase2[] getSquareBases() {
        return squareBases;
    }

    public void setSquareBases(SquareBase2[] squareBases) {
        this.squareBases = squareBases;
    }

    public Controller getPlayArenaBase() {
        return playArenaBase;
    }

    public void setPlayArenaBase(MultiController playArenaBase) {
        this.playArenaBase = playArenaBase;
    }

    public int getIndexRow() {
        return indexRow;
    }

    public void setIndexRow(int indexRow) {
        this.indexRow = indexRow;
    }

}
