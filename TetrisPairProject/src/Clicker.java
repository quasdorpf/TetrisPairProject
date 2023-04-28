import java.awt.event.*;

public class Clicker implements KeyListener, MouseListener, ActionListener {
	Grid grid;
	Block[][] visibleGrid;
	Clicker(Grid g){
		grid = g;
		visibleGrid = g.makeVisible();
		for (Block[] row: visibleGrid) {
			for (Block block: row)
				System.out.print(block.isEmpty()+"  ");
			System.out.println();
		}
	}
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void keyPressed(KeyEvent e) {
		
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void mouseClicked(MouseEvent e) {
		System.out.println("jj");
//		if (e.getKeyChar()==KeyEvent.VK_SPACE)
			grid.fallTetr();
		visibleGrid = grid.makeVisible();
//		for (Block[] row: visibleGrid) {
//			for (Block block: row)
//				System.out.print(block.isEmpty()+"  ");
//			System.out.println();
//		}
//		System.out.println();
//		System.out.println();
		
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