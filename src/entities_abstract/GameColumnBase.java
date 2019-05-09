package entities_abstract;

import entities.Square;
import views.Controller;

import java.util.ArrayList;

public abstract class GameColumnBase {

    protected SquareBase[] squareBases;

    protected Controller playArenaBase;

    protected int indexCol;

    public GameColumnBase(Controller playArenaBase,int indexCol) {
        this.playArenaBase = playArenaBase;
        squareBases = new SquareBase[Controller.TOTAL_ROW];
        this.indexCol = indexCol;
    }

    public abstract boolean checkFull();

    public abstract void addSquare(SquareBase square, int index);

    public abstract void removeSquare(SquareBase square);

    public SquareBase[] getSquareBases() {
        return squareBases;
    }

    public void setSquareBases(SquareBase[] squareBases) {
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

    public void setPlayArenaBase(Controller playArenaBase) {
        this.playArenaBase = playArenaBase;
    }
}
