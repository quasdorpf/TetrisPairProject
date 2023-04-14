import javax.swing.*;
import java.awt.*;
public class GamePanel extends JPanel {
	public static final Tetromino[] tetrs = {
			new ITetromino(), new JTetromino(), new LTetromino(),
			new OTetromino(), new STetromino(), new TTetromino(), 
			new ZTetromino()
	};
	public static final double BLOCK_PERC_SIZE = 0.04;
	public static final double GRID_PERC_X = 0.32;
	public static final double GRID_PERC_Y = 0.05;
	
	private JFrame screen = RunTetris.screen;
	private WelcomeScreen welcomeScreen = RunTetris.welcomeScreen;
	private Leaderboard leaderboard = RunTetris.leaderboard;
	
	public static int blockSize;
	
	public GamePanel(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		blockSize = RunTetris.getSizePerc(BLOCK_PERC_SIZE);
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
		drawGrid(g, RunTetris.getWidthPerc(GamePanel.GRID_PERC_X), RunTetris.getHeightPerc(GamePanel.GRID_PERC_Y), 
				GamePanel.blockSize);
		drawGridOutlineDecoration(g);
	}
	
	public void drawGameOverScreen(Graphics g) {
		
	}
	
	private void drawGrid(Graphics g, int x, int y, int blockSize) {
		Block[][] visGrid = RunTetris.grid.makeVisible();
		drawGridOutline(g, x, y, visGrid[0].length, visGrid.length, blockSize);
		for (int i=0; i<visGrid.length; i++) {
			for (int j=0; j<visGrid[0].length; j++) {
				visGrid[i][j].draw(g, x+(j*blockSize)+1, y+(i*blockSize)+1, blockSize);
			}
		}
	}
	
	public static void drawGridOutline(Graphics g, int x, int y, int xCount, int yCount, int blockSize) {
		g.setColor(Color.BLACK);
		g.drawRect(x, y, (xCount*blockSize)+2, (yCount*blockSize)+2);
		for (int i=0; i<yCount; i++) {
			for (int j=0; j<xCount; j++) {
				drawGridSquare(g, x+(j*blockSize)+1, y+(i*blockSize)+1, blockSize);
			}
		}
	}
	
	private static void drawGridSquare(Graphics g, int x, int y, int size) {
		g.setColor(Color.DARK_GRAY);
		g.drawRect(x, y, size, size);
		g.drawRect(x+1, y+1, size-2, size-2);
		
		g.setColor(new Color(10, 10, 10)); // Very dark grey is better than absolute black
		g.fillRect(x+2, y+2, size-3, size-3);
	}
	
	public static void drawLayeredRoundRect(Graphics g, int x, int y, int width, int height, int arcWidth, int arcHeight, 
			int layers, Color startColor, int colInc, boolean outToIn) {
		g.setColor(startColor);
		for (int i=0; i<layers; i++) {
			for (int arcI=0; arcI<6; arcI++) {
				int finX, finY, finWidth, finHeight;
				if (outToIn) {
					finX = x+i;
					finY = y+i;
					finWidth = width-(i*2);
					finHeight = height-(i*2);
				} else {
					finX = x-i;
					finY = y-i;
					finWidth = width+(i*2);
					finHeight = height+(i*2);
				}
				g.drawRoundRect(finX, finY, finWidth, finHeight, arcWidth-arcI, arcHeight-arcI);
			}
			Color gCol = g.getColor();
			int newRed = range(gCol.getRed()+colInc, 0, 255);
			int newGreen = range(gCol.getGreen()+colInc, 0, 255);
			int newBlue = range(gCol.getBlue()+colInc, 0, 255);
			g.setColor(new Color(newRed, newGreen, newBlue));
		}
	}
	
	private static int range(int num, int min, int max) {
		if (num < min) {
			return min;
		} else if (num > max) {
			return max;
		} else {
			return num;
		}
	}
	
	public static void drawGridOutlineDecoration(Graphics g) {
		int x = RunTetris.getWidthPerc(GRID_PERC_X);
		int y = RunTetris.getHeightPerc(GRID_PERC_Y);
		int width = blockSize*10 + RunTetris.getWidthPerc(0);
		int height = blockSize*20+RunTetris.getHeightPerc(0);
		int arcWidth = RunTetris.getWidthPerc(0.04);
		int arcHeight = RunTetris.getHeightPerc(0.04);
		int layers = RunTetris.getSizePerc(25.0/(double)RunTetris.DEFAULT_SIZE);
		int colInc = RunTetris.getSizePerc(-7.0/(double)RunTetris.DEFAULT_SIZE);
		int rgb = range((-colInc)*(layers-RunTetris.getSizePerc(5.0/(double)RunTetris.DEFAULT_SIZE)), 0, 255);
		Color color = new Color(rgb, rgb, rgb);
		drawLayeredRoundRect(g, x, y, width, height, arcWidth, arcHeight, layers, 
				color, colInc, false);
	}
}