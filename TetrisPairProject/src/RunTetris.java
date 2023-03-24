import javax.swing.*;

public class RunTetris {
	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 600;
	
	private JFrame screen;
	private GamePanel gamePanel;
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
		
		// temp
		screen.setContentPane(gamePanel);
		screen.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		screen.setVisible(true);
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