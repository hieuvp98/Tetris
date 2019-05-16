package entities2;

import entities_abstract2.GameRowBase2;
import entities_abstract2.SquareBase2;
import views.MultiController;

public class GameRow2 extends GameRowBase2 {

    public GameRow2(MultiController playArenaBase, int index) {
        super(playArenaBase, index);
    }

    @Override
    public boolean checkFull() {
        int dem = 0;
        for (SquareBase2 squareBase : this.squareBases) {
            if (squareBase != null) {
                dem++;
            }
        }
        return dem == 10;
    }

    @Override
    public void remove() {
        for (int i = 0; i < 10; i++) {
            this.playArenaBase.getGridPane2().getChildren().remove(squareBases[i].getImageView());       
        }
        squareBases = new SquareBase2[10];
        playArenaBase.getColumns2().forEach(gameColumnBase -> {
            gameColumnBase.getSquareBases()[this.indexRow] = null;
        });
    }

    @Override
    public void addSquare(SquareBase2 square, int index) {
        this.squareBases[index] = square;
        this.playArenaBase.getGridPane2().add(square.getImageView(), index, this.indexRow);
    }

    @Override
    public void removeSquare(SquareBase2 square) {
        for (int i = 0; i < this.squareBases.length; i++) {
            if (square == squareBases[i]) {
                squareBases[i] = null;
                break;
            }
        }
        this.playArenaBase.getGridPane2().getChildren().remove(square.getImageView());
    }
}
