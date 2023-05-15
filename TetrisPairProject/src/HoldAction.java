import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class HoldAction extends AbstractAction {
	private Grid grid;
	public HoldAction(Grid grid){
		this.grid = grid;
	}
	public void actionPerformed(ActionEvent e) {
		if (RunTetris.state == RunTetris.gameState.playing) {
			grid.holdTetr();
		}
	}

}