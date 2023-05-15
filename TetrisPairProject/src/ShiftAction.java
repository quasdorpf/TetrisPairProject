import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class ShiftAction extends AbstractAction {
	private String name;
	public ShiftAction(String name){
		this.name = name;
	}
	public void actionPerformed(ActionEvent e) {
		if (RunTetris.state == RunTetris.gameState.playing) {
			if(name.charAt(0)=='D') {
				RunTetris.grid.fallTetr();
				RunTetris.dropTimer.restart();
			} else {
				RunTetris.grid.shiftTetr(name.charAt(0));
			}
		}
	}

}
