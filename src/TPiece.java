import java.awt.Color;
import java.awt.Graphics;

public class TPiece extends Piece {

	public TPiece(Color pieceColor) {
		super(3, 3, pieceColor);
		//---
		//-x-
		//xxx
		matrix[0][0] = null; matrix[0][1] = null; matrix[0][2] = null; 
		matrix[1][0] = null; matrix[1][1] = new Block(rowPos+1,colPos+1,pieceColor);  matrix[1][2] = null;
		matrix[2][0] = new Block(rowPos+2,colPos,pieceColor); matrix[2][1] = new Block(rowPos+2,colPos+1,pieceColor); matrix[2][2] = new Block(rowPos+2,colPos+2,pieceColor);
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
