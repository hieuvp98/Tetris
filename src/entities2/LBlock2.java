/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities2;

import entities.*;
import entities_abstract.BlockBase;
import entities_abstract.SquareBase;
import entities_abstract2.BlockBase2;
import entities_abstract2.SquareBase2;
import javafx.scene.image.Image;
import views.Controller;
import views.MultiController;

/**
 * @author hoai
 */
public class LBlock2 extends BlockBase2 {

    private static final String URL = "images/xsquare3.png";

    public LBlock2(MultiController playArena, int mainCol, int mainRow) {
        super(playArena, mainCol, mainRow);
    }

    @Override
    public void init() {
        matrix[0] = new Square2(playArena, mainRow - 1, mainCol);
        matrix[1] = new Square2(playArena, mainRow, mainCol);
        matrix[2] = new Square2(playArena, mainRow + 1, mainCol);
        matrix[3] = new Square2(playArena, mainRow + 1, mainCol + 1);
        for (SquareBase2 squareBase : matrix) {
            squareBase.getImageView().setImage(new Image(getClass().getClassLoader().getResource(URL).toExternalForm()));
        }
    }

    @Override
    public boolean checkTransformable() {
        switch (statusForm) {
            case 1: {
                if (matrix[0].getIndexCol() == 0) {
                    return false;
                }
                SquareBase2[] squareBasesLeft = playArena.getColumns2().get(mainCol - 1).getSquareBases();
                SquareBase2[] squareBasesRight = playArena.getColumns2().get(mainCol + 1).getSquareBases();
                if (matrix[0].getIndexCol() == 0 || squareBasesLeft[mainRow] != null || squareBasesLeft[mainRow + 1] != null
                        || squareBasesRight[mainRow] != null || squareBasesRight[mainRow - 1] != null) {
                    return false;
                }
                break;
            }
            case 2: {
                SquareBase2[] squareBasesTop = playArena.getRows2().get(mainRow - 1).getSquareBases();
                SquareBase2[] squareBasesBottom = playArena.getRows2().get(mainRow + 1).getSquareBases();
                if (squareBasesTop[mainCol - 1] != null || squareBasesTop[mainCol] != null
                        || squareBasesBottom[mainCol] != null || squareBasesBottom[mainCol + 1] != null) {
                    return false;
                }
                break;

            }
            case 3: {
                if (matrix[0].getIndexCol() == 9) {
                    return false;
                }
                SquareBase2[] squareBasesLeft = playArena.getColumns2().get(mainCol - 1).getSquareBases();
                SquareBase2[] squareBasesRight = playArena.getColumns2().get(mainCol + 1).getSquareBases();
                if (matrix[0].getIndexCol() == 9 || squareBasesLeft[mainRow] != null || squareBasesLeft[mainRow + 1] != null
                        || squareBasesRight[mainRow] != null || squareBasesRight[mainRow - 1] != null) {
                    return false;
                }
                break;
            }

            case 4: {
                SquareBase2[] squareBasesTop = playArena.getRows2().get(mainRow - 1).getSquareBases();
                SquareBase2[] squareBasesBottom = playArena.getRows2().get(mainRow + 1).getSquareBases();
                if (squareBasesTop[mainCol - 1] != null || squareBasesTop[mainCol] != null
                        || squareBasesBottom[mainCol] != null || squareBasesBottom[mainCol + 1] != null) {
                    return false;
                }
                break;
            }
        }
        return true;
    }

    @Override
    public void form1() {
        matrix[0].reLocate(mainRow - 1, mainCol);
        matrix[1].reLocate(mainRow, mainCol);
        matrix[2].reLocate(mainRow + 1, mainCol);
        matrix[3].reLocate(mainRow + 1, mainCol + 1);

    }

    @Override
    public void form2() {
        matrix[0].reLocate(mainRow, mainCol + 1);
        matrix[2].reLocate(mainRow, mainCol - 1);
        matrix[3].reLocate(mainRow + 1, mainCol - 1);
    }

    @Override
    public void form3() {
        matrix[0].reLocate(mainRow + 1, mainCol);
        matrix[2].reLocate(mainRow - 1, mainCol);
        matrix[3].reLocate(mainRow - 1, mainCol - 1);
    }

    @Override
    public void form4() {
        matrix[0].reLocate(mainRow, mainCol - 1);
        matrix[2].reLocate(mainRow, mainCol + 1);
        matrix[3].reLocate(mainRow - 1, mainCol + 1);
    }

}
