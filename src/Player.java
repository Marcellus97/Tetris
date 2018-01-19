import java.awt.Graphics;

public class Player {

	Tetromino tetromino;
	Shape shape;

	public Player() {
		tetromino = new Tetromino();
		shape = tetromino.shape;
	}

	public void moveVertically(int rows) {
		for(Block[] row: tetromino.blocks) {
			for(Block b: row) {
				b.rowPos+=rows;
			}
		}
		tetromino.pivRow+=rows;
	}


	public void moveLaterally(int cols) {
		for(Block[] row: tetromino.blocks) {
			for(Block b: row) {
				b.colPos+=cols;
			}
		}
		tetromino.pivCol+=cols;
	}

	public void rotate(boolean clockWise) {

		int[][] rotationMatrix = new int[][]{ //ccw
			{0, -1},
			{1, 0}};

			if (clockWise) {

				rotationMatrix = new int[][]{ //cw
					{0,  1},
					{-1, 0}};
			}

			if(tetromino.shape != Shape.Square) { //Square doesn't rotate
				for(Block[] row: tetromino.blocks) {
					for(Block b: row) {

						//old Position relative from pivot
						int relativeRow = b.rowPos-tetromino.pivRow;
						int relativeCol = b.colPos-tetromino.pivCol;

						//Mulitply by Rotated Identity Matrix / CCW or CW
						int transformRow = (rotationMatrix[0][0] * relativeRow) + (rotationMatrix[0][1] * relativeCol);
						int transformCol = (rotationMatrix[1][0] * relativeRow) + (rotationMatrix[1][1] * relativeCol);

						//Translate old posiitons to new row/col
						b.rowPos = tetromino.pivRow + transformRow;
						b.colPos = tetromino.pivCol + transformCol;
					}
				}
			}
			tetromino.flipDir();
	}

	public void draw(Graphics g) {
		tetromino.draw(g);
	}
}
