import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class HardDropAction extends AbstractAction {
	public void actionPerformed(ActionEvent e) {
		if (RunTetris.state == RunTetris.gameState.playing) {
			while (RunTetris.grid.setting)
				RunTetris.grid.fallTetr();
			RunTetris.grid.setting=true;
		}
	}

}
