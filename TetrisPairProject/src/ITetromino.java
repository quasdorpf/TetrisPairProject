import java.awt.Color;

public class ITetromino extends Tetromino {
	private static final Color color = Color.CYAN;
	public ITetromino() {
		Block[][][] rotations = new Block[4][4][4];
		for (int i=0; i<rotations.length; i++) {
			for (int j=0; j<rotations[0].length; j++) {
				for (int k=0; k<rotations[0][0].length; k++) {
					rotations[i][j][k] = new Block(color, true);
				}
			}
		}
		for (int i=0; i<4; i++) {
			// 0 - UP
			rotations[0][i][1].setEmpty(false);
			
			// 1 - RIGHT
			rotations[1][2][i].setEmpty(false);
			
			// 2 - DOWN
			rotations[2][i][2].setEmpty(false);
			
			// 3 - LEFT
			rotations[3][1][i].setEmpty(false);
		}
		
		setRotations(rotations);
	}
}