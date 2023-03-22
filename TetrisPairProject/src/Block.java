import java.awt.*;

public class Block {
	private Color color;
	private boolean empty = false;
	public Block(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
	public boolean isEmpty() {
		return empty;
	}
	public void setEmpty(boolean e) {
		empty = e;
	}
}