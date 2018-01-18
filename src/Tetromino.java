import java.awt.Color;
import java.awt.Graphics;

public class Tetromino {

	Block blocks[][];
	Shape shape;
	Color color;
	int pivRow, pivCol;
	private boolean initialDirection; //flips every rotation

	public Tetromino() {
		initialDirection = true;
		chooseShape(true);
		longestRow();
	}
	public Tetromino(Shape shape) {
		initialDirection = true;
		this.shape=shape;
		chooseShape(false);
		longestRow();
	}

	public void flipDir() {
		initialDirection=!initialDirection;
	}

	public int longestRow() {
		int longest = 0;

		if(initialDirection) {
			longest = blocks.length;
		}
		else {
			for(Block[] row: blocks) {
				longest = Math.max(longest, row.length);
			}
		}
		System.out.println("Longest "+longest);
		return longest;
	}

	private void chooseShape(boolean random) {
		int num = (random) ? (int) (Math.random() * 7) : getShapeNum(shape); //6 shapes, randomly generated
		int mid = (Arena.COLS/2); //Middle Col

		switch(num) {

		case 0: shape = Shape.Square; //
		color = Color.red;
		blocks = new Block[][]{
			{new Block(-2,mid, color, false), new Block(-2,mid+1, color, false)},
			{new Block(-1,mid, color, false), new Block(-1,mid+1, color, false)}
		};
		pivRow = pivCol = 0;
		break;

		case 1: shape = Shape.Line;
		color = Color.CYAN;
		blocks = new Block[][]{
			{   new Block(-1,mid-1, color, false), new Block(-1,mid, color, true), 
				new Block(-1,mid+1, color, false), new Block(-1,mid+2, color, false)},
			{}
		};
		pivRow = -1;
		pivCol = mid;
		break;

		case 2: shape = Shape.S;
		color = Color.GREEN;
		blocks = new Block[][]{
			{                                  new Block(0,mid, color, true), new Block(0,mid+1, color, false)},
			{new Block(1,mid-1, color, false), new Block(1,mid, color, false)}
		};
		pivRow = 0;
		pivCol = mid;
		break;

		case 3: shape = Shape.Z;
		color = Color.ORANGE;
		blocks = new Block[][]{
			{new Block(0,mid-1, color, false), new Block(0,mid, color, true)},
			{                              new Block(1,mid, color, false), new Block(1,mid+1, color, false)}
		};
		pivRow = 0;
		pivCol = mid;
		break;

		case 4: shape = Shape.L;
		color = Color.PINK;
		blocks = new Block[][]{
			{new Block(-1,mid, color, false)}, 
			{new Block(0,mid, color, false)},
			{new Block(1,mid, color, true), new Block(1,mid+1, color, false)}
		};
		pivRow = 1;
		pivCol = mid;
		break;

		case 5: shape = Shape.J;
		color = Color.YELLOW.darker();
		blocks = new Block[][]{
			{                                  new Block(-1,mid, color, false)}, 
			{                                  new Block(0,mid, color, false)},
			{new Block(1,mid-1, color, false), new Block(1,mid, color, true)}
		};
		pivRow = 1;
		pivCol = mid;
		break;

		case 6: shape = Shape.T;
		color = Color.MAGENTA;
		blocks = new Block[][]{
			{new Block(-2,mid-1, color, false), new Block(-2,mid, color, true),new Block(-2,mid+1, color, false)},
			{                                   new Block(-1,mid, color, false)}
		};
		pivRow = -2;
		pivCol = mid;
		break;

		}
	}


	private int getShapeNum(Shape shape) {

		switch(shape) {
		case Square:
			return 0;
		case Line:
			return 1;
		case S:
			return 2;
		case Z:
			return 3;
		case L:
			return 4;
		case J:
			return 5;
		case T:
			return 6;
		}
		return 0;

	}
	public void draw(Graphics g) {
		for(Block[] row: blocks) {
			for(Block b: row) {
				b.draw(g);
			}
		}
	}

}
