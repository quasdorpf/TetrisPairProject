import javax.swing.*;
import java.awt.event.*;

public class RunTetris {
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 600;
	public static final int DEFAULT_SIZE = Math.min(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	private static final int TIME_BETWEEN_ANIMATIONS = 30;
	private static final int TIME_BETWEEN_DROPS = 1000;
	
	public static JFrame screen;
	public static GamePanel gamePanel;
	public static Clicker clicker;
	public static Leaderboard leaderboard;
	public static Grid grid;
	public static gameState state;
	
	public static JButton playButton, retryButton, exitButton;
	
	private static InputMap inputMap;
	private static ActionMap actionMap;
	
	private static int score;
	
	public static boolean playTrigger = false,
			endGameTrigger = false,
			retryTrigger = false,
			exitTrigger = false;
	
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
		
		leaderboard = new Leaderboard();
		
		gamePanel = new GamePanel(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		gamePanel.addMouseListener(clicker);
		
		playButton = new JButton("Play");
		playButton.setActionCommand("Play");
		playButton.addActionListener(clicker);
		retryButton = new JButton("Retry");
		retryButton.setActionCommand("Retry");
		retryButton.addActionListener(clicker);
		exitButton = new JButton("Exit");
		exitButton.setActionCommand("Exit");
		exitButton.addActionListener(clicker);
		
		// For initiating actions
		inputMap = gamePanel.getInputMap();
		actionMap = gamePanel.getActionMap();
		addShiftAction("DOWN");
		addShiftAction("RIGHT");
		addShiftAction("LEFT");
		addHardDropAction("SPACE");
		addRotateAction("UP");
		addHoldAction("C");
		
		
		screen.setContentPane(gamePanel);
		screen.pack();
		screen.setVisible(true);
		refreshTimer.start();
		screen.setSize(RunTetris.DEFAULT_WIDTH, RunTetris.DEFAULT_HEIGHT);
	}
	
	public void playTetris() { // essentially a runWelcomeScreen
		state = gameState.welcomeScreen;
		
		
	}
	
	public void runPlaying() {
		state = gameState.playing;
		
		gamePanel.requestFocusInWindow();
		grid.initializeGrid();
		score = 0;		
		
		
		dropTimer.start();
	}
	
	public void endGame() {
		state = gameState.gameOver;
		leaderboard.addScore(score);
		dropTimer.stop();
	}
	
	ActionListener refresher = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (state == gameState.welcomeScreen) {
				if (playTrigger) {
					playTrigger = false;
					RunTetris.gamePanel.remove(RunTetris.playButton);
					runPlaying();
				}
			} else if (state == gameState.playing) {
				if (endGameTrigger) {
					endGameTrigger = false;
					endGame();
				}
			} else if (state == gameState.gameOver) {
				if (retryTrigger) {
					retryTrigger = false;
					RunTetris.gamePanel.remove(RunTetris.retryButton);
					RunTetris.gamePanel.remove(RunTetris.exitButton);
					runPlaying();
				}
			}
			if (exitTrigger) {
				exitTrigger = false;
				RunTetris.gamePanel.remove(RunTetris.retryButton);
				RunTetris.gamePanel.remove(RunTetris.exitButton);
				playTetris();
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
		inputMap.put(key, name);
		actionMap.put(name, newAction);
	}
	private static void addHardDropAction(String name){
		Action newAction = new HardDropAction(grid);
		KeyStroke key = KeyStroke.getKeyStroke(name);
		inputMap.put(key, name);
		actionMap.put(name, newAction);
	}
	
	private static void addRotateAction(String name) {
		Action newAction = new RotateAction(grid, 1);
		KeyStroke key = KeyStroke.getKeyStroke(name);
		inputMap.put(key, name);
		actionMap.put(name, newAction);
	}
	
	private static void addHoldAction(String name) {
		Action newAction = new HoldAction(grid);
		KeyStroke key = KeyStroke.getKeyStroke(name);
		inputMap.put(key, name);
		actionMap.put(name, newAction);
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