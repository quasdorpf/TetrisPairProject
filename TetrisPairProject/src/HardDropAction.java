import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class HardDropAction extends AbstractAction {
	private Grid grid;
	public HardDropAction(Grid grid){
		this.grid = grid;
	}
	public void actionPerformed(ActionEvent e) {
		if (RunTetris.state == RunTetris.gameState.playing) {
			while (grid.setting)
				grid.fallTetr();
			grid.setting=true;
		}
	}

}
