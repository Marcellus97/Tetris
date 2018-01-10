
public class Block {
	public final static int SIZE = 50;
	int[][] matrix;
	int rowPos, colPos;
	String type;
	
	public Block() {
		rowPos = 0;
		colPos = 4;
		
		randomBlock();
		
	}

	private void randomBlock() {
		int[][] square = {	
				{1,1},
				{1,1}
		};
		int[][] line = {	
				{1},
				{1},
				{1},
				{1}
		};
		int[][] tee = {	
				{1,1,1},
				{0,1,0}
		};
		int[][] squiggly = {	
				{0,1,1},
				{1,1,0}
		};
		int[][] reverseSquiggly = {	
				{0,1,1},
				{1,1,0}
		};
		int[][] lBlock = {	
				{1,0},
				{1,0},
				{1,1}
		};
		int[][] reverseLBlock = {	
				{0,1},
				{0,1},
				{1,1}
		};
		int block = (int) (Math.random() * 7);
		
		switch(block) {
		case 0: matrix = square; break;
		case 1: matrix = line; break;
		case 2: matrix = tee; break;
		case 3: matrix = squiggly; break;
		case 4: matrix = reverseSquiggly; break;
		case 5: matrix = lBlock; break;
		case 6: matrix = reverseLBlock; break;
		}
	}
}
