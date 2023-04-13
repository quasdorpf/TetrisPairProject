import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class WelcomeScreen {
	private JButton playButton;
	private boolean reSized = false;
	private boolean signal = false;
	public WelcomeScreen() {
		playButton = new JButton("Play");
		playButton.setActionCommand("Play");
		playButton.addActionListener(new PlayClicker());
	}
	
	public void display(Graphics g, JFrame screen, GamePanel gamePanel) {
		Block[][] visGrid = RunTetris.grid.makeVisible();
		GamePanel.drawGridOutline(g, RunTetris.getWidthPerc(GamePanel.GRID_PERC_X), RunTetris.getHeightPerc(GamePanel.GRID_PERC_Y), 
				visGrid[0].length, visGrid.length, GamePanel.blockSize);
		GamePanel.drawGridOutlineDecoration(g);
		int imgSize = RunTetris.getSizePerc(0.25);
		g.drawImage(new ImageIcon("Imgs/tetris-logo.png").getImage(), RunTetris.getWidthPerc(0.35), RunTetris.getHeightPerc(0.2), 
				imgSize+RunTetris.getSizePerc(0.0595), imgSize, gamePanel);
		playButton.move(RunTetris.getWidthPerc(0.35), RunTetris.getHeightPerc(0.5));
		playButton.resize(RunTetris.getSizePerc(0.25), RunTetris.getSizePerc(0.08));
		gamePanel.add(playButton);
		if (!reSized) {
			RunTetris.screen.resize(RunTetris.DEFAULT_WIDTH, RunTetris.DEFAULT_HEIGHT);
			reSized = true;
		}
	}
	
	public boolean checkSignal() {
		return signal;
	}
	
	public void resetSignal() {
		signal = false;
	}
	
	private class PlayClicker implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			if (actionCommand.equals("Play")) {
				signal = true;
				RunTetris.gamePanel.remove(playButton);
			}
		}
		
	}
	
}