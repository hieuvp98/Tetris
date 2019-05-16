/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities2.*;
import entities_abstract2.BlockBase2;
import entities_abstract2.GameColumnBase2;
import entities_abstract2.GameRowBase2;
import entities_abstract2.SquareBase2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static views.Controller.DEFAULT_LOOP;
import static views.Controller.TOTAL_ROW;

/**
 *
 * @author hieuv
 */
public class MultiController extends Controller {

    private ArrayList<GameRowBase2> rows2;

    private ArrayList<GameColumnBase2> columns2;

    private boolean isAlive2 = true;

    private boolean runnable2 = true;

    private int pointer2 = 0;

    private int loop2 = DEFAULT_LOOP;

    private BlockBase2 currentBlock2;

    private AnimationTimer timer2;

    private int bonus2 = -1;

    private int score2 = 0;

    @FXML
    private Text txtCompare;
    @FXML
    private Text txtGameOver;
    @FXML
    private Text txtGameOver2;
    @FXML
    private Text txtScore2;
    @FXML
    private Text txtLevel2;
    @FXML
    private GridPane gridPane2;
    @FXML
    private ImageView imgNext2;
    @FXML
    private Pane beginPane;
    @FXML
    private Pane gameOverPane2;
    @FXML
    private Text btnStart;
    @FXML
    private ImageView imgPlayer1;
    @FXML
    private ImageView imgPlayer2;
    @FXML
    private Text btnExitGame;
    @FXML
    private Text txtNext2;

    @Override
    protected void init() {
        //To change body of generated methods, choose Tools | Templates.
        DEFAULT_LOOP = 50;
        listBlock = new ArrayList<>();
        listBlock.add(new Random().nextInt(7));
        gameOverPane.setVisible(false);
        gameOverPane2.setVisible(false);
        initBeginPane();
        initGrid();
        initGrid2();
        initRowsCols();
        initRowsCols2();
        eventHandler();
        initTimer();
        initTimer2();
        audioClip = new AudioClip(getClass().getClassLoader().getResource("sounds/theme.mp3").toExternalForm());
        audioClip.setCycleCount(Animation.INDEFINITE);
        audioClip.play(audioClip.getVolume() * 0.5);
    }

    private void initBeginPane() {
        beginPane.setVisible(true);
        imgPlayer1.setImage(new Image(this.getClass().getClassLoader().getResource("images/wasd.png").toExternalForm()));
        imgPlayer2.setImage(new Image(this.getClass().getClassLoader().getResource("images/arrow.png").toExternalForm()));
        btnStart.setOnMouseEntered(e -> {
            btnStart.setFont(new Font("Comic Sans MS", 35));
        });
        btnStart.setOnMouseExited(e -> {
            btnStart.setFont(new Font("Comic Sans MS", 24));
        });
        btnStart.setOnMouseClicked(e -> {
            initBlock();
            initBlock2();
            timer.start();
            timer2.start();
            beginPane.setVisible(false);
        });
    }

