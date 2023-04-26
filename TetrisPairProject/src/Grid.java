import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.Timer;

public class Grid {
	private Tetromino currTetr;
	private Tetromino heldTetr;
	private ArrayList<Tetromino> nextTetr;
	private Block[][] visibleGrid;
	private Block[][] grid;
	Timer clearTimer;
	Timer dropTimer;
	private int score;
	private boolean holding;
	
	Grid(){
		grid = new Block[20][10];
		holding = true;
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
		if (!testTetrPlacement(tetr, 0, 0)) {
			RunTetris.endGame();
		} else {
			currTetr = tetr;
		}
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
		boolean rotationWorks = false;
		Tetromino tetr = null;
		for (int i=0;i<5;i++) {
			if(!rotationWorks) {
				Tetromino tempTetr = currTetr.testRotate(i, leftorRight);
				boolean canPlace = true;
				for(Block block: tempTetr.getBlocks()) {
					boolean place=(block.getY()<20&&block.getY()>-1&&block.getX()<10&&block.getX()>-1);
					//System.out.println("place: "+place);
					if(place)
						place&=grid[block.getY()][block.getX()].isEmpty();
					canPlace&=place;
					//System.out.println("Can Place: "+canPlace);
				}
				//System.out.println(canPlace);
				if (canPlace) {
					rotationWorks = true;
					tetr = tempTetr;
				}
			}
		}
		if (tetr!=null)
			currTetr = tetr;
	}
	public void holdTetr() {
		if (!holding) {
		if (heldTetr == null) {
			heldTetr = currTetr.getNewTetromino();
			dropTetr(nextTetr.get(0));
			nextTetr.remove(0);
			nextTetr.add(Tetromino.getRandomTetromino());
		} else {
			Tetromino tempTetr = currTetr;
			dropTetr(heldTetr);
			heldTetr = tempTetr.getNewTetromino();
		}
		holding = true;
		}
	}
	public void setTetr() {
		ArrayList<Integer> clearRows = new ArrayList<Integer>();
		for(Block block:currTetr.getBlocks()) {
		boolean canClear = true;
			grid[block.getY()][block.getX()] = block;
			for (int j=0;j<10;j++) {
				canClear&=!grid[block.getY()][j].isEmpty();
				//System.out.println(!grid[block.getY()][j].isEmpty());
			}
			//System.out.println("-----BOTH------");
			//System.out.println(canClear);
			//System.out.println(!clearRows.contains(block.getY()));
			//System.out.println("------ONE------");
			if(canClear&&!clearRows.contains(block.getY())) {
				clearRows.add(block.getY());
			}
		}
		//System.out.println("-----NEXT------");
		if (clearRows.size()>0) {
			clearRows(clearRows);
		}else {
			dropTetr(nextTetr.get(0));
			nextTetr.remove(0);
			nextTetr.add(Tetromino.getRandomTetromino());
		}
		holding = false;
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
	public void addDropTimer(Timer dropTimer) {
		this.dropTimer = dropTimer;
	}
	private ArrayList<Integer> clearRows(ArrayList<Integer> rows) {
		if(dropTimer!=null)
			dropTimer.stop();
		if (rows.size() == 1) {
			RunTetris.incScore(40);
		} else if (rows.size() == 2) {
			RunTetris.incScore(100);
		} else if (rows.size() == 3) {
			RunTetris.incScore(300);
		} else if (rows.size() == 4) {
			RunTetris.incScore(1200);
		}
		currTetr = new EmptyTetromino();
		for (int row:rows)
			for(int j=0;j<grid[0].length;j++){
				grid[row][j] = new Block();
			}
		ActionListener action = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int shift = 0;
				while (rows.size()>0) {
					int bottomRow = rows.get(rows.size()-1);
					for (int i=bottomRow;i>0;i--)
						grid[i+shift] = grid[i+shift-1];
					rows.remove(rows.size()-1);
					shift++;
				}
				clearTimer.stop();
				if(dropTimer!=null)
					dropTimer.start();
				dropTetr(nextTetr.get(0));
				nextTetr.remove(0);
				nextTetr.add(Tetromino.getRandomTetromino());
			}
		};
		clearTimer = new Timer(50,action);
		clearTimer.setRepeats(true);
		clearTimer.start();
		return rows;
	}
	private void fallRows(ArrayList<Integer> rows) {
		while (rows.size()>0) {
			int bottomRow = -1;
			for(int row:rows)
				if (row>bottomRow)
					bottomRow = row;
			for (int i=1;i<bottomRow;i++)
				grid[bottomRow] = grid[bottomRow-1];
		}
	}
	public Block[][] getGrid(){
		return grid.clone();
	}
	public Tetromino getNextTetr(int index) {
		if (index < 0 || index >= nextTetr.size()) {
			return null;
		} else {
			return nextTetr.get(index);
		}
	}
	public Tetromino getHeldTetr() {
		return heldTetr;
	}
	private boolean testTetrPlacement(Tetromino tetr, int x, int y) {
		Block[] rot = tetr.getBlocks();
		for (int i=0; i<rot.length; i++) {
			
			Block gridBlock = grid[y+rot[i].getY()][x+rot[i].getX()];
			if (!gridBlock.isEmpty()) {
				return false;
			}
		}
		return true;
	}
}
