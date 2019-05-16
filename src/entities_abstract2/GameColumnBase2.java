package entities_abstract2;

import entities_abstract.*;
import entities.Square;
import views.Controller;

import java.util.ArrayList;
import views.MultiController;

public abstract class GameColumnBase2 {

    public SquareBase2[] squareBases;

    protected MultiController playArenaBase;

    protected int indexCol;

    public GameColumnBase2(MultiController playArenaBase,int indexCol) {
        this.playArenaBase = playArenaBase;
        squareBases = new SquareBase2[Controller.TOTAL_ROW];
        this.indexCol = indexCol;
    }

    public abstract boolean checkFull();

    public abstract void addSquare(SquareBase2 square, int index);

    public abstract void removeSquare(SquareBase2 square);

    public SquareBase2[] getSquareBases() {
        return squareBases;
    }

    public void setSquareBases(SquareBase2[] squareBases) {
        this.squareBases = squareBases;
    }

    public int getIndexCol() {
        return indexCol;
    }

    public void setIndexCol(int indexCol) {
        this.indexCol = indexCol;
    }

    public Controller getPlayArenaBase() {
        return playArenaBase;
    }

    public void setPlayArenaBase(MultiController playArenaBase) {
        this.playArenaBase = playArenaBase;
    }
}
