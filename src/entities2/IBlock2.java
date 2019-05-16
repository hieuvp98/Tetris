/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities2;

import entities_abstract2.SquareBase2;
import entities_abstract2.BlockBase2;
import javafx.scene.image.Image;
import views.MultiController;

/**
 * @author hoai
 */
public class IBlock2 extends BlockBase2 {

    private static final String URL = "images/xsquare1.png";

    public IBlock2(MultiController playArena, int mainCol, int mainRow) {
        super(playArena, mainCol, mainRow);
    }

    @Override
    public void init() {
        matrix[0] = new Square2(playArena, mainRow, mainCol - 1);
        matrix[1] = new Square2(playArena, mainRow, mainCol);
        matrix[2] = new Square2(playArena, mainRow, mainCol + 1);
        matrix[3] = new Square2(playArena, mainRow, mainCol + 2);
        for (SquareBase2 squareBase : matrix) {
            squareBase.getImageView().setImage(new Image(getClass().getClassLoader().getResource(URL).toExternalForm()));
        }
    }

    @Override
    public boolean checkTransformable() {
        switch (statusForm) {
            case 1: {
                if (mainRow == 0) {
                    return false;
                }
                SquareBase2[] squareBasesTop = playArena.getRows2().get(mainRow - 1).getSquareBases();
                SquareBase2[] squareBasesBottom1 = playArena.getRows2().get(mainRow + 1).getSquareBases();
                SquareBase2[] squareBasesBottom2 = playArena.getRows2().get(mainRow + 2).getSquareBases();
                if (squareBasesTop[mainCol - 1] != null || squareBasesTop[mainCol] != null
                        || squareBasesBottom1[mainCol] != null || squareBasesBottom1[mainCol + 1] != null || squareBasesBottom1[mainCol + 2] != null
                        || squareBasesBottom2[mainCol] != null || squareBasesBottom1[mainCol + 1] != null || squareBasesBottom2[mainCol + 2] != null) {
                    return false;
                }
                break;
            }
            case 2: {
                if (matrix[0].getIndexCol() == 0 || matrix[0].getIndexCol() == 1 || matrix[0].getIndexCol() == 9) {
                    return false;
                }
                SquareBase2[] squareBasesLeft1 = playArena.getColumns2().get(mainCol - 1).getSquareBases();
                SquareBase2[] squareBasesLeft2 = playArena.getColumns2().get(mainCol - 2).getSquareBases();
                SquareBase2[] squareBasesRight = playArena.getColumns2().get(mainCol + 1).getSquareBases();
                if (matrix[0].getIndexCol() == 0 || matrix[0].getIndexCol() == 1 || matrix[0].getIndexCol() == 9 || squareBasesLeft1[mainRow] != null || squareBasesLeft1[mainRow + 1] != null || squareBasesLeft1[mainRow + 2] != null
                        || squareBasesLeft2[mainRow] != null || squareBasesLeft2[mainRow + 1] != null || squareBasesLeft2[mainRow + 2] != null
                        || squareBasesRight[mainRow] != null || squareBasesRight[mainRow - 1] != null) {
                    return false;
                }
                break;
            }
            case 3: {
                if (mainRow < 2) {
                    return false;
                }
                SquareBase2[] squareBasesTop1 = playArena.getRows2().get(mainRow - 1).getSquareBases();
                SquareBase2[] squareBasesTop2 = playArena.getRows2().get(mainRow - 2).getSquareBases();
                SquareBase2[] squareBasesBottom = playArena.getRows2().get(mainRow + 1).getSquareBases();
                if (squareBasesTop1[mainCol - 2] != null || squareBasesTop1[mainCol - 1] != null || squareBasesTop1[mainCol] != null
                        || squareBasesTop2[mainCol - 2] != null || squareBasesTop2[mainCol - 1] != null || squareBasesTop2[mainCol] != null
                        || squareBasesBottom[mainCol] != null || squareBasesBottom[mainCol + 1] != null) {
                    return false;
                }
                break;
            }
            case 4: {
                if (matrix[0].getIndexCol() == 0 || matrix[0].getIndexCol() == 8 || matrix[0].getIndexCol() == 9) {
                    return false;
                }
                SquareBase2[] squareBasesLeft = playArena.getColumns2().get(mainCol - 1).getSquareBases();
                SquareBase2[] squareBasesRight1 = playArena.getColumns2().get(mainCol + 1).getSquareBases();
                SquareBase2[] squareBasesRight2 = playArena.getColumns2().get(mainCol + 2).getSquareBases();
                if (matrix[0].getIndexCol() == 0 || matrix[0].getIndexCol() == 8 || matrix[0].getIndexCol() == 9 || squareBasesLeft[mainRow] != null || squareBasesLeft[mainRow + 1] != null
                        || squareBasesRight1[mainRow] != null || squareBasesRight1[mainRow - 1] != null || squareBasesRight1[mainRow - 2] != null
                        || squareBasesRight2[mainRow] != null || squareBasesRight2[mainRow - 1] != null || squareBasesRight2[mainRow - 2] != null) {
                    return false;
                }
            }
            break;
        }
        return true;
    }

    @Override
    public void form1() {
        matrix[0].reLocate(mainRow, mainCol - 1);
        matrix[1].reLocate(mainRow, mainCol);
        matrix[2].reLocate(mainRow, mainCol + 1);
        matrix[3].reLocate(mainRow, mainCol + 2);
    }

    @Override
    public void form2() {
        matrix[0].reLocate(mainRow - 1, mainCol);
        matrix[2].reLocate(mainRow + 1, mainCol);
        matrix[3].reLocate(mainRow + 2, mainCol);
    }

    @Override
    public void form3() {
        matrix[0].reLocate(mainRow, mainCol + 1);
        matrix[2].reLocate(mainRow, mainCol - 1);
        matrix[3].reLocate(mainRow, mainCol - 2);

    }

    @Override
    public void form4() {
        matrix[0].reLocate(mainRow + 1, mainCol);
        matrix[2].reLocate(mainRow - 1, mainCol);
        matrix[3].reLocate(mainRow - 2, mainCol);
    }

}
