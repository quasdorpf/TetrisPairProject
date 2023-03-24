import java.awt.Color;

public class LTetromino extends Tetromino {
	private static final Color color = Color.ORANGE;
	public LTetromino() {
		Block[][][] rotations = new Block[4][3][3];
		for (int i=0; i<rotations.length; i++) {
			for (int j=0; j<rotations[0].length; j++) {
				for (int k=0; k<rotations[0][0].length; k++) {
					rotations[i][j][k] = new Block();
				}
			}
		}
		// 0 - UP
		rotations[0][1][0] = new Block(color);
		rotations[0][1][1] = new Block(color);
		rotations[0][0][2] = new Block(color);
		rotations[0][1][2] = new Block(color);
		
		// 1 - RIGHT
		rotations[1][0][1] = new Block(color);
		rotations[1][1][1] = new Block(color);
		rotations[1][2][1] = new Block(color);
		rotations[1][2][2] = new Block(color);
		
		// 2 - DOWN
		rotations[2][1][0] = new Block(color);
		rotations[2][2][0] = new Block(color);
		rotations[2][1][1] = new Block(color);
		rotations[2][1][2] = new Block(color);
		
		// 3 - LEFT
		rotations[3][0][0] = new Block(color);
		rotations[3][0][1] = new Block(color);
		rotations[3][1][1] = new Block(color);
		rotations[3][2][1] = new Block(color);
		
		setRotations(rotations);
	}
}