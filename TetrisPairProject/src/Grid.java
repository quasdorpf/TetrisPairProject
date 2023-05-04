import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.Timer;

public class Grid {
	private Tetromino currTetr;
	private Tetromino heldTetr;
	private ArrayList<Tetromino> nextTetr;
	private Block[][] grid;
	Timerx clearTimer;
	Timer dropTimer;
	public boolean setting;
	private boolean holding;
	Grid(){
		initializeGrid();
	}
	public void initializeGrid() {
		grid = new Block[20][10];
		setting = true;
		holding = false;
		nextTetr = new ArrayList<Tetromino>();
		heldTetr = new EmptyTetromino();
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[0].length;j++){
				grid[i][j] = new Block();
			}
		}
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
		else {
			setting=false;
			setTetr();
		}
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
					if(place)
						place&=grid[block.getY()][block.getX()].isEmpty();
					canPlace&=place;
				}
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
		if (heldTetr.isEmpty()) {
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
			}
			if(canClear&&!clearRows.contains(block.getY())) {
				clearRows.add(block.getY());
				while(clearRows.indexOf(block.getY())>0&&
						block.getY()<clearRows.get(clearRows.indexOf(block.getY())-1)) {
				clearRows.add(clearRows.remove(clearRows.indexOf(block.getY())-1));
				}
			}
		}
		if (clearRows.size()>0) {
			clearRows(clearRows);
		}else if (!testTetrPlacement(nextTetr.get(0), 0, 0)) {
			RunTetris.endGameTrigger = true;
		} else {
			dropTetr(nextTetr.remove(0));
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
			RunTetris.incScore(200);
		} else if (rows.size() == 2) {
			RunTetris.incScore(600);
		} else if (rows.size() == 3) {
			RunTetris.incScore(1000);
		} else if (rows.size() == 4) {
			RunTetris.incScore(1600);
		}
		currTetr = new EmptyTetromino();
		TimerTask task = new TimerTask() {
			int fromCenter = 0;
			public void run() {
				if(fromCenter<5){
					for (int row:rows) {
						grid[row][4-fromCenter] = new Block();
						grid[row][4+fromCenter+1] = new Block();
					}
					fromCenter++;
				}else{
					TimerTask task = new TimerTask(){
						int shift = 0;
						public void run() {
							if (rows.size()>0) {
								int bottomRow = rows.get(rows.size()-1);
								for (int i=bottomRow;i>0;i--) {
									grid[i+shift] = grid[i+shift-1];
								grid[shift]= new Block[10];
								for (int j=0;j<10;j++)
									grid[shift][j]= new Block();
								}
								rows.remove(rows.size()-1);
								shift++;
							}else {
							dropTetr(nextTetr.get(0));
							nextTetr.remove(0);
							nextTetr.add(Tetromino.getRandomTetromino());
							clearTimer.cancel();
							}
							if(dropTimer!=null)
								dropTimer.start();
						}
					};
					clearTimer.cancel();
					clearTimer = new Timerx();
					clearTimer.scheduleAtFixedRate(task,10,50);
				}
			}
		};
		clearTimer = new Timerx();
		clearTimer.scheduleAtFixedRate(task,10,50);
		return rows;
	}
	
	public Block[][] getGrid(){
		return grid.clone();
	}
	public boolean isFalling() {
		return setting;
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
