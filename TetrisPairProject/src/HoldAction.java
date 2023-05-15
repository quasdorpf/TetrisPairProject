import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class HoldAction extends AbstractAction {
	public void actionPerformed(ActionEvent e) {
		if (RunTetris.state == RunTetris.gameState.playing) {
			RunTetris.grid.holdTetr();
		}
	}

}