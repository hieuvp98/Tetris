package views;

import entities.*;
import entities_abstract.BlockBase;
import entities_abstract.GameColumnBase;
import entities_abstract.GameRowBase;
import entities_abstract.SquareBase;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class Controller implements Initializable {

    private int loop = DEFAULT_LOOP;

    public static final int TOTAL_ROW = 18;

    private static int DEFAULT_LOOP = 150;

    private int nextBlock;
    @FXML
    public GridPane gridPane;
    @FXML
    public ImageView imgNext;
    @FXML
    public Text txtNext;
    @FXML
    public Text txtLevel;
    @FXML
    public Text txtScore;
    @FXML
    public Pane gameOverPane;
    @FXML
    public Button btnReplay;

    private int score = 0;

    private int bonus = -1;

    private AnimationTimer timer;

    @FXML
    public Pane mainPane;
    private Alert alert;

    private ArrayList<GameRowBase> rows;

    private ArrayList<GameRowBase> rows2;

    private ArrayList<GameColumnBase> columns;

    private ArrayList<GameColumnBase> columns2;

    private BlockBase currentBlock;

    private BlockBase currentBlock2;

    private boolean isAlive = true;

    private boolean runnable = true;

    @FXML
    private Text txtGameStatus;
    @FXML
    private GridPane gridPane2;
    @FXML
    private Text txtScore2;
    @FXML
    private Text txtLevel2;
    @FXML
    private ImageView imgNext2;
    @FXML
    private Text txtNext2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    private void init() {
        //  initNewFrame();
        initGrid();
        initRowsCols();
        nextBlock = new Random().nextInt(7);
        initBlock();
        eventHandler();
        initAlert();
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
                        //showAlert();
                        gameOverPane.setVisible(true);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
       // initNewFrame();
    }

    private void initNewFrame() {
        
    }

    private void playSound(String url) {
        AudioClip audioClip = new AudioClip(this.getClass().getResource("/sounds/" + url).toExternalForm());
        audioClip.play();
    }

    private void initGrid() {
        for (int i = 0; i < TOTAL_ROW; i++) {
            gridPane.addRow(i);
            //   gridPane2.addRow(i);
        }
        for (int i = 1; i < 10; i++) {
            gridPane.addColumn(i);
            //   gridPane2.addColumn(i);
        }
        gridPane.setGridLinesVisible(false);
        for (int i = 0; i < TOTAL_ROW; i++) {
            for (int j = 0; j < 10; j++) {
                Label node = new Label();
                node.getStyleClass().add("node");
                gridPane.add(node, j, i);
            }
        }

//        gridPane2.setGridLinesVisible(false);
//        for (int i = 0; i < TOTAL_ROW; i++) {
//            for (int j = 0; j < 10; j++) {
//                Label node = new Label();
//                node.getStyleClass().add("node");
//                gridPane2.add(node, j, i);
//            }
//        }
    }

    private void initAlert() {
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Game over. Do you want replay?");
        alert.setContentText("Replay?");
        gameOverPane.getChildren().add(new Text("Game OVer"));

    }

    private void showAlert() {
        alert.show();
        playSound("gameover.wav");
    }

    private void initRowsCols() {
        columns = new ArrayList<>();
        rows = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            columns.add(new GameColumn(this, i));
        }
        for (int i = 0; i < TOTAL_ROW; i++) {
            rows.add(new GameRow(this, i));
        }

//        columns2 = new ArrayList<>();
//        rows2 = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            columns2.add(new GameColumn(this, i));
//        }
//        for (int i = 0; i < TOTAL_ROW; i++) {
//            rows2.add(new GameRow(this, i));
//        }
    }

    private boolean checkDie() {
        for (GameColumnBase column : columns) {
            if (column.checkFull()) {
                System.out.println("game over");
                return true;
            }
        }
        return false;
    }

    private void checkRows() {

        Set<Integer> set = new HashSet<>();
        for (SquareBase square : currentBlock.getMatrix()) {
            set.add(square.getIndexRow());
        }
        List<Integer> list = set.stream().collect(Collectors.toList());
        Collections.sort(list);

        list.forEach(index -> {
            GameRowBase row = rows.get(index);
            if (row.checkFull()) {
                loop = 1;
                System.out.println("Row " + row.getIndexRow() + " is full");
                row.remove();
                for (int j = 0; j < 10; j++) {
                    GameColumnBase col = columns.get(j);
                    for (int i = row.getIndexRow() - 1; i >= 0; i--) {
                        SquareBase squareCol = col.getSquareBases()[i];
                        if (squareCol != null) {
                            squareCol.reLocate(squareCol.getIndexRow() + 1, squareCol.getIndexCol());
                            squareCol.updateUI();
                        }
                    }
                }

                score += 1000;
                bonus++;
            }
        });
        if (bonus > -1) {
            playSound("clear.wav");
        }

        if (bonus > 0) {
            score += bonus * 500;
        }
        txtScore.setText(score + "");
        bonus = -1;
    }

    private void initBlock() {

        switch (nextBlock) {
            case 0: {
                currentBlock = new TBlock(this, 5, 1);
                break;
            }
            case 1: {
                currentBlock = new IBlock(this, 4, 0);
                break;
            }
            case 2: {
                currentBlock = new JBlock(this, 5, 1);
                break;
            }
            case 3: {
                currentBlock = new LBlock(this, 5, 1);
                break;
            }
            case 4: {
                currentBlock = new OBlock(this, 4, 0);
                break;
            }
            case 5: {
                currentBlock = new SBlock(this, 5, 1);
                break;
            }
            case 6: {
                currentBlock = new ZBlock(this, 5, 1);
                break;
            }
        }
        //      currentBlock2 = new IBlock(this, 4, 0);
        currentBlock.addToPanel();
        if (!currentBlock.checkMovable()) {
            isAlive = false;
        }
        nextBlock = new Random().nextInt(7);
        switch (nextBlock) {
            case 0: {
                txtNext.setText("Next: T");
                break;
            }
            case 1: {
                txtNext.setText("Next: I");
                break;
            }
            case 2: {
                txtNext.setText("Next: J");
                break;
            }
            case 3: {
                txtNext.setText("Next: L");
                break;
            }
            case 4: {
                txtNext.setText("Next: O");
                break;
            }
            case 5: {
                txtNext.setText("Next: S");
                break;
            }
            case 6: {
                txtNext.setText("Next: Z");
                break;
            }
        }
        //   currentBlock2.addToPanel();
        loop = DEFAULT_LOOP;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    private void checkLevel() {

        if (txtLevel.getText().equals("4") && score >= 30000) {
            playSound("success.wav");
            DEFAULT_LOOP = 40;
            txtLevel.setText("4");
        } else if (txtLevel.getText().equals("3") && score >= 20000) {
            playSound("success.wav");
            DEFAULT_LOOP = 60;
            txtLevel.setText("4");
        } else if (txtLevel.getText().equals("2") && score >= 10000) {
            playSound("success.wav");
            DEFAULT_LOOP = 90;
            txtLevel.setText("3");
        } else if (txtLevel.getText().equals("1") && score >= 5000) {
            playSound("success.wav");
            DEFAULT_LOOP = 120;
            txtLevel.setText("2");
        }
    }

    private void gameLoop() throws InterruptedException {
        Thread.sleep(loop);
        checkLevel();
        if (!currentBlock.checkMovable()) {
            checkRows();
            initBlock();
            playSound("fall.wav");
        } else {
            currentBlock.move();
            //  currentBlock2.move();
        }
    }

    private void eventHandler() {
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
        btnReplay.setOnAction(e -> {
            replay();
            gameOverPane.setVisible(false);
        });
    }

    private void replay() {
        gameOverPane.setVisible(false);
        isAlive = true;
        initGrid();
        initRowsCols();
        initBlock();
        DEFAULT_LOOP = 150;
        txtScore.setText("0");
        txtLevel.setText("1");
        timer.start();
        mainPane.requestFocus();
    }

    public ArrayList<GameRowBase> getRows() {
        return rows;
    }

    public void setRows(ArrayList<GameRowBase> rows) {
        this.rows = rows;
    }

    public ArrayList<GameColumnBase> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<GameColumnBase> columns) {
        this.columns = columns;
    }

    public BlockBase getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(BlockBase currentBlock) {
        this.currentBlock = currentBlock;
    }
}
