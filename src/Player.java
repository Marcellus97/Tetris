
public class Player {

	private Piece piece;
	private Arena arena;
	
	public Player(Piece piece, Arena arena) {
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
	public boolean collide(Block[][] matrix) {
		return false;
	}
}
