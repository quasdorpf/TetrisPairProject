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
		nextTetr = new ArrayList<Tetromino>();
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[0].length;j++){
				grid[i][j] = new Block();
			}
		}
		visibleGrid = grid.clone();
		dropTetr(Tetromino.getRandomTetromino());
		for (int i=0;i<4;i++)
			nextTetr.add(Tetromino.getRandomTetromino());
	}
	public void dropTetr(Tetromino tetr) {
		currTetr = tetr;
	}
	public void fallTetr() {
		boolean stop = false;
		for(Block block: currTetr.getBlocks()) {
			boolean pause=(block.getY()+1==20);
			if(!pause)
				pause|=!grid[block.getY()+1][block.getX()].isEmpty();
			stop|=pause;
		}
		if (!stop)
			currTetr.shift('D');
		else
			setTetr();
	}
	public void shiftTetr(char dir) {
		boolean stop = false;
		for(Block block: currTetr.getBlocks()) {
			boolean pause = false;
			if (dir==82) {
				pause=(block.getX()+1==10);
				if(!pause)
					pause|=!grid[block.getY()][block.getX()+1].isEmpty();
			} else if (dir==76) {
				pause=(block.getX()-1==-1);
				if(!pause)
					pause|=!grid[block.getY()][block.getX()-1].isEmpty();
			}
			stop|=pause;
		}
		if (!stop) {
			currTetr.shift(dir);}
	}
	public void rotate(int leftorRight) {
		currTetr.rotate(leftorRight);
	}
	public void holdTetr() {
		if (heldTetr == null) {
			dropTetr(nextTetr.get(0));
			nextTetr.remove(0);
			nextTetr.add(Tetromino.getRandomTetromino());
		} else {
			Tetromino tempTetr = currTetr;
			dropTetr(heldTetr);
			heldTetr = tempTetr.getNewTetromino();
		}
	}
	public void setTetr() {
		ArrayList<Integer> clearRows = new ArrayList<Integer>();
		boolean canClear = true;
		for(Block block:currTetr.getBlocks()) {
			grid[block.getY()][block.getX()] = block;
			for (int j=0;j<10;j++) {
				canClear&=!grid[block.getY()][j].isEmpty();
			}
			if(canClear&&!clearRows.contains(block.getY())) {
				clearRows.add(block.getY());
			}
		}
		clearRows(clearRows);
		dropTetr(nextTetr.get(0));
		nextTetr.remove(0);
		nextTetr.add(Tetromino.getRandomTetromino());
		
	}
	public Block[][] makeVisible() {
		Block[][] visibleGrid = new Block[20][10];
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[0].length;j++){
				visibleGrid[i][j] = grid[i][j];
			}
		}
		for(Block block: currTetr.getBlocks()) {
			visibleGrid[block.getY()][block.getX()] = block;
		}
		return visibleGrid;
	}
	private void clearRows(ArrayList<Integer> rows) {
		
	}
	public Block[][] getGrid(){
		return grid.clone();
	}
}
