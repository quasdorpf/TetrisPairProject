
public class EmptyTetromino extends Tetromino {

	EmptyTetromino(){
		super();
		Block[][] rotation = new Block[4][4];
		for (int k=0; k<4; k++) {
			for (int i=0; i<2; i++) {
				for (int j=0; j<2; j++) {
					rotation[k][2*i+j] = new Block();
				}
			}
		}
		setRotation(rotation);
	}
	public Tetromino getNewTetromino() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isEmpty() {
		return true;
	}

}
