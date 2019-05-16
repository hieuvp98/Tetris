package entities_abstract2;

import entities_abstract.*;
import views.Controller;

import java.util.Arrays;
import java.util.List;
import views.MultiController;

public abstract class BlockBase2 {

    private int count = 0;

    protected SquareBase2[] matrix;

    protected byte statusForm;

    protected boolean movable;

    protected boolean transformable;

    protected int directionX;

    protected MultiController playArena;

    protected int mainCol;

    protected int mainRow;

    public BlockBase2(MultiController playArena, int mainCol, int mainRow) {
        this.matrix = new SquareBase2[4];
        this.playArena = playArena;
        this.mainCol = mainCol;
        this.mainRow = mainRow;
        this.statusForm = 1;
        this.transformable = true;
        this.movable = true;
        this.directionX = 0;
        init();
    }

    public void addToPanel() {
        for (SquareBase2 squareBase : matrix) {
            squareBase.addToPanel();
        }
    }

    public void move() {
        if (!movable) return;
        mainCol += directionX;
        if (count == 4){
        mainRow++;
        count = 0;
        }
        count++;
        for (SquareBase2 squareBase : matrix) {
            squareBase.setDirectionX(this.directionX);
            squareBase.move();
        }
        updateUI();
        directionX = 0;
    }

    private void updateUI() {
        for (SquareBase2 squareBase : matrix) {
            squareBase.updateUI();
        }
    }

    public void transform() {
        if (!transformable || !movable) return;
        this.statusForm++;
        if (statusForm > 4) statusForm = 1;
        switch (statusForm) {
            case 1: {
                form1();
                break;
            }
            case 2: {
                form2();
                break;
            }
            case 3: {
                form3();
                break;
            }
            case 4: {
                form4();
                break;
            }
            default:
                break;
        }
        updateUI();
    }

    public abstract void init();

    public boolean checkMovable() {
        List<SquareBase2> test = Arrays.asList(matrix);
        for (SquareBase2 square : matrix) {
            if (square.indexRow == Controller.TOTAL_ROW - 1) {
                this.movable = false;              
                return false;
            } else if (!test.contains(playArena.getColumns2().get(square.getIndexCol()).squareBases[square.indexRow + 1]))
                if (!square.checkMoveDown()) {
                    this.movable = false;
                    return false;
                }
        }
        return true;
    }

    public abstract boolean checkTransformable();

    public boolean checkTurnLeft() {
        List<SquareBase2> test = Arrays.asList(matrix);
        for (SquareBase2 square : matrix) {
            if (square.indexCol == 0) {
                return false;
            } else if (!test.contains(playArena.getRows2().get(square.getIndexRow()).squareBases[square.indexCol - 1]))
                if (!square.checkMoveLeft()) {
                    return false;
                }
        }
        return true;
    }

    public boolean checkTurnRight() {
        List<SquareBase2> test = Arrays.asList(matrix);
        for (SquareBase2 square : matrix) {
            if (square.indexCol == 9) {
                return false;
            } else if (!test.contains(playArena.getRows2().get(square.getIndexRow()).squareBases[square.indexCol + 1]))
                if (!square.checkMoveRight()) {
                    return false;
                }
        }
        return true;
    }

    public abstract void form1();

    public abstract void form2();

    public abstract void form3();

    public abstract void form4();

    public byte getStatusForm() {
        return statusForm;
    }

    public void setStatusForm(byte statusForm) {
        this.statusForm = statusForm;
    }

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public boolean isTransformable() {
        return transformable;
    }

    public void setTransformable(boolean transformable) {
        this.transformable = transformable;
    }

    public int getDirectionX() {
        return directionX;
    }

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    public Controller getPlayArena() {
        return playArena;
    }

    public void setPlayArena(MultiController playArena) {
        this.playArena = playArena;
    }

    public SquareBase2[] getMatrix() {
        return matrix;
    }

    public void setMatrix(SquareBase2[] matrix) {
        this.matrix = matrix;
    }

    public int getMainCol() {
        return mainCol;
    }

    public void setMainCol(int mainCol) {
        this.mainCol = mainCol;
    }

    public int getMainRow() {
        return mainRow;
    }

    public void setMainRow(int mainRow) {
        this.mainRow = mainRow;
    }
}
