import java.awt.Color;
import java.awt.Graphics;

public class SquarePiece extends Piece {

	public SquarePiece() {
		super(2, 2);
		
		//matrix[0][0] = new Block();
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
