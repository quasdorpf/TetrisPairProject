import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class ShiftAction extends AbstractAction {
	private Grid grid;
	private String name;
	ShiftAction(String name,Grid grid){
		this.name = name;
		this.grid = grid;
	}
	public void actionPerformed(ActionEvent e) {
		if (RunTetris.state == RunTetris.gameState.playing) {
			if(name.charAt(0)=='D') {
				grid.fallTetr();
				RunTetris.dropTimer.restart();
			} else {
				grid.shiftTetr(name.charAt(0));
			}
		}
	}

}
