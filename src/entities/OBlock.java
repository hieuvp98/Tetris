package entities;

import entities_abstract.BlockBase;
import entities_abstract.SquareBase;
import javafx.scene.image.Image;
import views.Controller;

public class OBlock extends BlockBase {

    private static final String URL = "images/xsquare4.png";

    public OBlock(Controller playArena, int mainCol, int mainRow) {
        super(playArena, mainCol, mainRow);
    }

    @Override
    public void init() {
        matrix[0] = new Square(playArena, mainRow , mainCol);
        matrix[1] = new Square(playArena, mainRow, mainCol + 1);
        matrix[2] = new Square(playArena, mainRow + 1, mainCol);
        matrix[3] = new Square(playArena, mainRow + 1, mainCol + 1);
        for (SquareBase squareBase : matrix) {
            squareBase.getImageView().setImage(new Image(getClass().getClassLoader().getResource(URL).toExternalForm()));
        }
    }

    @Override
    public boolean checkTransformable() {
        return true;
    }

    @Override
    public void form1() {

    }

    @Override
    public void form2() {

    }

    @Override
    public void form3() {

    }

    @Override
    public void form4() {

    }
}
