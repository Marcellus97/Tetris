import java.awt.Graphics;
import java.util.ArrayList;

public class Arena {

	public static final int ROWS=20;
	public static final int COLS=11;

	Block[][] blocks;

	public Arena() {

		blocks = new Block[ROWS][COLS];
	}


	public void merge(Player player) {
		Tetromino tetromino = player.tetromino;

		for(Block[] row: tetromino.blocks) {
			for(Block b: row) {
				if(b.pivot) {
					b.color = b.color.brighter();
				}
				blocks[b.rowPos][b.colPos] = new Block(b.rowPos, b.colPos, b.color, true);
			}
		}
	}

	public int sweep() {
		ArrayList<Integer> deletedRows = new ArrayList<>();

		for (int i = blocks.length-1; i>=0; i--) {
			// Determine and delete filled rows
			if(rowIsFilled(i)){
				deleteRow(i);
				deletedRows.add(i);
			}
		}
		for (int row: deletedRows) {
			System.out.println(row);
		}
		
		shiftRows(deletedRows);

		return deletedRows.size();
	}

	public boolean rowIsFilled(int row) {
		for (int j = 0; j <blocks[row].length; j++) {
			if(blocks[row][j] == null) {
				return false;
			}
		}
		return true;
	}

	public void deleteRow(int row) {
		for (int j = 0; j <blocks[row].length; j++) {
			blocks[row][j] = null;
		}
	}

	public void shiftRows(ArrayList<Integer> deletedRows) {

		for(int row: deletedRows) {
				// SHift all rows above deleted row down by 1
				for (int i =row; i>0; i--) {
					for (int j =0; j<COLS; j++) {
						Block b = blocks[i-1][j];
						if(b!=null) {
							blocks[i][j] = new Block(b.rowPos+1,b.colPos,b.color, false);
							blocks[i-1][j] = null;
						}else {
							blocks[i][j] = null;
						}
					}
				}
		}
	}

	public void draw(Graphics g) {
		for (Block[] row : blocks) {
			for (Block b : row) {
				if(b!=null) {
					b.draw(g);
				}
			}
		}
	}
}
