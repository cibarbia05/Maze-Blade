package Model;

public class Minotaur {
    private int row, col;
    private boolean isAlive;

    /*Construct minotaur with given starting position*/
    public Minotaur(int r, int c) {
        row = r;
        col = c;
        isAlive = true;
    }
    /*Check if minotaur is alive*/
    public boolean isAlive() { return isAlive; }

    /*Returns the row of the position of the minotaur*/
    public int getRow() { return row; }

    /*Returns the column of the position of the minotaur*/
    public int getCol() { return col; }

    /*Checks if the minotaur is alive or dead*/
    public void setPos(int r, int c) { row = r; col = c; }

    /*Kills the minotaur*/
    public void kill(){
        isAlive = false;
    }

}
