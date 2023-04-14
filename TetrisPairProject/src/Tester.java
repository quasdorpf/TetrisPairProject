import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Tester{
	public static Grid g = new Grid();
	public static Clicker c = new Clicker(g);
	public static JFrame f = new JFrame();
	public static JPanel p = new JPanel();
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
//		p.addMouseListener(new Clicker(g));
//		p.addKeyListener(new Clicker(g));
		addAction("DOWN");
		addAction("RIGHT");
		addAction("LEFT");
		KeyStroke key = KeyStroke.getKeyStroke("UP");
		p.getInputMap().put(key, "UP");
		p.getActionMap().put("UP", new RotateAction(g, 1));
		f.add(p);
		f.setSize(200, 200);
		f.setVisible(true);
	}
	public static void addAction(String name){
		Action newAction = new ShiftAction(name, g);
		KeyStroke key = KeyStroke.getKeyStroke(name);
		p.getInputMap().put(key, name);
		p.getActionMap().put(name, newAction);
	}
}