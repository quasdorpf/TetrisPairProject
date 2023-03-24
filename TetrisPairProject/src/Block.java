import java.awt.*;

public class Block {
	private Color color;
	private boolean empty;
	public Block(Color color) {
		this.color = color;
		empty = false;
	}
	public Block() {
		this.color = Color.GRAY;
		empty = true;
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