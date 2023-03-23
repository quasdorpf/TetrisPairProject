
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
				int row = i+currTetr.getY();
				int col = i+currTetr.getX();
				if (!tetromino[i][j].isEmpty())
					grid[row][col]=tetromino[i][j];
			}
			for (int j=0;j<10;j++);
		}
		
	}

}
