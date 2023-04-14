import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class RotateAction  extends AbstractAction {
	private Grid grid;
	private int rotation;
	RotateAction(Grid grid,int rotation){
		this.rotation = rotation;
		this.grid = grid;
	}
	public void actionPerformed(ActionEvent e) {
		grid.rotate(rotation);
		Block[][] visibleGrid = grid.makeVisible();
//		for (Block[] row: visibleGrid) {
//			for (Block block: row)
//				System.out.print(block.isEmpty()+"  ");
//			System.out.println();
//		}
//		System.out.println();
//		System.out.println();
	}

}
