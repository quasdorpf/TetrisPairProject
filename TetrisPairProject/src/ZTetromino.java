import java.awt.Color;

public class ZTetromino extends Tetromino {
	private static final Color color = Color.RED;
	public ZTetromino() {
		Block[][][] rotations = new Block[4][3][3];
		for (int i=0; i<rotations.length; i++) {
			for (int j=0; j<rotations[0].length; j++) {
				for (int k=0; k<rotations[0][0].length; k++) {
					rotations[i][j][k] = new Block(color, true);
				}
			}
		}
		// 0 - UP
		rotations[0][0][0].setEmpty(false);
		rotations[0][0][1].setEmpty(false);
		rotations[0][1][1].setEmpty(false);
		rotations[0][1][2].setEmpty(false);
		
		// 1 - RIGHT
		rotations[1][1][1].setEmpty(false);
		rotations[1][2][1].setEmpty(false);
		rotations[1][0][2].setEmpty(false);
		rotations[1][1][2].setEmpty(false);
		
		// 2 - DOWN
		rotations[2][1][0].setEmpty(false);
		rotations[2][1][1].setEmpty(false);
		rotations[2][2][1].setEmpty(false);
		rotations[2][2][2].setEmpty(false);
		
		// 3 - LEFT
		rotations[3][1][0].setEmpty(false);
		rotations[3][2][0].setEmpty(false);
		rotations[3][0][1].setEmpty(false);
		rotations[3][1][1].setEmpty(false);
		
		setRotations(rotations);
	}
}