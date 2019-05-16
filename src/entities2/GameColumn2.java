package entities2;

import entities_abstract.SquareBase;
import entities_abstract2.GameColumnBase2;
import entities_abstract2.SquareBase2;
import views.Controller;

import views.MultiController;

public class GameColumn2 extends GameColumnBase2 {

    public GameColumn2(MultiController playArenaBase, int indexCol) {
        super(playArenaBase, indexCol);
    }

    @Override
    public boolean checkFull() {
        int dem = 0;
        for (SquareBase2 squareBase : this.squareBases) {
            if (squareBase != null) dem++;
        }
        return dem == Controller.TOTAL_ROW;
    }

    @Override
    public void addSquare(SquareBase2 square, int index) {
        this.squareBases[index] = square;
    }

    @Override
    public void removeSquare(SquareBase2 square) {
        for (int i = 0; i < this.squareBases.length; i++) {
            if (square == squareBases[i]){
                squareBases[i] = null;
                break;
            }
        }
    }
}
