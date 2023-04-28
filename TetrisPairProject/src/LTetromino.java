import java.awt.Color;

public class LTetromino extends Tetromino {
	private static final Color color = new Color(255, 165, 0); // Orange, a better shade than Color.ORANGE;
	public LTetromino() {
		Block[][] rotation = new Block[4][4];
		// 0 - UP
		rotation[0][0] = new Block(color,new int[] {0,2});
		rotation[0][1] = new Block(color,new int[] {1,0});
		rotation[0][2] = new Block(color,new int[] {1,1});
		rotation[0][3] = new Block(color,new int[] {1,2});
		
		// 1 - RIGHT
		rotation[1][0] = new Block(color,new int[] {0,1});
		rotation[1][1] = new Block(color,new int[] {1,1});
		rotation[1][2] = new Block(color,new int[] {2,1});
		rotation[1][3] = new Block(color,new int[] {2,2});
		
		// 2 - DOWN
		rotation[2][0] = new Block(color,new int[] {1,0});
		rotation[2][1] = new Block(color,new int[] {1,1});
		rotation[2][2] = new Block(color,new int[] {1,2});
		rotation[2][3] = new Block(color,new int[] {2,0});
		
		// 3 - LEFT
		rotation[3][0] = new Block(color,new int[] {0,0});
		rotation[3][1] = new Block(color,new int[] {0,1});
		rotation[3][2] = new Block(color,new int[] {1,1});
		rotation[3][3] = new Block(color,new int[] {2,1});
		setRotation(rotation);
	}
	public Tetromino getNewTetromino() {
		return new LTetromino();
	}
}