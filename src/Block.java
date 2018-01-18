import java.awt.Color;
import java.awt.Graphics;

public class Block {
	public final static int SIZE = 40;
	int rowPos, colPos;
	boolean pivot;
	Color color;
	
	public Block(int rowPos, int colPos, Color color, boolean pivot) {
		this.rowPos = rowPos;
		this.colPos = colPos;
		this.pivot = pivot;
		
		this.color= (pivot) ? color.darker(): color;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(colPos*SIZE, rowPos*SIZE, SIZE, SIZE);
		g.setColor(Color.black);
		g.drawRect(colPos*SIZE, rowPos*SIZE, SIZE, SIZE);
	}
	
}
