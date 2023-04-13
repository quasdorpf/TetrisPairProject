import java.awt.*;
import javax.swing.*;
public class WelcomeScreen {
	private JButton playButton;
	public WelcomeScreen() {
		playButton = new JButton("Play");
	}
	
	public void display(Graphics g, JFrame screen, GamePanel gamePanel) {
		Block[][] visGrid = RunTetris.grid.makeVisible();
		GamePanel.drawGridOutline(g, RunTetris.getWidthPerc(GamePanel.GRID_PERC_X), RunTetris.getHeightPerc(GamePanel.GRID_PERC_Y), 
				visGrid[0].length, visGrid.length, GamePanel.blockSize);
		GamePanel.drawGridOutlineDecoration(g);
		int imgSize = RunTetris.getSizePerc(0.25);
		g.drawImage(new ImageIcon("Imgs/tetris-logo.png").getImage(), RunTetris.getWidthPerc(0.35), RunTetris.getHeightPerc(0.2), 
				imgSize+RunTetris.getSizePerc(0.0595), imgSize, gamePanel);
		//gamePanel.add(playButton);
		
	}
	
}