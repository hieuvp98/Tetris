package entities_abstract2;

import entities_abstract.*;
import javafx.scene.image.ImageView;
import views.Controller;
import views.MultiController;

public abstract class SquareBase2 {

    public static final int WIDTH = 40;

    public static final int HEIGHT = 40;

    public static final String URL = "images/xsquare1.png";

    protected int directionX;

    protected boolean removable;

    protected int count = 0;

    protected ImageView imageView;

    protected MultiController playArena;

    protected int preIndexRow;

    protected int preIndexCol;

    protected int indexRow;

    protected int indexCol;

    public SquareBase2(MultiController playArena, int indexRow, int indexCol) {
//        Image image = new Image(getClass().getClassLoader().getResource(URL).toExternalForm());
        this.imageView = new ImageView();
        this.imageView.setFitWidth(WIDTH);
        this.imageView.setFitHeight(HEIGHT);
        this.removable = false;
        directionX = 0;
        this.playArena = playArena;
        this.indexCol = indexCol;
        this.indexRow = indexRow;
    }

    public abstract void addToPanel();

    public abstract void changeCol();

    public abstract void move();

    public abstract void updateUI();

    public abstract boolean checkMoveDown();

    public abstract boolean checkMoveLeft();

    public abstract boolean checkMoveRight();

    public abstract void reLocate(int row, int col);

    public int getDirectionX() {
        return directionX;
    }

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    public boolean isRemovable() {
        return removable;
    }

    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getIndexRow() {
        return indexRow;
    }

    public void setIndexRow(int indexRow) {
        this.indexRow = indexRow;
    }

    public int getIndexCol() {
        return indexCol;
    }

    public void setIndexCol(int indexCol) {
        this.indexCol = indexCol;
    }

    public int getPreIndexRow() {
        return preIndexRow;
    }

    public void setPreIndexRow(int preIndexRow) {
        this.preIndexRow = preIndexRow;
    }

    public int getPreIndexCol() {
        return preIndexCol;
    }

    public void setPreIndexCol(int preIndexCol) {
        this.preIndexCol = preIndexCol;
    }
}
