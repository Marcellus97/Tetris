import java.awt.Color;
import java.awt.Graphics;

public class Tetromino {

	Block blocks[];
	Shape shape;
	Color color;
	int pivRow, pivCol;

	public Tetromino() {
		randomShape();
	}


	private void randomShape() {
		int num = (int) (Math.random() * 7); //6 shapes, randomly generated
		int mid = (Arena.COLS/2); //Middle Col
		
		switch(6) {

		case 0: shape = Shape.Square; //
		color = Color.red;
		blocks = new Block[]{
				new Block(-2,mid, color), new Block(-2,mid+1, color),
				new Block(-1,mid, color), new Block(-1,mid+1, color)
		};
		pivRow = pivCol = 0;
		break;

		case 1: shape = Shape.Line;
		color = Color.red;
		blocks = new Block[]{
				new Block(-4,mid, color), 
				new Block(-3,mid, color),
				new Block(-2,mid, color), 
				new Block(-1,mid, color)
		};
		pivRow = -2;
		pivCol = mid;
		break;

		case 2: shape = Shape.S;
		color = Color.red;
		blocks = new Block[]{
				new Block(0,0, color), new Block(0,1, color),
				new Block(1,0, color), new Block(1,1, color)
		};
		pivRow = pivCol = 0;
		break;

		case 3: shape = Shape.Z;
		color = Color.red;
		blocks = new Block[]{
				new Block(0,0, color), new Block(0,1, color),
				new Block(1,0, color), new Block(1,1, color)
		};
		pivRow = pivCol = 0;
		break;

		case 4: shape = Shape.L;
		color = Color.red;
		blocks = new Block[]{
				new Block(0,0, color), new Block(0,1, color),
				new Block(1,0, color), new Block(1,1, color)
		};
		pivRow = pivCol = 0;
		break;

		case 5: shape = Shape.J;
		color = Color.red;
		blocks = new Block[]{
				new Block(0,0, color), new Block(0,1, color),
				new Block(1,0, color), new Block(1,1, color)
		};
		pivRow = pivCol = 0;
		break;

		case 6: shape = Shape.T;
		color = Color.MAGENTA;
		blocks = new Block[]{
				new Block(-2,mid-1, color), new Block(-2,mid, color),new Block(-2,mid+1, color),
				new Block(-1,mid, color)
		};
		pivRow = -2;
		pivCol = mid;
		break;

		}
	}


	public void draw(Graphics g) {
		for (Block block : blocks) {
			block.draw(g);
		}
	}

}
