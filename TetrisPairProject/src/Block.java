import java.awt.*;

public class Block {
	private Color color;
	private Color lightColor;
	private Color shadedColor;
	private Color darkColor;
	private boolean empty;
	private int[] coords;
	public Block(Color color) {
		this.color = color;
		empty = false;
		setLightShadedAndDarkColors();
	}
	public Block() {
		this.color = Color.GRAY;
		empty = true;
		setLightShadedAndDarkColors();
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color c) {
		color = c;
		setLightShadedAndDarkColors();
	}
	public boolean isEmpty() {
		return empty;
	}
	public void setEmpty(boolean e) {
		empty = e;
	}
	public void setCoords(int row, int column) {
		coords[0] = row;
		coords[1] = column;
	}
	public int getY() {
		return coords[0];
	}
	public int getX() {
		return coords[1];
	}
	public void draw(Graphics g, int x, int y, int size) {
		if (!empty) {
			int sideSize = (int)((double)size * 0.1);
			int midSize = size - (sideSize*2);
			
			// Middle
			g.setColor(color);
			g.fillRect(x+sideSize, y+sideSize, midSize, midSize);
			
			// Top
			g.setColor(lightColor);
			int[] xCoordsT = {x, x+size, x+size-sideSize, x+sideSize};
			int[] yCoordsT = {y, y, y+sideSize, y+sideSize};
			g.fillPolygon(xCoordsT, yCoordsT, 4);
			
			// Left
			g.setColor(shadedColor);
			int[] xCoordsL = {x, x+sideSize, x+sideSize, x};
			int[] yCoordsL = {y, y+sideSize, y+size-sideSize, y+size};
			g.fillPolygon(xCoordsL, yCoordsL, 4);
			
			// Right
			int[] xCoordsR = {x+size, x+size-sideSize, x+size-sideSize, x+size};
			int[] yCoordsR = {y, y+sideSize, y+size-sideSize, y+size};
			g.fillPolygon(xCoordsR, yCoordsR, 4);
			
			// Bottom
			g.setColor(darkColor);
			int[] xCoordsD = {x, x+sideSize, x+size-sideSize, x+size};
			int[] yCoordsD = {y+size, y+size-sideSize, y+size-sideSize, y+size};
			g.fillPolygon(xCoordsD, yCoordsD, 4);
		}
	}
	private void setLightShadedAndDarkColors() {
		float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
		float[] lightHSB = new float[3];
		float[] shadedHSB = new float[3];
		float[] darkHSB = new float[3];
		for (int i=0; i<3; i++) {
			lightHSB[i] = hsb[i];
			shadedHSB[i] = hsb[i];
			darkHSB[i] = hsb[i];
		}
		
		lightHSB[1] = (float) (hsb[1] * 0.6);
		lightColor = Color.getHSBColor(lightHSB[0], lightHSB[1], lightHSB[2]);
		
		shadedHSB[2] = (float) (hsb[2] * 0.9);
		shadedColor = Color.getHSBColor(shadedHSB[0], shadedHSB[1], shadedHSB[2]);
		
		darkHSB[2] = (float) (hsb[2] * 0.7);
		darkColor = Color.getHSBColor(darkHSB[0], darkHSB[1], darkHSB[2]);
	}
}