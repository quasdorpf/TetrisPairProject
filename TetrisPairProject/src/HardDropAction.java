import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class HardDropAction extends AbstractAction {
	private Grid grid;
	HardDropAction(Grid grid){
		this.grid = grid;
	}
	public void actionPerformed(ActionEvent e) {
		Tetromino tetr = grid.getNextTetr(0);
		while (tetr==grid.getNextTetr(0))
			grid.fallTetr();
	}

}
