import java.awt.Color;
import java.awt.Graphics;

public class OTetromino extends Tetromino {
	private static final Color color = Color.YELLOW;
	public OTetromino() {
		Block[][][] rotations = new Block[4][2][2];
		for (int i=0; i<rotations.length; i++) {
			for (int j=0; j<rotations[0].length; j++) {
				for (int k=0; k<rotations[0][0].length; k++) {
					rotations[i][j][k] = new Block(color,new int[]{i,j});
				}
			}
		}
		Block[][] rotation = new Block[4][4];
		for (int k=0; k<4; k++) {
			for (int i=0; i<2; i++) {
				for (int j=0; j<2; j++) {
					rotation[k][2*i+j] = new Block(color,new int[]{i,j});
				}
			}
		}
		setRotations(rotations);
		setRotation(rotation);
	}
	public Tetromino getNewTetromino() {
		return new OTetromino();
	}
	public void draw(Graphics g, int x, int y, int blockSize) {
		super.draw(g, x+(blockSize/2), y, blockSize);
	}
}