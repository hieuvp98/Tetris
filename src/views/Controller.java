package views;

import entities.*;
import entities_abstract.BlockBase;
import entities_abstract.GameColumnBase;
import entities_abstract.GameRowBase;
import entities_abstract.SquareBase;
import java.io.IOException;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import javafx.animation.Animation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Controller implements Initializable {

    protected int loop = DEFAULT_LOOP;

    public static final int TOTAL_ROW = 18;

    public static int DEFAULT_LOOP = 150;
    
    protected ArrayList<Integer> listBlock;
    
    protected int pointer = 0;
    
    AudioClip audioClip;
    
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

    protected int score = 0;

    protected int bonus = -1;

    protected AnimationTimer timer;

    @FXML
    public Pane mainPane;

    protected ArrayList<GameRowBase> rows;

    protected ArrayList<GameColumnBase> columns;

    protected BlockBase currentBlock;

    protected boolean isAlive = true;

    protected boolean runnable = true;

    @FXML
    public Text txtGameStatus;
    @FXML
    private Text btnExitGame;
    @FXML
    private Pane startPane;
    @FXML
    private ImageView imgArrow;
    @FXML
    private Text btnStart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    protected void init() {
        DEFAULT_LOOP = 150;
        listBlock = new ArrayList<>();
        initGrid();
        initRowsCols();
        listBlock.add(new Random().nextInt(7));
        eventHandler();     
        initTimer();
        initBeginPaneSingle();
        audioClip = new AudioClip(this.getClass().getResource("/sounds/theme.mp3").toExternalForm());
        audioClip.setVolume(audioClip.getVolume() * 0.5);
        audioClip.setCycleCount(Animation.INDEFINITE);
        audioClip.play();
    }

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
                        //showAlert();
                        gameOverPane.setVisible(true);
                    }
                } catch (InterruptedException e) {
                }
            }
        };
    }

    protected void playSound(String url) {
        AudioClip audioClipa = new AudioClip(this.getClass().getResource("/sounds/" + url).toExternalForm());
        audioClipa.play();
    }

    protected void initGrid() {
        for (int i = 0; i < TOTAL_ROW; i++) {
            gridPane.addRow(i);
           
        }
        for (int i = 1; i < 10; i++) {
            gridPane.addColumn(i);
        
        }
        gridPane.setGridLinesVisible(false);
        for (int i = 0; i < TOTAL_ROW; i++) {
            for (int j = 0; j < 10; j++) {
                Label node = new Label();
                node.getStyleClass().add("node");
                gridPane.add(node, j, i);
            }
        }
    }


    protected void initRowsCols() {
        columns = new ArrayList<>();
        rows = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            columns.add(new GameColumn(this, i));
        }
        for (int i = 0; i < TOTAL_ROW; i++) {
            rows.add(new GameRow(this, i));
        }
    }

    protected boolean checkDie() {
        for (GameColumnBase column : columns) {
            if (column.checkFull()) {
                System.out.println("game over");
                return true;
            }
        }
        return false;
    }

    protected void checkRows() {

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
    
    private void initBeginPaneSingle() {
        startPane.setVisible(true);
        imgArrow.setImage(new Image(this.getClass().getClassLoader().getResource("images/arrow.png").toExternalForm()));
        btnStart.setOnMouseEntered(e -> {
            btnStart.setFont(new Font("Comic Sans MS", 35));
        });
        btnStart.setOnMouseExited(e -> {
            btnStart.setFont(new Font("Comic Sans MS", 24));
        });
        btnStart.setOnMouseClicked(e -> {
            initBlock();
            timer.start();          
            startPane.setVisible(false);
            
        });
    }

    protected void initBlock() {

        switch (listBlock.get(pointer)) {
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
        pointer++;
        //      currentBlock2 = new IBlock(this, 4, 0);
        currentBlock.addToPanel();
        if (!currentBlock.checkMovable()) {
            isAlive = false;
             playSound("gameover.wav");
        }
        listBlock.add(new Random().nextInt(7));
        switch (listBlock.get(pointer)) {
            case 0: {
                txtNext.setText("Next: T");
                imgNext.setImage(new Image(getClass().getClassLoader().getResource("images/T-Block.png").toExternalForm()));
                break;
            }
            case 1: {
                txtNext.setText("Next: I");
                imgNext.setImage(new Image(getClass().getClassLoader().getResource("images/I-Block.png").toExternalForm()));
                break;
            }
            case 2: {
                txtNext.setText("Next: J");
                imgNext.setImage(new Image(getClass().getClassLoader().getResource("images/J-Block.png").toExternalForm()));
                break;
            }
            case 3: {
                txtNext.setText("Next: L");
                imgNext.setImage(new Image(getClass().getClassLoader().getResource("images/L-Block.png").toExternalForm()));
                break;
            }
            case 4: {
                txtNext.setText("Next: O");
                imgNext.setImage(new Image(getClass().getClassLoader().getResource("images/O-Block.png").toExternalForm()));
                break;
            }
            case 5: {
                txtNext.setText("Next: S");
                imgNext.setImage(new Image(getClass().getClassLoader().getResource("images/S-Block.png").toExternalForm()));
                break;
            }
            case 6: {
                txtNext.setText("Next: Z");
                imgNext.setImage(new Image(getClass().getClassLoader().getResource("images/Z-Block.png").toExternalForm()));
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

    protected void checkLevel() {

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

    protected void gameLoop() throws InterruptedException {
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

    protected void eventHandler() {
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
        btnExitGame.setOnMouseClicked(e -> {
           try {
                timer.stop();
                audioClip.stop();
                Parent parent = FXMLLoader.load(getClass().getResource("Begin.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) mainPane.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException ex) {
                System.out.println("load error: " + ex.getMessage());
            }
        });
    }

    protected void replay() {
        gameOverPane.setVisible(false);
        pointer = 0;
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
