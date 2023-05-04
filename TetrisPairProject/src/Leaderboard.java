import java.awt.*;
import java.util.ArrayList;

public class Leaderboard {
	private ArrayList<Integer> scores = new ArrayList<Integer>();
	
	public void draw(Graphics g, int x, int y, int width, int height) {
		double size = Math.min(width, height);
		int spaceBetweenScores = (int)((double)height * 0.17);
		int arcSize = RunTetris.getSizePerc(0.04);
		GamePanel.drawLayeredRoundRect(g, x, y, width, height, arcSize, arcSize, 
				relSizePerc(0.05, size), Color.BLACK, relSizePerc(0.035, size), false);
		g.setColor(Color.WHITE);
		g.fillRoundRect(x, y, width, height, arcSize, arcSize);
		
		int strScoreSize = relSizePerc(0.1, size);
		g.setFont(new Font("Serif", Font.PLAIN, strScoreSize));
		g.setColor(Color.BLACK);
		g.drawString("Leaderboard", x+(int)((double)width*0.23), y+(int)((double)height*0.085));
		
		g.setFont(new Font("Serif", Font.PLAIN, relSizePerc(0.1, size)));
		for (int i=0; i<5; i++) {
			int strY = y+(spaceBetweenScores*i)+(int)((double)height*0.25);
			int yDistanceTop = (spaceBetweenScores / 2) + (strScoreSize / 2);
			if (i < scores.size()) {
				double digits;
				int scoreRem = scores.get(i);
				for (digits=0; scoreRem>0; digits++) {
					scoreRem /= 10;
				}
				g.drawString(String.valueOf(scores.get(i)), 
						x+(int)((double)width*0.43)-(relSizePerc((digits-1)*0.02, width)), strY);
			}
			g.drawLine(x, strY-yDistanceTop, x+width, strY-yDistanceTop);
			g.drawLine(x, strY-yDistanceTop-1, x+width, strY-yDistanceTop-1);
		}
	}
	
	private int relSizePerc(double perc, double size) {
		return (int)(size*perc);
	}
	
	public void addScore(int score) {
		if (scores.size() == 0) {
			scores.add(score);
		} else {
			boolean added = false;
			for (int i=0; i<scores.size(); i++) {
				if (!added && score > scores.get(i)) {
					scores.add(i, score);
					added = true;
					if (scores.size() > 5) {
						scores.remove(5);
					}
				}
			}
			if (!added && scores.size() < 5) {
				scores.add(score);
			}
		}
	}
}