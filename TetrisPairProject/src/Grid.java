
public class Grid {
	private Tetromino currTetr;
	private Tetromino heldTetr;
	private Tetromino[] nextTetr;
	private Block[][] visibleGrid;
	private Block[][] grid;
	private int score;

	public void dropTetr(Tetromino tetr) {
		currTetr = tetr;
	}
	public void setTetr() {
		Block[][] tetromino = currTetr.getRotation();
		for(int i=0;i<tetromino.length;i++){
			for(int j=0;j<tetromino[0].length;j++){
				grid[i+currTetr.getY()][i+currTetr.getX()]=tetromino[i][j];
			}
		}
				
		
	}

}
