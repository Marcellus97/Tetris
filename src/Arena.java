import java.awt.Graphics;

public class Arena {

	public static final int ROWS = GamePanel.PANEL_HEIGHT/Block.BLOCK_HEGIHT;
	public static final int COLS = GamePanel.PANEL_WIDTH/Block.BLOCK_WIDTH;

	public Block[][] matrix;

	public Arena() {
		matrix = new Block[ROWS][COLS];
	}

	public void draw(Graphics g) {
		for (int i =0; i<matrix.length; i++) {
			for (int j =0; j<matrix[i].length; j++) {
				if(matrix[i][j]!= null) {
					
				}
			}
		}
	}
}
