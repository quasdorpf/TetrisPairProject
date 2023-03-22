
public abstract class Tetromino {
	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	private Block[][][] rotations;;
	private int currRotation = 0; // would be used in rotations[currRotation]
	public void rotate(int leftOrRight) {
		if (leftOrRight == -1 || leftOrRight == 1) {
			currRotation += leftOrRight;
			if (currRotation > 3) {
				currRotation -= 4;
			}
			if (currRotation < 0) {
				currRotation += 4;
			}
		}
	}
	public Block[][] getRotation() {
		return rotations[currRotation];
	}
	public static void main(String[] args) {
		
	}
}
