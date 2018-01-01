import java.awt.Color;
import java.awt.Graphics;

public abstract class Piece {
	 
	public Block[][] matrix; 
	public int rowPos, colPos;
	public Color pieceColor;

	public Piece(int width, int height) {
		int max = Math.max(width, height);	
		matrix = new Block[max][max];

		rowPos = 0;
		colPos = Arena.COLS/2;
		
		initColor();
	}
	private void initColor() {
		pieceColor = Color.red;
	}
	
	public abstract boolean rotateCW();
	public abstract boolean rotateCCW();
	public abstract void draw(Graphics g);

}
