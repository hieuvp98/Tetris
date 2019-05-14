package entities;

import entities_abstract.GameRowBase;
import entities_abstract.SquareBase;
import views.Controller;

public class GameRow extends GameRowBase {

    public GameRow(Controller playArenaBase, int index) {
        super(playArenaBase, index);
    }

    @Override
    public boolean checkFull() {
        int dem = 0;
        for (SquareBase squareBase : this.squareBases) {
            if (squareBase != null) {
                dem++;
            }
        }
        return dem == 10;
    }

    @Override
    public void remove() {
        for (int i = 0; i < 10; i++) {
            this.playArenaBase.gridPane.getChildren().remove(squareBases[i].getImageView());
            
            
        }
        squareBases = new SquareBase[10];
        playArenaBase.getColumns().forEach(gameColumnBase -> {
            gameColumnBase.getSquareBases()[this.indexRow] = null;
        });
    }

    @Override
    public void addSquare(SquareBase square, int index) {
        this.squareBases[index] = square;
        this.playArenaBase.gridPane.add(square.getImageView(), index, this.indexRow);
    }

    @Override
    public void removeSquare(SquareBase square) {
        for (int i = 0; i < this.squareBases.length; i++) {
            if (square == squareBases[i]) {
                squareBases[i] = null;
                break;
            }
        }
        this.playArenaBase.gridPane.getChildren().remove(square.getImageView());
    }
}
