package View;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import Model.Maze;
import Model.Minotaur;
import Model.Player;
import java.util.Optional;

public class MazeView {
    private Stage stage;
    private AnchorPane pane;
    private static Scene scene;
    private Maze maze;
    private Player p;
    private Minotaur mtr;

    private Rectangle[][] rect;
    private int[][] mazeLayout;
    private int finalCounter;
    private int xCounter;
    private int yCounter;


    /*Returns the MazeView scene*/
    public static Scene getScene() {
        return scene;
    }
    /*Constructs MazeView, so that the user can see the maze*/
    public MazeView(Maze maze, Player p, Minotaur m){
        this.maze = maze;
        this.p  = p;
        mtr = m;
        mazeLayout = maze.getMaze();
        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane,690,680);
        rect = new Rectangle[mazeLayout.length][mazeLayout[0].length];
        createSquares();
        addSquaresToPane();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /*Creates squares to form the maze, with the
     *same number of rows and columns as the one
     *chosen by the user. Sets the layout for each of the
     * squares so that it looks like a maze. Updates the maze
     * to include the characters and items (player, minotaur,
     * sword, and exit) and to color the maze with walls and
     * paths in the corresponding positions
     */
    public void createSquares(){
        for(int r=0;r< mazeLayout.length;r++){
            for(int c=0;c< mazeLayout[0].length;c++){
                rect[r][c] = new Rectangle(20, 20, 50, 50);
                setSquarePosition(r, c);
            }
        }
        update();
    }
    /*Sets the layout of each of the squares so that the maze
     *looks like the one chosen by the user
     * */
    public void setSquarePosition(int row, int col){
        if(finalCounter < mazeLayout[0].length) {

            rect[row][col].setLayoutX(xCounter);
            rect[row][col].setLayoutY(yCounter);
            xCounter+=50;
            finalCounter++;
        }
        else if(finalCounter >= mazeLayout[0].length){
            finalCounter = 0;
            xCounter=0;
            yCounter+=50;
            rect[row][col].setLayoutX(xCounter);
            rect[row][col].setLayoutY(yCounter);

            xCounter+=50;
            finalCounter++;
        }
    }

    /*Adds all the squares to the MazeView pane so that they are visible to the user*/
    public void addSquaresToPane() {
        for(int r=0;r< mazeLayout.length;r++){
            for(int c=0;c< mazeLayout[0].length;c++) {
                pane.getChildren().addAll(rect[r][c]);
            }
        }
    }
    /*Updates the map, by looping through the mazeLayout
     *and filling the maze with images and colors, according to the numbers in the maze,
     *in which 0 is a wall, 1 is a path, 2 is the player, 3 is the minotaur,
     *4 is the sword, and 5 is the exit
     */
    public void update(){
        mazeLayout = maze.getMaze();
        for(int r=0;r< mazeLayout.length;r++) {
            for (int c = 0; c < mazeLayout[0].length; c++) {
                if (mazeLayout[r][c] == 0) {
                    rect[r][c].setFill(Color.BLACK);
                } else if (mazeLayout[r][c] == 1) {
                    System.out.println("hi");
                    rect[r][c].setFill(Color.WHITE);
                } else if (mazeLayout[r][c] == 2) {
                    if(!p.hasSword()) {
                        rect[r][c].setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/View/Images/PlayerNoSword.png"))));
                    }
                    else{
                        rect[r][c].setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/View/Images/PlayerWithSword.png"))));
                    }
                } else if (mazeLayout[r][c] == 3) {
                    rect[r][c].setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/View/Images/Minotaur.png"))));
                } else if (mazeLayout[r][c] == 4) {
                    rect[r][c].setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/View/Images/Sword.png"))));
                } else if (mazeLayout[r][c] == 5) {
                    rect[r][c].setFill(Color.RED);
                }
            }
        }
    }
    /*Creates a victory alert to tell the user that he/she has won*/
    public void victoryAlert(){
        Alert alert = new Alert(Alert.AlertType.NONE, "You escaped from the Minotaur");
        alert.getDialogPane().getStylesheets().add("/View/stylesheet.css");
        ButtonType buttonMenu = new ButtonType("Menu");
        ButtonType buttonExit = new ButtonType("Exit");

        alert.getButtonTypes().setAll(buttonMenu , buttonExit);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonMenu ){
            StartView sv = new StartView();
            stage.hide();
        }
        else if(result.get() == buttonExit){
            System.exit(0);
        }

    }
    /*Creates a loss alert to tell the user that he/she has lost*/
    public void lossAlert(){
        Alert alert = new Alert(Alert.AlertType.NONE, "You were killed bt the Minotaur");
        alert.getDialogPane().getStylesheets().add("/View/stylesheet.css");
        ButtonType buttonMenu = new ButtonType("Menu");
        ButtonType buttonExit = new ButtonType("Exit");

        alert.getButtonTypes().setAll(buttonMenu , buttonExit);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonMenu ){
            StartView sv = new StartView();
            stage.hide();
        }
        else if(result.get() == buttonExit){
            System.exit(0);
        }
    }
}