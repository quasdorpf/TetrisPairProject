import javax.swing.*;
import java.awt.event.*;

public class RunTetris {
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 600;
	public static final int DEFAULT_SIZE = Math.min(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	private static final int TIME_BETWEEN_ANIMATIONS = 10;
	private static final int TIME_BETWEEN_DROPS = 1000;
	
	public static JFrame screen;
	public static GamePanel gamePanel;
	public static Clicker clicker;
	public static WelcomeScreen welcomeScreen;
	public static Leaderboard leaderboard;
	public static Grid grid;
	public static gameState state;
	
	public static JButton playButton;
	
	private static int score;
	
	public enum gameState {
		welcomeScreen,
		playing,
		gameOver
	}
	public RunTetris() {
		screen = new JFrame("Tetris");
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setResizable(true);
		
		grid = new Grid();
		
		clicker = new Clicker(grid);
		
		welcomeScreen = new WelcomeScreen();
		leaderboard = new Leaderboard();
		
		gamePanel = new GamePanel(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		gamePanel.addMouseListener(clicker);
		
		// For initiating actions
		addRotateAction("UP");
		
	}
	
	public void playTetris() { // essentially a runWelcomeScreen
		state = gameState.welcomeScreen;
		score = 0;
		
		screen.setContentPane(gamePanel);
		screen.pack();
		screen.setVisible(true);
		refreshTimer.start();
		
		
	}
	
	public void runPlaying() {
		state = gameState.playing;
		
		addShiftAction("DOWN");
		addShiftAction("RIGHT");
		addShiftAction("LEFT");
		addRotateAction("UP");
		addHoldAction("C");
		
		dropTimer.start();
	}
	
	public static void endGame() {
		removeAction("DOWN");
		removeAction("RIGHT");
		removeAction("LEFT");
		removeAction("UP");
		removeAction("C");
		
		state = gameState.gameOver;
		dropTimer.stop();
	}
	
	ActionListener refresher = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (state == gameState.welcomeScreen) {
				if (welcomeScreen.checkSignal()) {
					welcomeScreen.setSignal(false);
					runPlaying();
				}
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
	
	public static int getSizePerc(double perc) {
		return Math.min(getWidthPerc(perc), getHeightPerc(perc));
	}
	
	private static void addShiftAction(String name){
		Action newAction = new ShiftAction(name, grid);
		KeyStroke key = KeyStroke.getKeyStroke(name);
		gamePanel.getInputMap().put(key, name);
		gamePanel.getActionMap().put(name, newAction);
	}
	
	private static void addRotateAction(String name) {
		Action newAction = new RotateAction(grid, 1);
		KeyStroke key = KeyStroke.getKeyStroke(name);
		gamePanel.getInputMap().put(key, name);
		gamePanel.getActionMap().put(name, newAction);
	}
	
	private static void addHoldAction(String name) {
		Action newAction = new HoldAction(grid);
		KeyStroke key = KeyStroke.getKeyStroke(name);
		gamePanel.getInputMap().put(key, name);
		gamePanel.getActionMap().put(name, newAction);
	}
	
	private static void removeAction(String name) {
		KeyStroke key = KeyStroke.getKeyStroke(name);
		gamePanel.getInputMap().remove(key);
	}
	
	public static int getScore() {
		return score;
	}
	
	public static void incScore(int amount) {
		score += amount;
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