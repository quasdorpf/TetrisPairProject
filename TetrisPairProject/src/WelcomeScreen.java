import java.awt.*;
import javax.swing.*;
public class WelcomeScreen {
	private JButton playButton;
	public WelcomeScreen() {
		playButton = new JButton("Play");
	}
	
	public void display(Graphics g, JFrame screen, GamePanel gamePanel) {
		gamePanel.add(playButton);
	}
	
}