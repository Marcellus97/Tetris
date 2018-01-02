import java.awt.Color;
import java.awt.Graphics;

public abstract class Piece {
	 
	public Block[][] matrix; 
	public int rowPos, colPos; // of top left block, or [0][0]
	public Color pieceColor;

	public Piece(int rows, int cols, Color pieceColor) {
		int max = Math.max(rows, cols);	
		matrix = new Block[max][max];

		rowPos = 0;
		colPos = Arena.COLS/2 - 1;
		
		this.pieceColor = pieceColor;
	}
	
	public abstract boolean rotateCW();
	public abstract boolean rotateCCW();
	public abstract void draw(Graphics g);

}
