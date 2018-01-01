import java.awt.Color;
import java.awt.Graphics;

public class TPiece extends Piece {

	public TPiece() {
		super(2, 2);
		
		matrix[0][0] = new Block(Arena.COLS/2,0); matrix[1][0] = new Block((Arena.COLS/2) +1,0);
		matrix[0][1] = new Block(Arena.COLS/2,1); matrix[0][0] = new Block((Arena.COLS/2) +1,1);
	}

	@Override
	public boolean rotateCW() {
		return true;
	}

	@Override
	public boolean rotateCCW() {
		return true;
	}

	@Override
	public void draw(Graphics g) {
		for (int i =0; i<matrix.length; i++) {
			for (int j =0; j<matrix[i].length; j++) {
				matrix[i][j].draw(g);
			}
		}
	}
	
	
}
