package Controller;

import View.MazeView;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import Model.World;

public class ControllerMaze {
    private World w;
    private String eventCode;


    /*Constructs a ControllerMaze, which wil control the movements of the player and minotaur*/
    public ControllerMaze() {
        w = new World();
        keyPressed();
    }
    /*Sets the actions for the keyboard, so that when the
     *user clicks one of the arrows ( either up,down,left,
     *or right) the player will move to that position and
     *the minotaur will follow to try and kill him
     */
    public void keyPressed(){
        Scene scene = MazeView.getScene();
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                eventCode = event.getCode().toString();
                w.movePlayer(eventCode);
                w.moveMinotaur();
            }
            else if (event.getCode() == KeyCode.LEFT) {
                eventCode = event.getCode().toString();
                w.movePlayer(eventCode);
                w.moveMinotaur();
            }
            else if (event.getCode() == KeyCode.UP) {
                eventCode = event.getCode().toString();

                w.movePlayer(eventCode);
                w.moveMinotaur();
            }
            else if (event.getCode() == KeyCode.DOWN) {
                eventCode = event.getCode().toString();
                w.movePlayer(eventCode);
                w.moveMinotaur();
            }
            w.update();

        });
    }
}