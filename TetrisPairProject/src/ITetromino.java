import java.awt.Color;
import java.awt.*;

public class ITetromino extends Tetromino {
	private static final Color color = Color.CYAN;
	public ITetromino() {
		Block[][] rotation = new Block[4][4];
		for (int i=0; i<4; i++) {
			
			// 0 - UP
			rotation[0][i] = new Block(color,new int[] {1,i});
			
			// 1 - RIGHT
			rotation[1][i] = new Block(color,new int[] {i,2});
			
			// 2 - DOWN
			rotation[2][i] = new Block(color,new int[] {2,i});
			
			// 3 - LEFT
			rotation[3][i] = new Block(color,new int[] {i,1});
		}
		setRotation(rotation);
		setBlockPosition(0, 3);
	}
	public Tetromino getNewTetromino() {
		return new ITetromino();
	}
	public Tetromino testRotate(int testCase, int dir) {
		Tetromino test =  getNewTetromino();
		int currRotation = getRotationNum();
		test.setBlockPosition(getY()-test.getY(), getX()-test.getX());
		for (int i=0;i<currRotation+dir;i++)
			test.rotate(1);
		if (currRotation==0&&dir==1) {
			if (testCase==0)
				test.setBlockPosition(0,0);
				else if (testCase==1)test.setBlockPosition(0,-2);
				else if (testCase==2)test.setBlockPosition(0,1);
				else if (testCase==3)test.setBlockPosition(1,-2);
				else if (testCase==4)test.setBlockPosition(-2,1);
		}else if(currRotation==1&&dir==-1) {
			if (testCase==0)
				test.setBlockPosition(0,0);
				else if (testCase==1)test.setBlockPosition(0,2);
				else if (testCase==2)test.setBlockPosition(0,-1);
				else if (testCase==3)test.setBlockPosition(-1,2);
				else if (testCase==4)test.setBlockPosition(2,-1);
		}else if(currRotation==1&&dir==1) {
			if (testCase==0)
				test.setBlockPosition(0,0);
				else if (testCase==1)test.setBlockPosition(0,-1);
				else if (testCase==2)test.setBlockPosition(0,2);
				else if (testCase==3)test.setBlockPosition(-2,-1);
				else if (testCase==4)test.setBlockPosition(1,2);
		}else if(currRotation==2&&dir==-1) {
			if (testCase==0)
				test.setBlockPosition(0,0);
				else if (testCase==1)test.setBlockPosition(0,1);
				else if (testCase==2)test.setBlockPosition(0,-2);
				else if (testCase==3)test.setBlockPosition(2,1);
				else if (testCase==4)test.setBlockPosition(-1,-2);
		}else if(currRotation==2&&dir==1) {
			if (testCase==0)
				test.setBlockPosition(0,0);
				else if (testCase==1)test.setBlockPosition(0,2);
				else if (testCase==2)test.setBlockPosition(0,-1);
				else if (testCase==3)test.setBlockPosition(-1,2);
				else if (testCase==4)test.setBlockPosition(2,-1);
		}else if(currRotation==3&&dir==-1) {
			if (testCase==0)
				test.setBlockPosition(0,0);
				else if (testCase==1)test.setBlockPosition(0,-2);
				else if (testCase==2)test.setBlockPosition(0,1);
				else if (testCase==3)test.setBlockPosition(1,-2);
				else if (testCase==4)test.setBlockPosition(-2,1);
		}else if(currRotation==3&&dir==1) {
			if (testCase==0)
				test.setBlockPosition(0,0);
				else if (testCase==1)test.setBlockPosition(0,1);
				else if (testCase==2)test.setBlockPosition(0,-2);
				else if (testCase==3)test.setBlockPosition(2,1);
				else if (testCase==4)test.setBlockPosition(-1,-2);
		}else if(currRotation==0&&dir==-1) {
			if (testCase==0)
				test.setBlockPosition(0,0);
				else if (testCase==1)test.setBlockPosition(0,-1);
				else if (testCase==2)test.setBlockPosition(0,2);
				else if (testCase==3)test.setBlockPosition(-2,-1);
				else if (testCase==4)test.setBlockPosition(1,2);
		}
		return test;
	}
	public void draw(Graphics g, int x, int y, int blockSize) {
		super.draw(g, x-(blockSize/2), y, blockSize);
	}
}