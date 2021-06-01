package Model;

public class Player {
    private int row, col;
    private boolean isAlive, wins,sword;

    /*Constructs a player with a starting position*/
    public Player(int r, int c) {
        row = r;
        col = c;
        isAlive = true;
    }
    /*Returns the row of the position of the player*/
    public int getRow() { return row; }

    /*Returns the column of the position of the player*/
    public int getCol() { return col; }

    /*Sets the position of the player*/
    public void setPos(int r, int c) { row = r; col = c; }

    /*Checks if the player is alive or dead*/
    public boolean isAlive() { return isAlive; }

    /*Kills the player*/
    public void kill() { isAlive = false; }

    /*Checks if the player has the sword*/
    public boolean hasSword(){
        return sword;
    }

    /*Gives the player the sword*/
    public void giveSword(){
        sword = true;
    }

    /*Gives the player the win*/
    public void giveWin(){
        wins = true;
    }

    /*Checks if the player has won*/
    public boolean wins(){
        return wins;
    }

}