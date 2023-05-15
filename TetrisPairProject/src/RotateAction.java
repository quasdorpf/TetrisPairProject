import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class RotateAction  extends AbstractAction {
	private int rotation;
	public RotateAction(int rotation){
		this.rotation = rotation;
	}
	public void actionPerformed(ActionEvent e) {
		if (RunTetris.state == RunTetris.gameState.playing) {
			RunTetris.grid.rotate(rotation);
		}
	}

}