    @Override
    protected void initTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    if (isAlive) {
                        if (runnable) {
                            gameLoop();
                        }
                    } else {
                        timer.stop();
                        gameOverPane.setVisible(true);
                        if (!isAlive && !isAlive2) {
                            if (score > score2) {
                                txtGameOver.setText("Winner");
                            } else {
                                txtGameOver2.setText("Winner");
                            }
                        }
                    }
                } catch (InterruptedException e) {
                }
            }
        };
    }

    private void initTimer2() {
        timer2 = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    if (isAlive2) {
                        if (runnable2) {
                            gameLoop2();
                        }
                    } else {
                        timer2.stop();
                        gameOverPane2.setVisible(true);
                        if (!isAlive && !isAlive2) {
                            if (score > score2) {
                                txtGameOver.setText("Winner");
                            } else {
                                txtGameOver2.setText("Winner");
                            }
                        }
                    }
                } catch (InterruptedException e) {
                }
            }
        };
    }

    private void gameLoop2() throws InterruptedException {
        Thread.sleep(loop2);
        if (score > score2) {
            txtCompare.setText(">>");
        } else {
            txtCompare.setText("<<");
        }
        checkLevel();
        if (!currentBlock2.checkMovable()) {
            checkRows2();
            initBlock2();
            playSound("fall.wav");
        } else {
            currentBlock2.move();
        }
    }

    @Override
    protected void checkLevel() {
        if (txtLevel.getText().equals("4") && (score >= 24000 || score2 >= 24000)) {
            playSound("success.wav");
            DEFAULT_LOOP = 10;
            txtLevel.setText("5");
            txtLevel2.setText("5");
        } else if (txtLevel2.getText().equals("3") && (score >= 16000 || score2 >= 16000)) {
            playSound("success.wav");
            DEFAULT_LOOP = 20;
            txtLevel.setText("4");
            txtLevel2.setText("4");
        } else if (txtLevel2.getText().equals("2") && (score >= 8000 || score2 >= 8000)) {
            playSound("success.wav");
            DEFAULT_LOOP = 30;
            txtLevel.setText("3");
            txtLevel2.setText("3");
        } else if (txtLevel2.getText().equals("1") && (score >= 4000 || score2 >= 4000)) {
            playSound("success.wav");
            DEFAULT_LOOP = 40;
            txtLevel.setText("2");
            txtLevel2.setText("2");
        }
    }

    private void initBlock2() {
        switch (listBlock.get(pointer2)) {
            case 0: {
                currentBlock2 = new TBlock2(this, 5, 1);
                break;
            }
            case 1: {
                currentBlock2 = new IBlock2(this, 4, 0);
                break;
            }
            case 2: {
                currentBlock2 = new JBlock2(this, 5, 1);
                break;
            }
            case 3: {
                currentBlock2 = new LBlock2(this, 5, 1);
                break;
            }
            case 4: {
                currentBlock2 = new OBlock2(this, 4, 0);
                break;
            }
            case 5: {
                currentBlock2 = new SBlock2(this, 5, 1);
                break;
            }
            case 6: {
                currentBlock2 = new ZBlock2(this, 5, 1);
                break;
            }
            default:
                currentBlock2 = new IBlock2(this, 4, 0);
        }
        pointer2++;

        if (!currentBlock2.checkMovable()) {
            isAlive2 = false;
            playSound("gameover.wav");
        }
        if (pointer2 > listBlock.size() - 1) {
            listBlock.add(new Random().nextInt(7));
        }

        switch (listBlock.get(pointer2)) {
            case 0: {
                txtNext2.setText("Next: T");
                imgNext2.setImage(new Image(getClass().getClassLoader().getResource("images/T-Block.png").toExternalForm()));
                break;
            }
            case 1: {
                txtNext2.setText("Next: I");
                imgNext2.setImage(new Image(getClass().getClassLoader().getResource("images/I-Block.png").toExternalForm()));
                break;
            }
            case 2: {
                txtNext2.setText("Next: J");
                imgNext2.setImage(new Image(getClass().getClassLoader().getResource("images/J-Block.png").toExternalForm()));
                break;
            }
            case 3: {
                txtNext2.setText("Next: L");
                imgNext2.setImage(new Image(getClass().getClassLoader().getResource("images/L-Block.png").toExternalForm()));
                break;
            }
            case 4: {
                txtNext2.setText("Next: O");
                imgNext2.setImage(new Image(getClass().getClassLoader().getResource("images/O-Block.png").toExternalForm()));
                break;
            }
            case 5: {
                txtNext2.setText("Next: S");
                imgNext.setImage(new Image(getClass().getClassLoader().getResource("images/S-Block.png").toExternalForm()));
                break;
            }
            case 6: {
                txtNext2.setText("Next: Z");
                imgNext2.setImage(new Image(getClass().getClassLoader().getResource("images/Z-Block.png").toExternalForm()));
                break;
            }
            default: {
                txtNext2.setText("Next: I");
            }
        }
        currentBlock2.addToPanel();
        loop2 = DEFAULT_LOOP;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    private void checkRows2() {
        Set<Integer> set = new HashSet<>();
        for (SquareBase2 square : currentBlock2.getMatrix()) {
            set.add(square.getIndexRow());
        }
        List<Integer> list = set.stream().collect(Collectors.toList());
        Collections.sort(list);

        list.forEach(index -> {
            GameRowBase2 row = rows2.get(index);
            if (row.checkFull()) {
                loop2 = 1;
                System.out.println("Row " + row.getIndexRow() + " is full");
                row.remove();
                for (int j = 0; j < 10; j++) {
                    GameColumnBase2 col = columns2.get(j);
                    for (int i = row.getIndexRow() - 1; i >= 0; i--) {
                        SquareBase2 squareCol = col.getSquareBases()[i];
                        if (squareCol != null) {
                            squareCol.reLocate(squareCol.getIndexRow() + 1, squareCol.getIndexCol());
                            squareCol.updateUI();
                        }
                    }
                }

                score2 += 1000;
                bonus2++;
            }
        });
        if (bonus2 > -1) {
            playSound("clear.wav");
        }

        if (bonus2 > 0) {
            score2 += bonus2 * 500;
        }
        txtScore2.setText(score2 + "");
        bonus2 = -1;
    }

    @Override
    protected void eventHandler() {

        mainPane.setOnKeyPressed(event -> {

            //player 2
            if (event.getCode() == KeyCode.RIGHT && currentBlock2.checkTurnRight()) {
                currentBlock2.setDirectionX(1);
            } else if (event.getCode() == KeyCode.LEFT && currentBlock2.checkTurnLeft()) {
                currentBlock2.setDirectionX(-1);
            }
            if (event.getCode() == KeyCode.UP && currentBlock2.checkTransformable()) {
                currentBlock2.transform();
            }
            if (event.getCode() == KeyCode.DOWN) {
                loop = 1;
            }
            //player 1
            if (event.getCode() == KeyCode.D && currentBlock.checkTurnRight()) {
                currentBlock.setDirectionX(1);
            } else if (event.getCode() == KeyCode.A && currentBlock.checkTurnLeft()) {
                currentBlock.setDirectionX(-1);
            }
            if (event.getCode() == KeyCode.W && currentBlock.checkTransformable()) {
                currentBlock.transform();
            }
            if (event.getCode() == KeyCode.S) {
                loop2 = 1;
            }
            if (event.getCode() == KeyCode.SPACE) {
                runnable = !runnable;
                runnable2 = !runnable2;
                if (runnable) {
                    txtGameStatus.setText("Game Running");

                } else {
                    txtGameStatus.setText("Game Paused");
                }
            }
        });

        mainPane.setOnKeyReleased(event -> {
            loop = DEFAULT_LOOP;
            loop2 = DEFAULT_LOOP;
        });
        btnExitGame.setOnMouseClicked(e -> {
            timer.stop();
            timer2.stop();
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("Begin.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) mainPane.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException ex) {
                System.out.println("load error: " + ex.getMessage());
            }
        });

    }

    private void initGrid2() {
        for (int i = 0; i < TOTAL_ROW; i++) {
            gridPane2.addRow(i);
            //   gridPane2.addRow(i);
        }
        for (int i = 1; i < 10; i++) {
            gridPane2.addColumn(i);
            //   gridPane2.addColumn(i);
        }
        gridPane2.setGridLinesVisible(false);
        for (int i = 0; i < TOTAL_ROW; i++) {
            for (int j = 0; j < 10; j++) {
                Label node = new Label();
                node.getStyleClass().add("node");
                gridPane2.add(node, j, i);
            }
        }
    }

    private void initRowsCols2() {
        columns2 = new ArrayList<>();
        rows2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            columns2.add(new GameColumn2(this, i));
        }
        for (int i = 0; i < TOTAL_ROW; i++) {
            rows2.add(new GameRow2(this, i));
        }
    }

    private void eventHandler2() {
        mainPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT && currentBlock.checkTurnRight()) {
                currentBlock.setDirectionX(1);
            } else if (event.getCode() == KeyCode.LEFT && currentBlock.checkTurnLeft()) {
                currentBlock.setDirectionX(-1);
            }

            if (event.getCode() == KeyCode.UP && currentBlock.checkTransformable()) {
                currentBlock.transform();
            } else if (event.getCode() == KeyCode.DOWN) {
                loop = 1;
            }
            if (event.getCode() == KeyCode.SPACE) {
                runnable = !runnable;
                if (runnable) {
                    txtGameStatus.setText("Game Running");

                } else {
                    txtGameStatus.setText("Game Paused");
                }
            }
        });
        mainPane.setOnKeyReleased(event -> {
            loop = DEFAULT_LOOP;
        });
        btnExitGame.setOnMouseClicked(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public ArrayList<GameRowBase2> getRows2() {
        return rows2;
    }

    public void setRows2(ArrayList<GameRowBase2> rows2) {
        this.rows2 = rows2;
    }

    public ArrayList<GameColumnBase2> getColumns2() {
        return columns2;
    }

    public void setColumns2(ArrayList<GameColumnBase2> columns2) {
        this.columns2 = columns2;
    }

    public GridPane getGridPane2() {
        return gridPane2;
    }

    public void setGridPane2(GridPane gridPane2) {
        this.gridPane2 = gridPane2;
    }

}
