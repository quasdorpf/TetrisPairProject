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
		new ZTetromino().draw(g, 100, 100, 50);
	}
	
	public void drawGrid(Graphics g) {
		
	}
	
}