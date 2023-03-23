import javax.swing.*;

public class RunTetris {
	private JFrame screen;
	private GamePanel gamePanel;
	private WelcomeScreen welcomeScreen;
	private Leaderboard leaderboard;
	private Grid grid;
	private int score;
	private boolean playingTetris = false;
	public RunTetris() {
		
	}
	
	public void playTetris() {
		
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