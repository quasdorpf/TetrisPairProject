import java.util.*;
import java.awt.*;

public abstract class Tetromino {
	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	private Block[][][] rotations;
	private Block[][] rotation;
	private int[] position;
	private int currRotation = 0; // would be used in rotations[currRotation]
	Tetromino(){
		position = new int[]{0,0};
		rotation = new Block[4][4];
	}
	public abstract Tetromino getNewTetromino();
	public void setRotations(Block[][][] rot) {
		rotations = rot;
	}
	public void setRotation(Block[][] rot) {
		rotation = rot;
	}
	public void rotate(int leftOrRight) {
		if (leftOrRight == -1 || leftOrRight == 1) {
			currRotation += leftOrRight;
			if (currRotation > 3) {
				currRotation -= 4;
			}
			if (currRotation < 0) {
				currRotation += 4;
			}
		}
	}
	public static Tetromino getRandomTetromino() {
		int rand = new Random().nextInt(7);
		switch (rand) {
		case 0:return new OTetromino();
		case 1:return new ITetromino();
		case 2:return new ZTetromino();
		case 3:return new JTetromino();
		case 4:return new TTetromino();
		case 5:return new STetromino();
		case 6:return new LTetromino();
		default:return new ITetromino();
		}
	}
	public void setPosition(int row,int col) {
		position = new int[] {row, col};
	}
	public Block[][] getRotation() {
		return rotations[currRotation];
	}
	public Tetromino testRotate(int testCase, int dir) {
		Tetromino test =  getNewTetromino();
		test.setBlockPosition(getY()-test.getY(), getX()-test.getX());
		for (int i=0;i<currRotation+dir;i++)
			test.rotate(1);
		if (currRotation==0&&dir==1) {
			if (testCase==0)
				test.setBlockPosition(0, 0);
				else if (testCase==1)test.setBlockPosition(1, 0);
				else if (testCase==2)test.setBlockPosition(1, -1);
				else if (testCase==3)test.setBlockPosition(0, 2);
				else if (testCase==4)test.setBlockPosition(1, 2);
		}else if(currRotation==1&&dir==-1) {
			if (testCase==0)
				test.setBlockPosition(0, 0);
				else if (testCase==1)test.setBlockPosition(-1, 0);
				else if (testCase==2)test.setBlockPosition(-1, 1);
				else if (testCase==3)test.setBlockPosition(0, -2);
				else if (testCase==4)test.setBlockPosition(-1, -2);
		}else if(currRotation==1&&dir==1) {
			if (testCase==0)
				test.setBlockPosition(0, 0);
				else if (testCase==1)test.setBlockPosition(-1, 0);
				else if (testCase==2)test.setBlockPosition(-1, 1);
				else if (testCase==3)test.setBlockPosition(0, -2);
				else if (testCase==4)test.setBlockPosition(-1, -2);
		}else if(currRotation==2&&dir==-1) {
			if (testCase==0)
				test.setBlockPosition(0, 0);
				else if (testCase==1)test.setBlockPosition(1, 0);
				else if (testCase==2)test.setBlockPosition(1, -1);
				else if (testCase==3)test.setBlockPosition(0, 2);
				else if (testCase==4)test.setBlockPosition(1, 2);
		}else if(currRotation==2&&dir==1) {
			if (testCase==0)
				test.setBlockPosition(0, 0);
				else if (testCase==1)test.setBlockPosition(-1, 0);
				else if (testCase==2)test.setBlockPosition(-1, -1);
				else if (testCase==3)test.setBlockPosition(0, 2);
				else if (testCase==4)test.setBlockPosition(-1, 2);
		}else if(currRotation==3&&dir==-1) {
			if (testCase==0)
				test.setBlockPosition(0, 0);
				else if (testCase==1)test.setBlockPosition(1, 0);
				else if (testCase==2)test.setBlockPosition(1, 1);
				else if (testCase==3)test.setBlockPosition(0, -2);
				else if (testCase==4)test.setBlockPosition(1, -2);
		}else if(currRotation==3&&dir==1) {
			if (testCase==0)
				test.setBlockPosition(0, 0);
				else if (testCase==1)test.setBlockPosition(1, 0);
				else if (testCase==2)test.setBlockPosition(1, -1);
				else if (testCase==3)test.setBlockPosition(0, -2);
				else if (testCase==4)test.setBlockPosition(1, -2);
		}else if(currRotation==0&&dir==-1) {
			if (testCase==0)
				test.setBlockPosition(0, 0);
				else if (testCase==1)test.setBlockPosition(-1, 0);
				else if (testCase==2)test.setBlockPosition(-1, -1);
				else if (testCase==3)test.setBlockPosition(0, 2);
				else if (testCase==4)test.setBlockPosition(-1, 2);
		}
		return test;
	}
	public Block[] getBlocks() {
		return rotation[currRotation];
	}
	public void shift(char dir) {
		switch (dir) {
		case 'D':setBlockPosition(1,0);
			break;
		case 'R':setBlockPosition(0,1);
			break;
		case 'L':setBlockPosition(0,-1);
			break;
		default:;
		}
		
	}
	public int getRotationNum() {
		return currRotation;
	}
	public void setBlockPosition(int row, int col) {
		position[0] += row;
		position[1] += col;
		for(Block[] rot:rotation) {
			for (Block block:rot) {
//				System.out.println(col);
				block.shiftCoords(row, col);
			}
		}
	}
	public int getX() {
		return position[1];
	}
	public int getY() {
		return position[0];
	}
	public int size() {
		return rotations[0].length;
	}
	public void draw(Graphics g, int x, int y, int blockSize) {
		Block[] rot = rotation[currRotation];
		int blockX;
		int blockY;
		for (int i=0; i<rotation.length; i++) {
			Block b = rot[i];
			blockX = x + ((b.getX()-position[1]) * blockSize);
			blockY = y + ((b.getY()-position[0]) * blockSize);
			b.draw(g, blockX, blockY, blockSize);
		}
	}
}
