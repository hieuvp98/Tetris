package entities2;

import entities_abstract2.GameColumnBase2;
import entities_abstract2.GameRowBase2;
import entities_abstract2.SquareBase2;
import views.Controller;
import views.MultiController;

public class Square2 extends SquareBase2 {


    public Square2(MultiController playArena, int indexRow, int indexCol) {
        super(playArena, indexRow, indexCol);
    }

    @Override
    public void addToPanel() {
        playArena.getRows2().get(indexRow).addSquare(this, indexCol);
        playArena.getColumns2().get(indexCol).addSquare(this, indexRow);
    }

    @Override
    public void changeCol() {// this.preIndexRow = this.indexRow;

    }

    @Override
    public void move() {
        if (this.indexRow == Controller.TOTAL_ROW - 1) return;
        this.preIndexCol = this.indexCol;
        this.preIndexRow = this.indexRow;
        if ((directionX == 1 && indexCol < 14) || (directionX == -1 && indexCol > 0))
            this.indexCol += directionX;
        if (count == 4){
            this.indexRow++;
            count = 0;
        }
        count++;
    }

    @Override
    public void updateUI() {
        GameRowBase2 row = playArena.getRows2().get(preIndexRow);
        row.removeSquare(this);
        GameColumnBase2 col = playArena.getColumns2().get(preIndexCol);
        col.removeSquare(this);
        playArena.getRows2().get(indexRow).addSquare(this, indexCol);
        playArena.getColumns2().get(indexCol).addSquare(this, indexRow);
    }

    @Override
    public boolean checkMoveDown() {
        if (indexRow == Controller.TOTAL_ROW - 1) return false;
        GameColumnBase2 col = playArena.getColumns2().get(this.indexCol);
        SquareBase2[] squares = col.getSquareBases();
        if (squares[this.indexRow+1] != null){          
            return false;}
        return true;

    }

    @Override
    public boolean checkMoveLeft() {
        if (indexCol == 0) return false;
        return playArena.getRows2().get(this.indexRow).getSquareBases()[this.indexCol - 1] == null;
    }

    @Override
    public boolean checkMoveRight() {
        if (indexCol == 9) return false;
        return playArena.getRows2().get(this.indexRow).getSquareBases()[this.indexCol + 1] == null;
    }

    @Override
    public void reLocate(int row, int col) {
        this.preIndexCol = this.indexCol;
        this.preIndexRow = this.indexRow;
        this.indexCol = col;
        this.indexRow = row;
    }
}
