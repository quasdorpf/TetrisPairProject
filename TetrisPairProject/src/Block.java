import java.awt.*;

public class Block {
	private Color color;
	private boolean empty;
	public Block(Color color, boolean empty) {
		this.color = color;
		this.empty = empty;
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