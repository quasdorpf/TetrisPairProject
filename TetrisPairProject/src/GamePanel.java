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
		//g.draw3DRect(100, 100, 100, 100, );
	}
	
	public void drawGrid(Graphics g) {
		
	}
	
}