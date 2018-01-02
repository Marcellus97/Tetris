import java.awt.Graphics;

public class Player {

	Piece piece;
	Arena arena;

	public Player(Arena arena,Piece piece) {
		this.piece=piece;
		this.arena=arena;
	}

	public void moveRight(){
		for (int i =0; i<piece.matrix.length;i++) {
			for (int j =0; j<piece.matrix[i].length; j++) {
				if(piece.matrix[i][j] != null) {
					piece.matrix[i][j].col++;
				}
			}
		}
	}
	public void moveLeft() {
		for (int i =0; i<piece.matrix.length;i++) {
			for (int j =0; j<piece.matrix[i].length; j++) {
				if(piece.matrix[i][j] != null) {
					piece.matrix[i][j].col--;
				}
			}
		}

	}
	public void moveDown() {
		for (int i =0; i<piece.matrix.length;i++) {
			for (int j =0; j<piece.matrix[i].length; j++) {
				if(piece.matrix[i][j] != null) {
					piece.matrix[i][j].row++;
				}
			}
		}

	}
	public void instantFall() {
		while(!collision()) {
			moveDown();
		}
	}
	public void check() {
		for (int i =0; i<piece.matrix.length;i++) {
			for (int j =0; j<piece.matrix[i].length; j++) {
			}
		}
	}
	public boolean collision() { //fix this
		for (int i =0; i<piece.matrix.length;i++) {
			for (int j =0; j<piece.matrix[i].length; j++) {
				if(piece.matrix[i][j] != null && arena.matrix != null) {
					Block block = piece.matrix[i][j]; 
					if(block.row > arena.ROWS-1 || block.row < 0) {
						System.out.println("collide 1");
						return true;
					}
					if(block.col > arena.COLS-1 || block.col < 0) {
						System.out.println("collide 2");
						return true;
					}
					if(arena.matrix[block.row]!=null && arena.matrix[block.col]!=null) {
						System.out.println("collide 3");
						return true;
					}
				}
			}
		}
		return false;
	}
	private int lastOccupuedRow() {
		
		return 0;
	}

	public void draw(Graphics g) {
		for (int i =0; i<piece.matrix.length;i++) {
			for (int j =0; j<piece.matrix[i].length; j++) {
				if(piece.matrix[i][j]!=null) {
					piece.matrix[i][j].draw(g);
				}
			}
		}
	}
}
