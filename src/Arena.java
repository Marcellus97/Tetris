import java.awt.Graphics;
import java.util.ArrayList;

public class Arena {

	public static final int ROWS=20;
	public static final int COLS=11;

	ArrayList<Block> blocks;

	public Arena() {

		blocks = new ArrayList<>();
	}


	public void merge(Player player) {
		Tetromino tetromino = player.tetromino;
		
		for(Block[] row: tetromino.blocks) {
			for(Block b: row) {
				if(b.pivot) {
					b.color = b.color.brighter();
				}
				blocks.add(new Block(b.rowPos, b.colPos, b.color, true));
			}
		}
	}
	public void draw(Graphics g) {
		for (Block block : blocks) {
			block.draw(g);
		}
	}
}
