package entities_abstract;

import entities.Square;
import views.Controller;
import java.util.ArrayList;

public abstract class GameRowBase {

    protected SquareBase[] squareBases;

    protected Controller playArenaBase;

    protected int indexRow;

    public GameRowBase(Controller playArenaBase, int index) {
        this.playArenaBase = playArenaBase;
        this.squareBases = new SquareBase[10];
        this.indexRow = index;
    }

    public abstract boolean checkFull();

    public abstract void remove();

    public abstract void addSquare(SquareBase square,int index);

    public abstract void removeSquare(SquareBase square);

    public SquareBase[] getSquareBases() {
        return squareBases;
    }

    public void setSquareBases(SquareBase[] squareBases) {
        this.squareBases = squareBases;
    }

    public Controller getPlayArenaBase() {
        return playArenaBase;
    }

    public void setPlayArenaBase(Controller playArenaBase) {
        this.playArenaBase = playArenaBase;
    }

    public int getIndexRow() {
        return indexRow;
    }

    public void setIndexRow(int indexRow) {
        this.indexRow = indexRow;
    }

}
