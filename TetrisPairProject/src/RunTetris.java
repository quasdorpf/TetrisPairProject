import javax.swing.*;
import java.awt.event.*;

public class RunTetris {
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	private static final int TIME_BETWEEN_ANIMATIONS = 10;
	private static final int TIME_BETWEEN_DROPS = 1000;
	
	public static JFrame screen;
	private static GamePanel gamePanel;
	private static Clicker clicker;
	public static WelcomeScreen welcomeScreen;
	public static Leaderboard leaderboard;
	public static Grid grid;
	public static gameState state;
	
	private int currScore;
	
	public enum gameState {
		welcomeScreen,
		playing,
		gameOver
	}
	public RunTetris() {
		screen = new JFrame("Tetris");
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setResizable(true);
		
		welcomeScreen = new WelcomeScreen();
		leaderboard = new Leaderboard();
		grid = new Grid();
		
		gamePanel = new GamePanel(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		clicker = new Clicker(grid);
		gamePanel.addMouseListener(clicker);
		addAction("DOWN");
		addAction("RIGHT");
		addAction("LEFT");
		
	}
	
	public void playTetris() {
		state = gameState.welcomeScreen;
		currScore = 0;
		
		screen.setContentPane(gamePanel);
		screen.pack();
		screen.setVisible(true);
		refreshTimer.start();
		
		// temp
		dropTimer.start();
	}
	
	ActionListener refresher = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (state == gameState.welcomeScreen) {
				
			} else if (state == gameState.playing) {
				
			} else if (state == gameState.gameOver) {
				
			}
			gamePanel.repaint();
		}
	};
	Timer refreshTimer = new Timer(TIME_BETWEEN_ANIMATIONS, refresher);
	
	private static ActionListener dropper = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			grid.fallTetr();
		}
	};
	public static Timer dropTimer = new Timer(TIME_BETWEEN_DROPS, dropper);
	
	public static int getWidthPerc(double perc) {
		return (int)((double)screen.getWidth() * perc);
	}
	
	public static int getHeightPerc(double perc) {
		return (int)((double)screen.getHeight() * perc);
	}
	
	public static void addAction(String name){
		Action newAction = new ShiftAction(name, grid);
		KeyStroke key = KeyStroke.getKeyStroke(name);
		gamePanel.getInputMap().put(key, name);
		gamePanel.getActionMap().put(name, newAction);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				new RunTetris().playTetris();
			}
		});
	}
}