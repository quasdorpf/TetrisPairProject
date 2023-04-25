import javax.swing.*;
import java.awt.*;
public class GamePanel extends JPanel {
	public static final Tetromino[] tetrs = {
			new ITetromino(), new JTetromino(), new LTetromino(),
			new OTetromino(), new STetromino(), new TTetromino(), 
			new ZTetromino()
	};
	public static final double BLOCK_SIZE_PERC = 0.04;
	public static final double GRID_X_PERC = 0.32;
	public static final double GRID_Y_PERC = 0.05;
	public static final double ROUND_RAD_PERC = 0.04;
	public static final Color BETTER_BLACK = new Color(10, 10, 10); // Very dark grey is better than absolute black
	
	private JFrame screen = RunTetris.screen;
	private WelcomeScreen welcomeScreen = RunTetris.welcomeScreen;
	private Leaderboard leaderboard = RunTetris.leaderboard;
	
	public static int blockSize;
	private Block[][] visGrid;
	
	private JButton retryButton;
	private JButton exitButton;
	
	public GamePanel(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		blockSize = RunTetris.getSizePerc(BLOCK_SIZE_PERC);
		visGrid = RunTetris.grid.makeVisible();
		if (RunTetris.state == RunTetris.gameState.welcomeScreen) {
			drawWelcomeScreenAndLeaderboard(g);
		} else if (RunTetris.state == RunTetris.gameState.playing) {
			drawGridAndBackground(g);
		} else if (RunTetris.state == RunTetris.gameState.gameOver) {
			drawGridAndBackground(g);
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
		drawGridOutlineDecoration(g);
		drawGrid(g, RunTetris.getWidthPerc(GRID_X_PERC), RunTetris.getHeightPerc(GRID_Y_PERC), blockSize);
		
		// Next Tetrominoes
		int x = RunTetris.getWidthPerc(BLOCK_SIZE_PERC+(visGrid[0].length*BLOCK_SIZE_PERC)+0.25);
		int y = RunTetris.getHeightPerc(GRID_Y_PERC);
		int width = RunTetris.getSizePerc(0.2);
		int height = RunTetris.getSizePerc(0.5);
		int arcWidth = RunTetris.getWidthPerc(ROUND_RAD_PERC);
		int arcHeight = RunTetris.getWidthPerc(ROUND_RAD_PERC);
		drawLayeredRoundRect(g, x, y, width, height,
				arcWidth, arcHeight, RunTetris.getSizePerc(specNumInPerc(10)), Color.BLACK, 
				RunTetris.getSizePerc(specNumInPerc(7)), false);
		g.setColor(Color.WHITE);
		g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Serif", Font.PLAIN, RunTetris.getSizePerc(specNumInPerc(20))));
		g.drawString("Next", x+(width/3), y+RunTetris.getHeightPerc(0.04));
		int tetrX = x+(int)((double)width/5);
		for (int i=0; i<3; i++) {
			RunTetris.grid.getNextTetr(i).draw(g, tetrX, y+RunTetris.getSizePerc(0.06+(i*0.12)), blockSize);
		}
		
		// Held Tetromino
		width = RunTetris.getSizePerc(0.2);
		height = RunTetris.getSizePerc(0.3);
		x = RunTetris.getWidthPerc(GRID_X_PERC-0.22);
		y = RunTetris.getHeightPerc(GRID_Y_PERC);
		drawLayeredRoundRect(g, x, y, width, height, arcWidth, arcHeight, RunTetris.getSizePerc(specNumInPerc(10)), Color.BLACK,
				RunTetris.getSizePerc(specNumInPerc(7)), false);
		g.setColor(Color.WHITE);
		g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
		g.setColor(Color.BLACK);
		g.drawString("Hold", x+(int)((double)width/3.5), y+RunTetris.getHeightPerc(0.04));
		Tetromino heldTetr = RunTetris.grid.getHeldTetr();
		if (heldTetr != null) {
			heldTetr.draw(g, x+(int)((double)width/6), y+RunTetris.getHeightPerc(0.06), blockSize);
		}
		
		// Score
		y = RunTetris.getHeightPerc(GRID_Y_PERC+0.5);
		height = RunTetris.getSizePerc(0.2);
		drawLayeredRoundRect(g, x, y, width, height, arcWidth, arcHeight, RunTetris.getSizePerc(specNumInPerc(10)), Color.BLACK,
				RunTetris.getSizePerc(specNumInPerc(7)), false);
		g.setColor(Color.WHITE);
		g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
		g.setColor(Color.BLACK);
		g.drawString("Score", x+(int)((double)width/3.5), y+RunTetris.getHeightPerc(0.04));
		double digits;
		int scoreRem = RunTetris.getScore();
		for (digits=0; scoreRem>0; digits++) {
			scoreRem /= 10;
		}
		System.out.println(digits);
		g.drawString(String.valueOf(RunTetris.getScore()), x+(int)((double)width/2.2)-(RunTetris.getWidthPerc((digits-1)*0.01)), 
				y+RunTetris.getHeightPerc(0.09));
	}
	
	public void drawGameOverScreen(Graphics g) {
		int x = RunTetris.getWidthPerc(GRID_X_PERC+0.0);
		int y = RunTetris.getHeightPerc(GRID_Y_PERC+0.2);
		int width = RunTetris.getSizePerc(BLOCK_SIZE_PERC*10);
		int height = RunTetris.getSizePerc(BLOCK_SIZE_PERC*10);
		int arcWidth = RunTetris.getWidthPerc(ROUND_RAD_PERC);
		int arcHeight = RunTetris.getHeightPerc(ROUND_RAD_PERC);
		drawLayeredRoundRect(g, x, y, width, height,
				arcWidth, arcHeight, RunTetris.getSizePerc(specNumInPerc(10)), Color.BLACK, 
				RunTetris.getSizePerc(specNumInPerc(7)), false);
		g.setColor(Color.WHITE);
		g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Serif", Font.PLAIN, RunTetris.getSizePerc(specNumInPerc(20))));
		g.drawString("Game Over", x+RunTetris.getSizePerc(BLOCK_SIZE_PERC*3), y+RunTetris.getSizePerc(BLOCK_SIZE_PERC*1.5));
		g.drawString("Your score: " + RunTetris.getScore(), x+RunTetris.getSizePerc(BLOCK_SIZE_PERC*1.5), y+RunTetris.getSizePerc(BLOCK_SIZE_PERC*3));
	}
	
	private void drawGrid(Graphics g, int x, int y, int blockSize) {
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
		
		g.setColor(BETTER_BLACK);
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
	
	public static void drawGridOutlineDecoration(Graphics g) {
		int x = RunTetris.getWidthPerc(GRID_X_PERC);
		int y = RunTetris.getHeightPerc(GRID_Y_PERC);
		int width = blockSize*10 + RunTetris.getWidthPerc(0);
		int height = blockSize*20+RunTetris.getHeightPerc(0);
		int arcWidth = RunTetris.getWidthPerc(0.04);
		int arcHeight = RunTetris.getHeightPerc(0.04);
		int layers = RunTetris.getSizePerc(specNumInPerc(25));
		int colInc = RunTetris.getSizePerc(specNumInPerc(-7));
		int rgb = range((-colInc)*(layers-RunTetris.getSizePerc(specNumInPerc(5))), 0, 255);
		Color color = new Color(rgb, rgb, rgb);
		drawLayeredRoundRect(g, x, y, width, height, arcWidth, arcHeight, layers, 
				color, colInc, false);
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
	
	private static double specNumInPerc(int num) {
		return ((double)num/(double)RunTetris.DEFAULT_SIZE);
	}
}