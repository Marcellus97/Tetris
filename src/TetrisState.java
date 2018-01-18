import java.awt.Graphics;

import com.sun.glass.events.KeyEvent;

public class TetrisState extends GameState{
	private int ticks;
	private Tetromino player;
	private Arena arena;

	public TetrisState(GameStateManager gsm) {
		super(gsm);
		init();
		arena = new Arena();
	}

	@Override
	public void init() {
		player = new Tetromino();
	}

	@Override
	public void tick() {
		ticks++;
		if(ticks>GamePanel.FPS) {
			
			fall();
			
			ticks=0;
		}
	}

	public void fall() {
		for(Block current: player.blocks) {
			current.rowPos++;
		}
		player.pivRow++;
		
		if(collideFloor() || collideBlock()) {
			moveUp();
			arena.merge(player);
			init();
		}
	}

	public void instantFall() {

	}

	public void rotate() {
		if(player.shape != Shape.Square) { //Square doesn't rotate
			for(Block current: player.blocks) {
				int VRr = current.rowPos-player.pivRow;
				int VRc = current.colPos-player.pivCol;
				
				//Mulitply by Rotated IDentity Matrix / CCW
				//[0 -1]
				//[1 0 ]
				
				int VTr = -1 * VRc;
				int VTc = VRr;
				
				current.rowPos = player.pivRow + VTr;
				current.colPos = player.pivCol + VTc;
				
			}
		}
		
		while(collideFloor()) {
			moveUp();
		}
	}
	public void moveUp() {
		for(Block current: player.blocks) {
			current.rowPos--;
		}
		player.pivRow--;
	}

	public void moveRight() {
		for(Block current: player.blocks) {
			current.colPos++;
		}
		player.pivCol++;
	}
	
	public void moveLeft() {
		for(Block current: player.blocks) {
			current.colPos--;
		}
		player.pivCol--;
	}
	
	public boolean collideWall() {
		for (Block current: player.blocks) {
			if(current.colPos < 0) {
				System.out.println("Collide left");
				return true;
			}
			else if (current.colPos >= Arena.COLS) {
				System.out.println("Collide right");
				return true;
			}
		}
		return false;
	}
	
	public boolean collideFloor() {
		for (Block current: player.blocks) {
			if(current.rowPos >= Arena.ROWS) {
				System.out.println("Collide Floor");
				return true;
			}
		}
		return false;
	}
	
