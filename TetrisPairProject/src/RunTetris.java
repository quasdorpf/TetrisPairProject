import javax.swing.*;
import java.awt.event.*;

public class RunTetris {
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	private static final int TIME_BETWEEN_ANIMATIONS = 10;
	private static final int TIME_BETWEEN_DROPS = 10;
	
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
		playing
	}
	public RunTetris() {
		screen = new JFrame("Tetris");
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setResizable(true);
		
		welcomeScreen = new WelcomeScreen();
		leaderboard = new Leaderboard();
		grid = new Grid();
		
		
		
		
	}
	
	public void playTetris() {
		state = gameState.welcomeScreen;
		currScore = 0;
		
		gamePanel = new GamePanel(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		clicker = new Clicker(grid);
		gamePanel.addMouseListener(clicker);
		screen.setContentPane(gamePanel);
		screen.pack();
		screen.setVisible(true);
		refreshTimer.start();
	}
	
	ActionListener refresher = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (state == gameState.welcomeScreen) {
				
			} else if (state == gameState.playing) {
				
			}
			gamePanel.repaint();
		}
	};
	Timer refreshTimer = new Timer(TIME_BETWEEN_ANIMATIONS, refresher);
	
	ActionListener dropper = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	Timer dropTimer = new Timer(TIME_BETWEEN_DROPS, dropper);
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				new RunTetris().playTetris();
			}
		});
	}
}