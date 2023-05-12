import javax.swing.*;
import java.awt.*;
public class GamePanel extends JPanel {
	public static final double BLOCK_SIZE_PERC = 0.04;
	public static final double GRID_X_PERC = 0.32;
	public static final double GRID_Y_PERC = 0.05;
	public static final double ROUND_RAD_PERC = 0.04;
	public static final Color BETTER_BLACK = new Color(10, 10, 10); // Very dark grey is better than absolute black
	
	private static int blockSize;
	private Block[][] visGrid;
	
	
	
	public GamePanel(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		blockSize = RunTetris.getSizePerc(BLOCK_SIZE_PERC);
		visGrid = RunTetris.grid.makeVisible();
		g.setColor(new Color(245, 245, 245));
		g.fillRect(0, 0, RunTetris.screen.getWidth(), RunTetris.screen.getHeight());
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
		
		// Welcome screen
		int gridX = RunTetris.getWidthPerc(GRID_X_PERC);
		int gridY = RunTetris.getHeightPerc(GRID_Y_PERC);
		Block[][] visGrid = RunTetris.grid.makeVisible();
		drawGridOutlineDecoration(g);
		drawGridOutline(g, gridX, gridY, 
				visGrid[0].length, visGrid.length, blockSize);
		g.drawImage(new ImageIcon("Imgs/tetris-logo.png").getImage(), gridX+blockSizeMultiply(1.25), 
				gridY+blockSizeMultiply(3.75), blockSizeMultiply(7.5), blockSizeMultiply(6.25), this);
		RunTetris.playButton.setBounds(gridX+blockSizeMultiply(1.75), 
				gridY+blockSizeMultiply(11.25), blockSizeMultiply(6.5), 
				blockSizeMultiply(2));
		RunTetris.playButton.setBackground(new Color(245, 245, 245));
		this.add(RunTetris.playButton);
		
		// Leaderboard
		RunTetris.leaderboard.draw(g, gridX+(blockSize*visGrid[0].length)+RunTetris.getSizePerc(0.1), 
				gridY+(blockSize*4), RunTetris.getSizePerc(0.3), 
				RunTetris.getSizePerc(0.5));
		
	}
	
	private int blockSizeMultiply(double num) {
		return (int)((double)blockSize*num);
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
		
		// Controls
		y = RunTetris.getHeightPerc(GRID_Y_PERC) + height + RunTetris.getSizePerc(0.05);
		width = RunTetris.getSizePerc(0.3);
		height = RunTetris.getSizePerc(0.28);
		drawLayeredRoundRect(g, x, y, width, height, arcWidth, arcHeight, RunTetris.getSizePerc(specNumInPerc(10)), Color.BLACK, 
				RunTetris.getSizePerc(specNumInPerc(7)), false);
		g.setColor(Color.WHITE);
		g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Serif", Font.PLAIN, RunTetris.getSizePerc(specNumInPerc(20))));
		g.drawString("Controls", x+(int)((double)width*0.2), y+(int)((double)height*0.15));
		g.setFont(new Font("Serif", Font.PLAIN, RunTetris.getSizePerc(specNumInPerc(16))));
		g.drawString("Shift Left:       ←", x+(int)((double)width*0.07), y+(int)((double)height*0.3));
		g.drawString("Shift Right:     →", x+(int)((double)width*0.07), y+(int)((double)height*0.42));
		g.drawString("Drop:             ↓", x+(int)((double)width*0.07), y+(int)((double)height*0.54));
		g.drawString("Rotate:           ↑", x+(int)((double)width*0.07), y+(int)((double)height*0.66));
		g.drawString("Hard Drop:    Space", x+(int)((double)width*0.07), y+(int)((double)height*0.78));
		g.drawString("Hold Tetr:      C", x+(int)((double)width*0.07), y+(int)((double)height*0.9));
		
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
		g.setFont(new Font("Serif", Font.PLAIN, RunTetris.getSizePerc(specNumInPerc(20))));
		g.drawString("Hold", x+(int)((double)width/3.5), y+RunTetris.getHeightPerc(0.04));
		Tetromino heldTetr = RunTetris.grid.getHeldTetr();
		if (heldTetr != null) {
			heldTetr.draw(g, x+(int)((double)width/6), y+RunTetris.getHeightPerc(0.07), blockSize);
		}
		
		// Score
		y = RunTetris.getHeightPerc(GRID_Y_PERC) + RunTetris.getSizePerc(0.5);
		height = RunTetris.getSizePerc(0.2);
		drawLayeredRoundRect(g, x, y, width, height, arcWidth, arcHeight, RunTetris.getSizePerc(specNumInPerc(10)), Color.BLACK,
				RunTetris.getSizePerc(specNumInPerc(7)), false);
		g.setColor(Color.WHITE);
		g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
		g.setColor(Color.BLACK);
		g.drawString("Score", x+(int)((double)width/3.5), y+RunTetris.getSizePerc(0.04));
		double digits;
		int scoreRem = RunTetris.getScore();
		for (digits=0; scoreRem>0; digits++) {
			scoreRem /= 10;
		}
		g.drawString(String.valueOf(RunTetris.getScore()), x+(int)((double)width/2.4)-(RunTetris.getWidthPerc((digits-1)*0.005)), 
				y+RunTetris.getSizePerc(0.09));
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
		g.drawString("Game Over", x+(int)((double)blockSize*2.5), y+(int)((double)blockSize*1.5));
		g.drawString("Your score: " + RunTetris.getScore(), x+(int)((double)blockSize*2.5), y+(blockSize*3));
		RunTetris.retryButton.setBounds(x+(int)((double)blockSize*1.5), y+(blockSize*4), blockSize*3, (int)((double)blockSize*1.75));
		RunTetris.retryButton.setBackground(new Color(245, 245, 245));
		this.add(RunTetris.retryButton);
		RunTetris.exitButton.setBounds(x+(int)((double)blockSize*5.5), y+(blockSize*4), blockSize*3, (int)((double)blockSize*1.75));
		RunTetris.exitButton.setBackground(new Color(245, 245, 245));
		this.add(RunTetris.exitButton);
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
	
	public static int range(int num, int min, int max) {
		if (num < min) {
			return min;
		} else if (num > max) {
			return max;
		} else {
			return num;
		}
	}
	
	public static double specNumInPerc(int num) {
		return ((double)num/(double)RunTetris.DEFAULT_SIZE);
	}
}