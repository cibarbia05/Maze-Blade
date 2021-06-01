package View;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import Controller.ControllerMaze;
import Model.Maze;
import java.util.Optional;

public class StartView {
    private Stage stage;
    private AnchorPane pane;
    private Scene scene;
    private Maze maze;
    private Button start;
    private Button help;
    private Alert alert;
    private Alert alert2;

    /*Constructs a StartView*/
    public StartView() {
        stage = new Stage();
        pane = new AnchorPane();
        scene = new Scene(pane,880,480);
        maze = new Maze();

        addButton(createButtonStart("START"));
        addButton(createButtonHelp("HELP"));
        createMenuButton("MAZE OPTIONS", "Maze 1", "Maze 2", "Maze 3");
        createBackground(pane,new Image(getClass().getResourceAsStream("/View/Images/MenuImage.png")));
        createLabel("MAZE BLADE");

        scene.getStylesheets().addAll("/View/stylesheet.css");
        stage.setTitle("Maze Blade");

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        createAlertStart();
        createAlertHelp();
        addButtonToAlertStart();
        addButtonAlertHelp();

    }
    /*Creates the start button, which once clicked will lead the user to the MazeView*/
    public Button createButtonStart(String name){
        start = new Button(name);
        start.setPrefSize(100,50);
        start.setLayoutX(70);
        start.setLayoutY(150);

        return start;
    }
    /*Creates a help button, which once will have instructions for the user on how to play*/
    public Button createButtonHelp(String name){
        help = new Button(name);
        help.setPrefSize(100,50);
        help.setLayoutX(70);
        help.setLayoutY(230);

        return help;
    }
    /*Adds the Button passed as a parameter to the pane*/
    public void addButton(Button b){
        pane.getChildren().addAll(b);
    }

    /*Creates a Menu Button for the user to select their maze*/
    public MenuButton createMenuButton(String name1, String name2, String name3, String name4){
        MenuItem mt1 = new MenuItem(name2);
        mt1.setOnAction(event -> maze.i = 0);
        MenuItem mt2 = new MenuItem(name3);
        mt2.setOnAction(event -> maze.i = 1);
        MenuItem mt3 = new MenuItem(name4);
        mt3.setOnAction(event -> maze.i = 2);
        MenuButton mb = new MenuButton(name1,null,mt1,mt2,mt3);
        pane.getChildren().addAll(mb);
        mb.setLayoutX(70);
        mb.setLayoutY(310);
        mb.setPrefSize(185,50);
        return mb;
    }
    /*Creates a background with the image provided on the pane provided*/
    public void createBackground(AnchorPane p, Image f) {
        BackgroundImage backImage = new BackgroundImage(f, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background back = new Background(backImage);
        p.setBackground(back);
    }
    /*Creates a label (title) with the String provided*/
    public void createLabel(String s){
        Label l = new Label(s);
        l.setLayoutX(570);
        l.setLayoutY(170);
        l.setId("label1");
        pane.getChildren().addAll(l);
    }
    /*Creates a start alert that will be called once the user
     *clicks start game, to show the user the goal of the game
     */
    public void createAlertStart(){
        alert = new Alert(Alert.AlertType.NONE,"Hercules has been set on a mission to save the princess from the horrific Minotaur, kill the minotaur and reach the end");
        alert.getDialogPane().getStylesheets().add("/View/stylesheet.css");
        start.setOnAction(event -> alert.showAndWait());
    }
    /*Creates an alert for the user to see how to play*/
    public void createAlertHelp(){
        alert2 = new Alert(Alert.AlertType.NONE, "You are Hercules, your job is to get the sword and kill the minotaur or escape from him \n\n" +
                "To move use the arrow keys\n\nIf the minotaur gets you he kills you \n\nThe red square is the exit");
        alert2.getDialogPane().getStylesheets().add("/View/stylesheet.css");
        help.setOnAction(event -> alert2.showAndWait());
    }
    /*Sets the action for the start button*/
    public void addButtonToAlertStart(){
        start.setOnAction(event -> {
            ButtonType buttonTypePlay = new ButtonType("Play");
            ButtonType buttonTypeCancel = new ButtonType("Cancel");

            alert.getButtonTypes().setAll(buttonTypePlay , buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonTypePlay ){
                    ControllerMaze cm = new ControllerMaze();
                    stage.hide();
            }
        });
    }
    /*Sets the actions for the help button*/
    public void addButtonAlertHelp(){
        help.setOnAction(event -> {
            ButtonType buttonTypeOk = new ButtonType("Ok");
            alert2.getButtonTypes().setAll(buttonTypeOk);
            alert2.showAndWait();
        });
    }
}
