import java.util.*;

public class Grid {
	private Tetromino currTetr;
	private Tetromino heldTetr;
	private ArrayList<Tetromino> nextTetr;
	private Block[][] visibleGrid;
	private Block[][] grid;
	private int score;
	
	Grid(){
		grid = new Block[20][10];
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[0].length;j++){
				grid[i][j] = new Block();
			}
		}
		visibleGrid = grid.clone();
	}

	public void dropTetr(Tetromino tetr) {
		currTetr = tetr;
	}
	public void fallTetr(Tetromino tetr) {
		tetr.shift('D');
	}
	public void holdTetr() {
		if (heldTetr == null) {
			dropTetr(nextTetr.get(0));
			nextTetr.remove(0);
			nextTetr.add(Tetromino.getRandomTetromino());
		} else {
			Tetromino tempTetr = currTetr;
			dropTetr(heldTetr);
			tempTetr.setPosition(0, 0);
			heldTetr = tempTetr;
		}
	}
	public void setTetr() {
		Block[][] tetromino = currTetr.getRotation();
		ArrayList<Integer> clearRows = new ArrayList<Integer>();
		for(int i=0;i<tetromino.length;i++){
			for(int j=0;j<tetromino[0].length;j++){
				int row = i+currTetr.getY();
				int col = i+currTetr.getX();
				if (!tetromino[i][j].isEmpty())
					grid[row][col]=tetromino[i][j];
			}
			boolean canClear = true;
			for (int j=0;j<10;j++) {
				canClear&=!tetromino[i][j].isEmpty();
			}
			if(canClear) {
				clearRows.add(i);
			}
			clearRows(clearRows);
			dropTetr(nextTetr.get(0));
			nextTetr.remove(0);
			nextTetr.add(Tetromino.getRandomTetromino());
		}
		
	}
	private void clearRows(ArrayList<Integer> rows) {
		
	}
	public Block[][] getGrid(){
		return grid.clone();
	}
}
