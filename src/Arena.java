import java.awt.Graphics;
import java.util.ArrayList;

public class Arena {

	public static final int ROWS=20;
	public static final int COLS=11;

	ArrayList<Block> blocks;

	public Arena() {

		blocks = new ArrayList<>();
	}


	public void merge(Tetromino tetromino) {
		for (Block current : tetromino.blocks) {
			blocks.add(new Block(current.rowPos, current.colPos, current.color.darker()));
		}
	}
	public void draw(Graphics g) {
		for (Block block : blocks) {
			block.draw(g);
		}
	}
}
