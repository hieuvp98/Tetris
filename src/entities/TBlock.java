package entities;

import entities_abstract.BlockBase;
import entities_abstract.SquareBase;
import javafx.scene.image.Image;
import views.Controller;

public class TBlock extends BlockBase {

    private static final String URL = "images/xsquare6.png";


    public TBlock(Controller playArena, int mainCol, int mainRow) {
        super(playArena, mainCol, mainRow);
    }

    @Override
    public void init() {
        matrix[0] = new Square(playArena, mainRow - 1, mainCol);
        matrix[1] = new Square(playArena, mainRow, mainCol - 1);
        matrix[2] = new Square(playArena, mainRow, mainCol);
        matrix[3] = new Square(playArena, mainRow, mainCol + 1);
        for (SquareBase squareBase : matrix) {
            squareBase.getImageView().setImage(new Image(getClass().getClassLoader().getResource(URL).toExternalForm()));
        }
    }

    @Override
    public boolean checkTransformable() {
        System.out.println("checking");
        switch (statusForm) {
            case 1: {
                SquareBase[] squareBasesTop = playArena.getRows().get(mainRow - 1).getSquareBases();
                SquareBase[] squareBasesBottom = playArena.getRows().get(mainRow + 1).getSquareBases();
                if (squareBasesTop[mainCol - 1] != null || squareBasesTop[mainCol + 1] != null
                        || squareBasesBottom[mainCol] != null || squareBasesBottom[mainCol + 1] != null)
                    return false;
                break;
            }
            case 2: {
                if (matrix[0].getIndexCol() == 1)
                    return false;               
                SquareBase[] squareBasesLeft = playArena.getColumns().get(mainCol - 1).getSquareBases();
                SquareBase[] squareBasesRight = playArena.getColumns().get(mainCol + 1).getSquareBases();
                if (matrix[0].getIndexCol() == 1||squareBasesLeft[mainRow] != null || squareBasesLeft[mainRow + 1] != null
                        || squareBasesRight[mainRow - 1] != null || squareBasesRight[mainRow + 1] != null)
                    return false;               
                break;
            }
            case 3:{
                SquareBase[] squareBasesTop = playArena.getRows().get(mainRow - 1).getSquareBases();
                SquareBase[] squareBasesBottom = playArena.getRows().get(mainRow + 1).getSquareBases();
                if (squareBasesTop[mainCol - 1] != null || squareBasesTop[mainCol] != null
                        || squareBasesBottom[mainCol - 1] != null || squareBasesBottom[mainCol + 1] != null)
                    return false;
                break;
            }
            case 4: {
                if (matrix[2].getIndexCol() == 9) 
                    return false;
                SquareBase[] squareBasesLeft = playArena.getColumns().get(mainCol - 1).getSquareBases();
                SquareBase[] squareBasesRight = playArena.getColumns().get(mainCol + 1).getSquareBases();
                if (matrix[2].getIndexCol() == 9||squareBasesLeft[mainRow - 1] != null || squareBasesLeft[mainRow + 1] != null
                        || squareBasesRight[mainRow - 1] != null || squareBasesRight[mainRow] != null)
                    return false;               
                break;
            }
        }
        return true;
    }

    @Override
    public void form1() {
        matrix[0].reLocate(mainRow - 1, mainCol);
        matrix[1].reLocate(mainRow, mainCol - 1);
        matrix[2].reLocate(mainRow, mainCol);
        matrix[3].reLocate(mainRow, mainCol + 1);
    }

    @Override
    public void form2() {
        matrix[0].reLocate(mainRow, mainCol + 1);
        matrix[1].reLocate(mainRow - 1, mainCol);
        matrix[3].reLocate(mainRow + 1, mainCol);
    }

    @Override
    public void form3() {
        matrix[0].reLocate(mainRow + 1, mainCol);
        matrix[1].reLocate(mainRow, mainCol + 1);
        matrix[3].reLocate(mainRow, mainCol - 1);
    }

    @Override
    public void form4() {
        matrix[0].reLocate(mainRow, mainCol - 1);
        matrix[1].reLocate(mainRow + 1, mainCol);
        matrix[3].reLocate(mainRow - 1, mainCol);
    }
}
