import java.awt.Color;
import java.awt.Graphics;

public class Block {

	public static final int BLOCK_WIDTH = 50;
	public static final int BLOCK_HEGIHT = 50;
	
	public int row, col;
	
	public Block(int row, int col) {
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics g) {
		g.drawRect(col*BLOCK_WIDTH, row*BLOCK_HEGIHT, BLOCK_WIDTH, BLOCK_HEGIHT);
		g.setColor(Color.RED);
		g.fillRect(col*BLOCK_WIDTH, row*BLOCK_HEGIHT, BLOCK_WIDTH, BLOCK_HEGIHT);
	}

}
