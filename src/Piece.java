import java.awt.Color;
import java.awt.Graphics;

public abstract class Piece {
	 
	public Block[][] matrix; //0 = empty, 1 = block, 2= block and pivot
	public int rowPos, colPos; //About pivot point, which is always the bottom middle of the matrix 
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

//	public void setRowPos(int row) {
//		this.rowPos=row;
//	}
//	public int getRowPos() {
//		return this.rowPos;
//	}
//	public void setColPos(int col) {
//		this.colPos=col;
//	}
//	public int getColPos() {
//		return this.colPos;
//	}
//	public int[][] getMatrix() {
//		return matrix;
//	}
//	public void setMatrix(int row, int col, int val) {
//		matrix[row][col] = val;
//	}
//	public Color getColor() {
//		return this.pieceColor;
//	} 
	
}
