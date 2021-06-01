package Model;

import View.MazeView;

public class World {
    private Player p;
    private Minotaur t ;
    private Maze m;
    private MazeView mv;


    /*Constructs a world with the maze, player, and minotaur*/
    public World() {
        m = new Maze();
        p = new Player(1, 0);
        t = new Minotaur(6, 8);
        mv = new MazeView(m,p,t);
    }


    /*On call, updates the maze by changing it to the original one,
     *with only black and white rectangles, and then putting the minotaur,
     *sword, and player in place according to their current positions
     */
    public void update() {
        for(int r=0;r< m.getMaze().length;r++){
            for(int c=0;c< m.getMaze()[0].length;c++){
                if(m.getMaze()[r][c]==2){
                    m.getMaze()[r][c] = 1;
                }
                else if(m.getMaze()[r][c]==3){
                    m.getMaze()[r][c] = 1;
                }
                else if(m.getMaze()[r][c]==4 && p.hasSword()){
                    m.getMaze()[r][c] = 1;
                }
            }
        }
        if(!p.wins()) {
            m.getMaze()[p.getRow()][p.getCol()] = 2;
        }
        if(t.isAlive()) {
            m.getMaze()[t.getRow()][t.getCol()] = 3;
        }
        mv.update();

        if(p.wins()){
            mv.victoryAlert();
        }
        else if(!p.isAlive()){
            mv.lossAlert();
        }


    }
    /*Moves the player either up, down, left or right,
     *depending on which arrow key was pressed
     */
    public void movePlayer(String moveTo) {
        int r = p.getRow();
        int c = p.getCol();

        int[][] maze = m.getMaze();
        int mRows = maze.length;
        int mCols = maze[0].length;

        // Moving Up
        if (moveTo.equalsIgnoreCase("UP")) {
            if ((r - 1) >= 0 && maze[r - 1][c] == 1) {
                p.setPos(r - 1, c);
            }
            else if((r - 1) >= 0 && maze[r - 1][c] == 3 && !p.hasSword()) {
                p.kill();
            }
            else if((r - 1) >= 0 && maze[r - 1][c] == 3 && p.hasSword()) {
                p.setPos(r - 1, c);
                t.kill();
            }
            else if((r - 1) >= 0 && maze[r - 1][c] == 4) {
                p.setPos(r - 1, c);
                p.giveSword();
            }
            else if((r - 1) >= 0 && maze[r - 1][c] ==  5) {
                p.giveWin();

            }
        }
        // Moving Down
        if (moveTo.equalsIgnoreCase("DOWN")) {
            if ((r + 1) < mRows && maze[r + 1][c]== 1) {
                p.setPos(r + 1, c);
            }
            else if((r + 1) < mRows && maze[r + 1][c] == 3 && !p.hasSword()) {
                p.kill();
            }
            else if((r + 1) < mRows && maze[r + 1][c] == 3 && p.hasSword()) {
                p.setPos(r + 1, c);
                t.kill();
            }
            else if((r + 1) < mRows && maze[r + 1][c]== 4) {
                p.setPos(r + 1, c);
                p.giveSword();
            }
            else if((r + 1) < mRows && maze[r + 1][c]==  5) {
                p.giveWin();

            }
        }
        // Moving Right
        if (moveTo.equalsIgnoreCase("RIGHT")) {
            if ((c + 1) < mCols && maze[r][c + 1]== 1) {
                p.setPos(r, c + 1);
            }
            else if((c + 1) < mCols && maze[r][c + 1] == 3 && !p.hasSword()) {
                p.kill();
            }
            else if((c + 1) < mCols && maze[r][c + 1] == 3 && p.hasSword()) {
                p.setPos(r, c + 1);
                t.kill();
            }
            else if((c + 1) < mCols && maze[r][c + 1]== 4) {
                p.setPos(r, c + 1);
                p.giveSword();
            }
            else if((c + 1) < mCols && maze[r][c + 1]== 5) {
                p.giveWin();

            }
        }
        // Moving Left
        if (moveTo.equalsIgnoreCase("LEFT")) {
            if ((c - 1) >= 0 && m.getMaze()[r][c - 1]== 1) {
                p.setPos(r, c - 1);
            }
            else if((c - 1) >= 0 && m.getMaze()[r][c - 1]== 3 && !p.hasSword()) {
                p.kill();
            }
            else if((c - 1) >= 0 && m.getMaze()[r][c - 1]== 3 && p.hasSword()) {
                p.setPos(r, c - 1);
                t.kill();
            }
            else if((c - 1) >= 0 && m.getMaze()[r][c - 1]== 4) {
                p.setPos(r, c - 1);
                p.giveSword();
            }
            else if((c - 1) >= 0 && m.getMaze()[r][c - 1]== 5) {
                p.giveWin();

            }
        }

    }

    /*Moves the minotaur to the direction closest to the player if available*/
    public void moveMinotaur() {
        int rDist = p.getRow() - t.getRow();
        int cDist = p.getCol() - t.getCol();

        int r = t.getRow();
        int c = t.getCol();

        int[][] maze = m.getMaze();

        // Minotaur moving Up
        if(rDist < 0 && maze[r - 1][c]== 1) {
            t.setPos(r - 1, c);
            minotaurDies();
        }

        // Minotaur moving Down
        else if(rDist > 0 && maze[r +1][c]== 1) {
            t.setPos(r + 1, c);
            minotaurDies();
        }
        // Minotaur moving Right
        else if(cDist > 0 && maze[r][c + 1]== 1) {
            t.setPos(r, c + 1);
            minotaurDies();
        }
        // Minotaur moving Left
        else if(cDist < 0 && maze[r][c - 1]== 1) {
            t.setPos(r, c - 1);
            minotaurDies();
        }
    }
    /*Checks if the position of the minotaur matches that of the player,
     *and the player has a sword, and if so the minotaur dies
     * */
    private void minotaurDies(){
        if(t.getCol()== p.getCol() && t.getRow() == p.getRow() &&p.hasSword()){
            t.kill();
        }
    }
}