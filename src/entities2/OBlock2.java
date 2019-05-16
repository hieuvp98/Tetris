package entities2;

import entities.*;
import entities_abstract.BlockBase;
import entities_abstract.SquareBase;
import entities_abstract2.BlockBase2;
import entities_abstract2.SquareBase2;
import javafx.scene.image.Image;
import views.Controller;
import views.MultiController;

public class OBlock2 extends BlockBase2 {

    private static final String URL = "images/xsquare4.png";

    public OBlock2(MultiController playArena, int mainCol, int mainRow) {
        super(playArena, mainCol, mainRow);
    }

    @Override
    public void init() {
        matrix[0] = new Square2(playArena, mainRow , mainCol);
        matrix[1] = new Square2(playArena, mainRow, mainCol + 1);
        matrix[2] = new Square2(playArena, mainRow + 1, mainCol);
        matrix[3] = new Square2(playArena, mainRow + 1, mainCol + 1);
        for (SquareBase2 squareBase : matrix) {
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
