import java.awt.*;
import javax.swing.*;
public class WelcomeScreen {
	private JButton playButton;
	public WelcomeScreen() {
		playButton = new JButton("Play");
	}
	
	public void display(Graphics g, JFrame screen, GamePanel gamePanel) {
		//g.drawImage(new ImageIcon("Imgs/tetris-logo.png").getImage(), RunTetris.getWidthPerc(0.3), RunTetris.getHeightPerc(0.2), 
				//RunTetris.getWidthPerc(0.4), RunTetris.getHeightPerc(0.3), gamePanel);
		GamePanel.drawGridOutlineDecoration(g);
		
		//gamePanel.add(playButton);
		
	}
	
	
}