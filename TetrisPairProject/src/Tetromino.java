import java.util.*;

public abstract class Tetromino {
	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	private Block[][][] rotations;
	private int[] position;
	private int currRotation = 0; // would be used in rotations[currRotation]
	Tetromino(){
		position = new int[]{0,0};
	}
	public void setRotations(Block[][][] rot) {
		rotations = rot;
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
	public void shift(char dir) {
		switch (dir) {
		case 'D':position[0]++ ;
		case 'R':position[1]++;
		case 'L':position[1]--;
		default:;
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
}
