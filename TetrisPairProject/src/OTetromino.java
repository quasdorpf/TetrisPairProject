import java.awt.Color;

public class OTetromino extends Tetromino {
	private static final Color color = Color.YELLOW;
	public OTetromino() {
		Block[][][] rotations = new Block[4][2][2];
		for (int i=0; i<rotations.length; i++) {
			for (int j=0; j<rotations[0].length; j++) {
				for (int k=0; k<rotations[0][0].length; k++) {
					rotations[i][j][k] = new Block(color);
				}
			}
		}
		setRotations(rotations);
	}
}