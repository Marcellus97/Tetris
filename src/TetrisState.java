import java.awt.Color;
import java.awt.Graphics;

import com.sun.glass.events.KeyEvent;

public class TetrisState extends GameState{
	int rows, cols;
	int[][] arena;

	int ticks;
	Block block;

	public TetrisState(GameStateManager gsm) {
		super(gsm);
		arena = new int[rows][cols];
	}

	@Override
	public void init() {
		rows = GamePanel.PANEL_HEIGHT/Block.SIZE;
		cols = GamePanel.PANEL_WIDTH/Block.SIZE;
		
		System.out.println(rows);
		System.out.println(cols);
		block = new Block();
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
		block.rowPos++;
		if(collideFloor() || collideBlock()){
			block.rowPos--;
			merge();
			init();
		}
		System.out.println("row: "+block.rowPos+" col: "+block.colPos);
	}
	public void instantFall() {
		block.rowPos++;
		while(!collideFloor() && !collideBlock()){
			block.rowPos++;
		}
		block.rowPos--;
		merge();
		init();
	}

	public void rotate() {
		int[][]newMatrix = new int[block.matrix[0].length][block.matrix.length];

		for(int i = 0; i<block.matrix.length; i++) {
			for (int j = 0; j<block.matrix[i].length; j++) {
				newMatrix[j][block.matrix.length-1-i]=block.matrix[i][j];
			}
		}
		int diff = newMatrix.length-block.matrix.length;
		System.out.println(diff);
		block.rowPos-=diff;
		block.matrix = newMatrix;
	}

	public boolean collideWall() {
		int blockColPos=0;

		for(int i = 0; i<block.matrix.length; i++) {
			for (int j = 0; j<block.matrix[i].length; j++) {
				if(block.matrix[i][j]==1) {
					blockColPos=block.colPos + j;

					if(blockColPos>cols-1) {
						System.out.println("collide right");
						return true;
					}
					if(blockColPos<0) {
						System.out.println("collide left");
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean collideFloor(){
		int blockRowPos=0;

		for(int i = 0; i<block.matrix.length; i++) {
			for (int j = 0; j<block.matrix[i].length; j++) {
				if(block.matrix[i][j]==1) {
					blockRowPos=block.rowPos +i;

					if(blockRowPos>=rows) { 
						System.out.println("collide bottom");	
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean collideBlock() {
		for(int i = 0; i<block.matrix.length; i++) {
			for (int j = 0; j<block.matrix[i].length; j++) {
				if(block.matrix[i][j]==1 && arena[block.rowPos+i][block.colPos+j]==1) {
					System.out.println("collide block");
					return true;
				}
			}
		}
		return false;
	}

	public void merge() {
		for(int i = 0; i<block.matrix.length; i++) {
			for (int j = 0; j<block.matrix[i].length; j++) {
				if(block.matrix[i][j]==1) {
					System.out.println(block.rowPos+i+", "+(block.colPos+j));
					arena[block.rowPos+i][block.colPos+j]=1;
				}
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, GamePanel.PANEL_WIDTH, GamePanel.PANEL_HEIGHT);

		g.setColor(Color.red.darker());
		for(int i = 0; i<block.matrix.length; i++) {
			for (int j = 0; j<block.matrix[i].length; j++) {
				if(block.matrix[i][j]==1)
					g.fillRect(block.colPos*Block.SIZE+ j*Block.SIZE, block.rowPos*Block.SIZE +i*Block.SIZE, Block.SIZE, Block.SIZE);
			}
		}

		g.setColor(Color.blue);
		for(int i = 0; i<arena.length; i++) {
			for (int j = 0; j<arena[i].length; j++) {
				if(arena[i][j]==1)
					g.fillRect(j*Block.SIZE, i*Block.SIZE, Block.SIZE, Block.SIZE);
			}
		}

	}

	@Override
	public void keyPressed(int k) {
		if(k==KeyEvent.VK_RIGHT) {
			block.colPos++;
			if(collideWall() || collideBlock())
				block.colPos--;
		}

		else if(k==KeyEvent.VK_LEFT) { //hits left wall
			block.colPos--;
			if(collideWall() || collideBlock())
				block.colPos++;
		}

		else if(k==KeyEvent.VK_DOWN) {
			instantFall();
		}

		else if(k==KeyEvent.VK_UP) {
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
