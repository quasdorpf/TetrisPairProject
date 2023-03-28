import javax.swing.*;
import java.awt.*;
public class GamePanel extends JPanel {
	
	public GamePanel(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (RunTetris.state == RunTetris.gameState.welcomeScreen) {
			drawWelcomeScreenAndLeaderboard(g);
		} else if (RunTetris.state == RunTetris.gameState.playing) {
			drawGrid(g);
		}
	}
	
	public void drawWelcomeScreenAndLeaderboard(Graphics g) {
		new Block(new Color(255, 165, 0)).draw(g, 100, 100, 100);
		new Block(Color.ORANGE).draw(g, 300, 100, 100);
	}
	
	public void drawGrid(Graphics g) {
		
	}
	
}