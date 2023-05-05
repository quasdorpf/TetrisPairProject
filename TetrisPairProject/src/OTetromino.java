import java.awt.Color;
import java.awt.Graphics;

public class OTetromino extends Tetromino {
	private static final Color color = Color.YELLOW;
	public OTetromino() {
		this.setPosition(0, 4);
		Block[][] rotation = new Block[4][4];
		for (int k=0; k<4; k++) {
			for (int i=0; i<2; i++) {
				for (int j=0; j<2; j++) {
					rotation[k][2*i+j] = new Block(color,new int[]{i,j});
				}
			}
		}
		setRotation(rotation);
	}
	public Tetromino getNewTetromino() {
		return new OTetromino();
	}
	public void draw(Graphics g, int x, int y, int blockSize) {
		super.draw(g, x+(blockSize/2), y, blockSize);
	}
}