import java.awt.*;
import javax.swing.*;
public class WelcomeScreen {
	private JButton playButton;
	public WelcomeScreen() {
		playButton = new JButton("Play");
	}
	
	public void display(Graphics g, JFrame screen, GamePanel gamePanel) {
		g.drawImage(new ImageIcon("Imgs/tetris-logo.png").getImage(), RunTetris.getWidthPerc(0.3), RunTetris.getHeightPerc(0.2), 
				RunTetris.getWidthPerc(0.4), RunTetris.getHeightPerc(0.3), gamePanel);
		drawLayeredRoundRect(g, RunTetris.getWidthPerc(0.1), RunTetris.getHeightPerc(0.1), RunTetris.getWidthPerc(0.6), 
					RunTetris.getHeightPerc(0.6), RunTetris.getWidthPerc(0.05), RunTetris.getHeightPerc(0.05), 10);
		
		gamePanel.add(playButton);
		
	}
	
	private void drawLayeredRoundRect(Graphics g, int x, int y, int width, int height, int arcWidth, int arcHeight, int layers) {
		g.setColor(Color.BLACK);
		for (int i=0; i<layers; i++) {
			for (int arcI=0; arcI<6; arcI++) {
				g.drawRoundRect(x+i, y+i, width-(i*2), height-(i*2), arcWidth-arcI, arcHeight-arcI);
			}
			Color gCol = g.getColor();
			int colInc = 10;
			int newRed = gCol.getRed()+colInc;
			int newGreen = gCol.getGreen()+colInc;
			int newBlue = gCol.getBlue()+colInc;
			if (newRed > 255) {
				newRed = 255;
			}
			if (newGreen > 255) {
				newGreen = 255;
			}
			if (newBlue > 255) {
				newBlue = 255;
			}
			g.setColor(new Color(newRed, newGreen, newBlue));
		}
	}
	
}