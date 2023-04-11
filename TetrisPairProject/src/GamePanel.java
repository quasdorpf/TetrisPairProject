import javax.swing.*;
import java.awt.*;
public class GamePanel extends JPanel {
	public static final Tetromino[] tetrs = {
			new ITetromino(), new JTetromino(), new LTetromino(),
			new OTetromino(), new STetromino(), new TTetromino(), 
			new ZTetromino()
	};
	private static final double BLOCK_PERC_SIZE = 0.04;
	
	private JFrame screen = RunTetris.screen;
	private WelcomeScreen welcomeScreen = RunTetris.welcomeScreen;
	private Leaderboard leaderboard = RunTetris.leaderboard;
	
	private int blockSize;
	private int gridblockSize;
	
	public GamePanel(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		blockSize = Math.min(RunTetris.getWidthPerc(BLOCK_PERC_SIZE), RunTetris.getHeightPerc(BLOCK_PERC_SIZE));
		if (RunTetris.state == RunTetris.gameState.welcomeScreen) {
			drawWelcomeScreenAndLeaderboard(g);
		} else if (RunTetris.state == RunTetris.gameState.playing) {
			drawGridAndBackground(g);
		} else if (RunTetris.state == RunTetris.gameState.gameOver) {
			drawGameOverScreen(g);
		}
	}
	
	public void drawWelcomeScreenAndLeaderboard(Graphics g) {
		/*
		for (int i=0; i<tetrs.length; i++) {
			tetrs[i].draw(g, i*100+50, 50, 20);
		}
		*/
		welcomeScreen.display(g, screen, this);
	}
	
	public void drawGridAndBackground(Graphics g) {
		
	}
	
	public void drawGameOverScreen(Graphics g) {
		
	}
	
	private void drawGrid(Graphics g, int x, int y, int blockSize) {
		Block[][] visGrid = RunTetris.grid.makeVisible();
		drawGridOutline(g, RunTetris.getWidthPerc(0.05), RunTetris.getHeightPerc(0.05), visGrid[0].length, visGrid.length, blockSize);
		for (int i=0; i<visGrid.length; i++) {
			for (int j=0; j<visGrid[0].length; j++) {
				visGrid[i][j].draw(g, RunTetris.getWidthPerc(0.05)+(j*blockSize)+1, RunTetris.getHeightPerc(0.05)+(i*blockSize)+1, blockSize);
			}
		}
	}
	
	private void drawGridOutline(Graphics g, int x, int y, int xCount, int yCount, int blockSize) {
		g.drawRect(x, y, (xCount*blockSize)+2, (yCount*blockSize)+2);
		for (int i=0; i<yCount; i++) {
			for (int j=0; j<xCount; j++) {
				drawGridSquare(g, x+(j*blockSize)+1, y+(i*blockSize)+1, blockSize);
			}
		}
	}
	
	private void drawGridSquare(Graphics g, int x, int y, int size) {
		g.setColor(Color.DARK_GRAY);
		g.drawRect(x, y, size, size);
		g.drawRect(x+1, y+1, size-2, size-2);
		
		g.setColor(new Color(10, 10, 10)); // Very dark grey is better than absolute black
		g.fillRect(x+2, y+2, size-3, size-3);
	}
}