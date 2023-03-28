import java.awt.*;
import javax.swing.*;

public class Tester {
	public static void main(String[] args) {
//		ZTetromino tetr = new ZTetromino();
//		Block[][] rotation;
//		for(int i=0; i<4; i++) {
//			rotation = tetr.getRotation();
//			
//			for (int x=0; x<tetr.size(); x++) {
//				for (int y=0; y<tetr.size(); y++) {
//					System.out.print(rotation[x][y].isEmpty() + "\t");
//				}
//				System.out.println();
//			}
//			System.out.println("\n");
//			
//			tetr.rotate(Tetromino.RIGHT);
//		}
		Grid g = new Grid();
		Clicker c = new Clicker(g);
		JFrame f = new JFrame();
		JPanel p = new JPanel();
		p.addMouseListener(c);
		f.add(p);
		f.setSize(200, 200);
		f.setVisible(true);
	}
}