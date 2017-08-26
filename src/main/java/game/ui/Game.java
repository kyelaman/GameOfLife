package game.ui; 

import game.GameRules;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {

    private Button[][] gamegrid; 
    public boolean cell[][];  

    @Override
    public void start(Stage stage) throws Exception {
        int size = getSize();
        cell = new boolean[size][size];
        gamegrid = new Button[size][size];
        gameOfLifeStart(size);
    }

    public static int getSize(){

        GridPane gridpane = new GridPane();
        final Label label = new Label();
        GridPane.setConstraints(label, 0, 0);
        gridpane.getChildren().add(label);
        label.setText("Enter the size");

        final TextField sizeTextField = new TextField();
        GridPane.setConstraints(sizeTextField, 1, 1);
        gridpane.getChildren().add(sizeTextField);

        Button start = new Button("Start");
        GridPane.setConstraints(start, 7, 2);
        gridpane.getChildren().add(start);

        Scene scene = new Scene(gridpane,400, 100);
        final Stage stage = new Stage();
        stage.setScene(scene);

        start.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (sizeTextField.getText() != null) {
                    stage.close();
                }
            }
        });
        stage.showAndWait();
        return Integer.parseInt(sizeTextField.getText());
    }

    private void startGame(GridPane mainGrid,int size)
    {
        Button startGame = new Button();
        startGame.setText("Start game");
        mainGrid.add(startGame, size + 1, size + 1);
        startGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                startGamePlay(cell);
            }
        });
    }

    private void stopGame(GridPane mainGrid,int size)
    {
        Button endGame = new Button();
        endGame.setText("End game");
        mainGrid.add(endGame, size + 1, size + 2);

        Scene scene = new Scene(mainGrid);
        final Stage game = new Stage();
        game.setScene(scene);
        endGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game.close();
            }
        });
        game.show();
    }
    
    public void gameOfLifeStart(int size)
    {
        GridPane mainGrid = new GridPane();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gamegrid[i][j] = new Button();
                gamegrid[i][j].setId(i + " " + j);
                gamegrid[i][j].setStyle(" -fx-base: #b6e7c9; -fx-background-color:#26fff0");
                mainGrid.add(gamegrid[i][j], j, i);
                gamegrid[i][j].setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent e) {
                        Button clickedbutton = (Button) e.getSource();
                        String[] id = clickedbutton.getId().split("\\s");
                        int x = Integer.parseInt(id[0]);
                        int y = Integer.parseInt(id[1]);
                        cell[x][y] = !cell[x][y];
                        if (cell[x][y])
                            clickedbutton.setStyle("-fx-base: #b6e7c9; -fx-background-color:#2b3fff");
                        else
                            clickedbutton.setStyle("-fx-base: #b6e7c9; -fx-background-color:#ff2327");
                    }
                });

            }
        }

        startGame(mainGrid, size);
        stopGame(mainGrid, size);
        drawGrid();

    }

    public void startGamePlay(boolean[][] cell){
        final Timeline beat = new Timeline(new KeyFrame(Duration.ZERO,new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                update();
            }
        }) , new KeyFrame(Duration.millis(1000)));
		beat.setCycleCount(Timeline.INDEFINITE);
        beat.play();
    }

    public void drawGrid() {
        for(int i= 0; i < cell.length; i++)
            for (int j = 0; j < cell.length; j++) {
                if (cell[i][j])
                    gamegrid[i][j].setStyle("-fx-base: #b6e7c9; -fx-background-color:#2b3fff");
                else
					gamegrid[i][j].setStyle("-fx-base: #b6e7c9; -fx-background-color:#ff2327");

            }
    }

    public void update() {
        cell = GameRules.generateNext(cell);
        drawGrid();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}