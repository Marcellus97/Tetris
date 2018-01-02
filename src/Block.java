import java.awt.Color;
import java.awt.Graphics;

public class Block {

	public static final int BLOCK_WIDTH = 50;
	public static final int BLOCK_HEGIHT = 50;
	
	public int row, col;
	public Color color;
	
	public Block(int row, int col, Color color) {
		this.row = row;
		this.col = col;
		this.color = color;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(col*BLOCK_WIDTH, row*BLOCK_HEGIHT, BLOCK_WIDTH, BLOCK_HEGIHT);
		g.setColor(Color.BLACK);
		g.drawRect(col*BLOCK_WIDTH, row*BLOCK_HEGIHT, BLOCK_WIDTH, BLOCK_HEGIHT);
	}

}
