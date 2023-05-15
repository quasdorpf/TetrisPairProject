import java.awt.event.*;

public class Clicker implements KeyListener, MouseListener, ActionListener {
	Grid grid;
	Block[][] visibleGrid;
	Clicker(Grid g){
		grid = g;
		visibleGrid = g.makeVisible();
	}
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void keyPressed(KeyEvent e) {
		
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void mouseClicked(MouseEvent e) {
		grid.fallTetr();
		visibleGrid = grid.makeVisible();
		
		RunTetris.dropTimer.restart();
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals("Play")) {
			RunTetris.playTrigger = true;
			RunTetris.gamePanel.remove(RunTetris.playButton);
		} else if (actionCommand.equals("Retry")) {
			RunTetris.retryTrigger = true;
			RunTetris.gamePanel.remove(RunTetris.retryButton);
			RunTetris.gamePanel.remove(RunTetris.exitButton);
		} else if (actionCommand.equals("Exit")) {
			RunTetris.exitTrigger = true;
			RunTetris.gamePanel.remove(RunTetris.retryButton);
			RunTetris.gamePanel.remove(RunTetris.exitButton);
		}
	}
	
}