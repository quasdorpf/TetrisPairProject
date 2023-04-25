import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class WelcomeScreen {
	private boolean reSized = false;
	private boolean signal = false;
	public WelcomeScreen() {
		RunTetris.playButton = new JButton("Play");
		RunTetris.playButton.setActionCommand("Play");
		RunTetris.playButton.addActionListener(RunTetris.clicker);
	}
	
	public void display(Graphics g, JFrame screen, GamePanel gamePanel) {
		Block[][] visGrid = RunTetris.grid.makeVisible();
		GamePanel.drawGridOutlineDecoration(g);
		GamePanel.drawGridOutline(g, RunTetris.getWidthPerc(GamePanel.GRID_X_PERC), RunTetris.getHeightPerc(GamePanel.GRID_Y_PERC), 
				visGrid[0].length, visGrid.length, GamePanel.blockSize);
		int imgSize = RunTetris.getSizePerc(0.25);
		g.drawImage(new ImageIcon("Imgs/tetris-logo.png").getImage(), RunTetris.getWidthPerc(0.35), RunTetris.getHeightPerc(0.2), 
				imgSize+RunTetris.getSizePerc(0.0595), imgSize, gamePanel);
		RunTetris.playButton.setBounds(RunTetris.getWidthPerc(0.35), RunTetris.getHeightPerc(0.5), RunTetris.getSizePerc(0.25), 
				RunTetris.getSizePerc(0.08));
		gamePanel.add(RunTetris.playButton);
		if (!reSized) {
			RunTetris.screen.setSize(RunTetris.DEFAULT_WIDTH, RunTetris.DEFAULT_HEIGHT);
			reSized = true;
		}
	}
	
	public boolean checkSignal() {
		return signal;
	}
	
	public void setSignal(boolean b) {
		signal = b;
	}
	
}