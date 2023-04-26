import java.awt.Color;

public class TTetromino extends Tetromino {
	private static final Color color = Color.MAGENTA;
	public TTetromino() {
		Block[][][] rotations = new Block[4][3][3];
		for (int i=0; i<rotations.length; i++) {
			for (int j=0; j<rotations[0].length; j++) {
				for (int k=0; k<rotations[0][0].length; k++) {
					rotations[i][j][k] = new Block();
				}
			}
		}
		Block[][] rotation = new Block[4][4];
//		// 0 - UP
//		rotations[0][1][0] = new Block(color);
//		rotations[0][0][1] = new Block(color);
//		rotations[0][1][1] = new Block(color);
//		rotations[0][1][2] = new Block(color);
//		
//		// 1 - RIGHT
//		rotations[1][0][1] = new Block(color);
//		rotations[1][1][1] = new Block(color);
//		rotations[1][2][1] = new Block(color);
//		rotations[1][1][2] = new Block(color);
//		
//		// 2 - DOWN
//		rotations[2][1][0] = new Block(color);
//		rotations[2][1][1] = new Block(color);
//		rotations[2][2][1] = new Block(color);
//		rotations[2][1][2] = new Block(color);
//		
//		// 3 - LEFT
//		rotations[3][1][0] = new Block(color);
//		rotations[3][0][1] = new Block(color);
//		rotations[3][1][1] = new Block(color);
//		rotations[3][2][1] = new Block(color);

		// 0 - UP
		rotation[0][0] = new Block(color,new int[] {1,0});
		rotation[0][1] = new Block(color,new int[] {0,1});
		rotation[0][2] = new Block(color,new int[] {1,1});
		rotation[0][3] = new Block(color,new int[] {1,2});
		
		// 1 - RIGHT
		rotation[1][0] = new Block(color,new int[] {0,1});
		rotation[1][1] = new Block(color,new int[] {1,1});
		rotation[1][2] = new Block(color,new int[] {2,1});
		rotation[1][3] = new Block(color,new int[] {1,2});
		
		// 2 - DOWN
		rotation[2][0] = new Block(color,new int[] {1,0});
		rotation[2][1] = new Block(color,new int[] {1,1});
		rotation[2][2] = new Block(color,new int[] {2,1});
		rotation[2][3] = new Block(color,new int[] {1,2});
		
		// 3 - LEFT
		rotation[3][0] = new Block(color,new int[] {1,0});
		rotation[3][1] = new Block(color,new int[] {0,1});
		rotation[3][2] = new Block(color,new int[] {1,1});
		rotation[3][3] = new Block(color,new int[] {2,1});
		setRotation(rotation);
	}
	public Tetromino getNewTetromino() {
		return new TTetromino();
	}
}