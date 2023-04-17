import java.awt.Color;
import java.awt.*;

public class ITetromino extends Tetromino {
	private static final Color color = Color.CYAN;
	public ITetromino() {
		Block[][][] rotations = new Block[4][4][4];
		Block[][] rotation = new Block[4][4];
		for (int i=0; i<rotations.length; i++) {
			for (int j=0; j<rotations[0].length; j++) {
				for (int k=0; k<rotations[0][0].length; k++) {
					rotations[i][j][k] = new Block();
				}
			}
		}
		for (int i=0; i<4; i++) {
			// 0 - UP
//			rotations[0][1][i] = new Block(color);
//			
//			// 1 - RIGHT
//			rotations[1][i][2] = new Block(color);
//			
//			// 2 - DOWN
//			rotations[2][2][i] = new Block(color);
//			
//			// 3 - LEFT
//			rotations[3][i][1] = new Block(color);
			
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
	}
	public Tetromino getNewTetromino() {
		return new ITetromino();
	}
	public void draw(Graphics g, int x, int y, int blockSize) {
		super.draw(g, x-(blockSize/2), y, blockSize);
	}
}