	public boolean collideBlock() {
		for (Block current: player.blocks) {
			for (Block arenaBlock: arena.blocks) {
				if(current.rowPos == arenaBlock.rowPos && current.colPos == arenaBlock.colPos) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void draw(Graphics g) {
		player.draw(g);
		arena.draw(g);
	}

	@Override
	public void keyPressed(int k) {
		if(k==KeyEvent.VK_RIGHT) {
			moveRight();
			if(collideWall() || collideBlock()) {
				moveLeft();
			}
		}
		else if(k==KeyEvent.VK_LEFT) {
			moveLeft();
			if(collideWall() || collideBlock()) {
				moveRight();
			}
		}

		else if(k==KeyEvent.VK_DOWN) {
			fall();
		}

		else if(k==KeyEvent.VK_UP) {
			instantFall();
		}
		else if(k==KeyEvent.VK_SPACE) {
			rotate();
		}

		else if(k==KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}
	
	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub

	}

}


//import java.awt.Color;
//import java.awt.Graphics;
//import java.util.ArrayList;
//
//import com.sun.glass.events.KeyEvent;
//
//public class TetrisState extends GameState{
//	int rows, cols;
//	int[][] arena;
//
//	int ticks;
//	Block block;
//
//	public TetrisState(GameStateManager gsm) {
//		super(gsm);
//
//		rows = GamePanel.PANEL_HEIGHT/Block.SIZE;
//		cols = GamePanel.PANEL_WIDTH/Block.SIZE;
//		arena = new int[rows][cols];
//
//		System.out.println(rows);
//		System.out.println(cols);
//	}
//
//	@Override
//	public void init() {
//		block = new Block();
//	}
//
//	@Override
//	public void tick() {
//		ticks++;
//		if(ticks>GamePanel.FPS) {
//			fall();
//			ticks=0;
//
//		}
//	}
//
//	public void fall() {
//		block.rowPos++;
//		if(collideFloor() || collideBlock()){
//			block.rowPos--;
//			merge();
//			init();
//			deleteCompleteRows();
//		}
//		//System.out.println("row: "+block.rowPos+" col: "+block.colPos);
//	}
//
//	public void instantFall() {
//		block.rowPos++;
//		while(!collideFloor() && !collideBlock()){
//			block.rowPos++;
//		}
//		block.rowPos--;
//		merge();
//		init();
//		deleteCompleteRows();
//	}
//
//	public void rotate() {
//		int[][]newMatrix = new int[block.matrix[0].length][block.matrix.length];
//
//		for(int i = 0; i<block.matrix.length; i++) {
//			for (int j = 0; j<block.matrix[i].length; j++) {
//				newMatrix[j][block.matrix.length-1-i]=block.matrix[i][j];
//			}
//		}
//		int diff = newMatrix.length-block.matrix.length;
//		System.out.println(diff);
//		block.rowPos-=diff;
//		block.matrix = newMatrix;
//	}
//
//	public boolean collideWall() {
//		int blockColPos=0;
//
//		for(int i = 0; i<block.matrix.length; i++) {
//			for (int j = 0; j<block.matrix[i].length; j++) {
//				if(block.matrix[i][j]==1) {
//					blockColPos=block.colPos + j;
//
//					if(blockColPos>cols-1) {
//						System.out.println("collide right");
//						return true;
//					}
//					if(blockColPos<0) {
//						System.out.println("collide left");
//						return true;
//					}
//				}
//			}
//		}
//		return false;
//	}
//
//	public boolean collideFloor(){
//		int blockRowPos=0;
//
//		for(int i = 0; i<block.matrix.length; i++) {
//			for (int j = 0; j<block.matrix[i].length; j++) {
//				if(block.matrix[i][j]==1) {
//					blockRowPos=block.rowPos +i;
//
//					if(blockRowPos>=rows) { 
//						System.out.println("collide bottom");	
//						return true;
//					}
//				}
//			}
//		}
//		return false;
//	}
//	public boolean collideBlock() {
//		for(int i = 0; i<block.matrix.length; i++) {
//			for (int j = 0; j<block.matrix[i].length; j++) {
//				if(block.matrix[i][j]==1 && arena[block.rowPos+i][block.colPos+j]==1) {
//					System.out.println("collide block");
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	public void merge() {
//		for(int i = 0; i<block.matrix.length; i++) {
//			for (int j = 0; j<block.matrix[i].length; j++) {
//				if(block.matrix[i][j]==1) {
//					System.out.println(block.rowPos+i+", "+(block.colPos+j));
//					arena[block.rowPos+i][block.colPos+j]=1;
//				}
//			}
//		}
//	}
//
//	private void deleteCompleteRows() {
//
//		ArrayList<Integer> rowsCompleted = new ArrayList<>();
//		boolean rowCompleted;
//		int lowestEmptyRow = -1;
//
//		for (int i = 0; i<arena.length; i++) {
//			rowCompleted = true;
//
//			for (int j = 0; j<arena[i].length; j++) {
//				if(arena[i][j]!=1) {
//					rowCompleted = false;
//					break;
//				}
//			}
//
//			if(rowCompleted) {
//				rowsCompleted.add(i);
//				lowestEmptyRow = Math.max(lowestEmptyRow, i);
//			}
//		}
//
//
//		//Possible animations with  rowsCompleted
//		
//		// delete completed rows and shift above rows downward
//		for (int i = lowestEmptyRow; i>rowsCompleted.size(); i--) {
//			for(int j = 0; j<arena[i].length; j++) {
//				arena[i][j] = arena[i- rowsCompleted.size()][j];
//			}
//		}
//
//		System.out.println("Deleted "+rowsCompleted.size()+" rows");
//	}
//
//	@Override
//	public void draw(Graphics g) {
//		g.setColor(Color.GRAY);
//		g.fillRect(0, 0, GamePanel.PANEL_WIDTH, GamePanel.PANEL_HEIGHT);
//
//		g.setColor(Color.red.darker());
//		for(int i = 0; i<block.matrix.length; i++) {
//			for (int j = 0; j<block.matrix[i].length; j++) {
//				if(block.matrix[i][j]==1)
//					g.fillRect(block.colPos*Block.SIZE+ j*Block.SIZE, block.rowPos*Block.SIZE +i*Block.SIZE, Block.SIZE, Block.SIZE);
//			}
//		}
//
//		g.setColor(Color.blue);
//		for(int i = 0; i<arena.length; i++) {
//			for (int j = 0; j<arena[i].length; j++) {
//				if(arena[i][j]==1)
//					g.fillRect(j*Block.SIZE, i*Block.SIZE, Block.SIZE, Block.SIZE);
//			}
//		}
//
//	}
//
//	@Override
//	public void keyPressed(int k) {
//		if(k==KeyEvent.VK_RIGHT) {
//			block.colPos++;
//			if(collideWall() || collideBlock())
//				block.colPos--;
//		}
//
//		else if(k==KeyEvent.VK_LEFT) { //hits left wall
//			block.colPos--;
//			if(collideWall() || collideBlock())
//				block.colPos++;
//		}
//
//		else if(k==KeyEvent.VK_DOWN) {
//			fall();
//		}
//
//		else if(k==KeyEvent.VK_UP) {
//			instantFall();
//		}
//		else if(k==KeyEvent.VK_SPACE) {
//			rotate();
//		}
//
//		else if(k==KeyEvent.VK_ESCAPE) {
//			System.exit(0);
//		}
//	}
//	@Override
//	public void keyReleased(int k) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
