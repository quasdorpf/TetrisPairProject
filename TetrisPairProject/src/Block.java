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
			
			// Top
			
			
			// Left
			
			
			// Middle
			g.setColor(color);
			g.fillRect(x+sideSize, y+sideSize, midSize, midSize);
			
			// Right
			
			
			// Bottom
			
		}
	}
	private void setLightShadedAndDarkColors() {
		int intensity = 2;
		int[] baseRGB = getBaseRGBValues(color, intensity);
		int[] light = {255, 255, 255};
		int[] shaded = {color.getRed(), color.getGreen(), color.getBlue()};
		int[] dark = {0, 0, 0};
		for (int i=0; i<3; i++) {
			light[i] -= baseRGB[i];
			shaded[i] -= intensity;
			dark[i] += baseRGB[i];
		}
		lightColor = new Color(light[0], light[1], light[2]);
		shadedColor = new Color(shaded[0], shaded[1], shaded[2]);
		darkColor = new Color(dark[0], dark[1], dark[2]);
	}
	private int[] getBaseRGBValues(Color c, int intensity) {
		int[] rgb = {c.getRed(), c.getGreen(), c.getBlue()};
		int minVal = Math.min(Math.min(rgb[0], rgb[1]), Math.min(rgb[1], rgb[2]));
		for (int i=0; i<rgb.length; i++) {
			rgb[i] = ((rgb[i] / minVal) + 1) * intensity;
		}
		return rgb;
	}
}