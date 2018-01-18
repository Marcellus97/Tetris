import java.awt.Color;
import java.awt.Graphics;
import com.sun.glass.events.KeyEvent;

public class TetrisState extends GameState{
	private int ticks;
	private Tetromino player, shadow;
	private Arena arena;

	public TetrisState(GameStateManager gsm) {
		super(gsm);
		init();
		arena = new Arena();
	}

	@Override
	public void init() {
		player = new Tetromino();
		shadow = new Tetromino(player.shape);
	}

	@Override
	public void tick() {
		ticks++;
		if(ticks>GamePanel.FPS) {
			fall(1);
			ticks=0;
		}
	}

	//return false if the fall resulted in collision
	public boolean fall(int rows) {
		moveVertically(+rows);

		if(collideFloor() || collideBlock()) {
			moveVertically(-rows);
			arena.merge(player);
			init();
			return false;
		}
		return true;
	}

	public void instantFall() {
		boolean falling = true;
		do {
			falling = fall(1);
		}while(falling);
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

			if(player.shape != Shape.Square) { //Square doesn't rotate
				for(Block[] row: player.blocks) {
					for(Block b: row) {

						//old Position relative from pivot
						int relativeRow = b.rowPos-player.pivRow;
						int relativeCol = b.colPos-player.pivCol;

						//Mulitply by Rotated Identity Matrix / CCW or CW
						int transformRow = (rotationMatrix[0][0] * relativeRow) + (rotationMatrix[0][1] * relativeCol);
						int transformCol = (rotationMatrix[1][0] * relativeRow) + (rotationMatrix[1][1] * relativeCol);

						//Translate old posiitons to new row/col
						b.rowPos = player.pivRow + transformRow;
						b.colPos = player.pivCol + transformCol;
					}
				}
			}
			player.flipDir();
	}
	public boolean canKick() {
		//try offsetting left and right until offset<longestRow
		int offset =0;

		//lateral
		while (offset < player.longestRow()) {
			offset = (offset >=0) ? (-offset) -1: -offset; //0, -1, 1, -2, 2...
			System.out.println(offset);

			moveLaterally(offset);

			if(!collideBlock() && !collideWall()) {
				return true;
			}else {
				moveLaterally(-offset);
			}
		}

		//vertical


		return false;
	}

	public void moveVertically(int rows) {
		for(Block[] row: player.blocks) {
			for(Block b: row) {
				b.rowPos+=rows;
			}
		}
		player.pivRow+=rows;
	}


	public void moveLaterally(int cols) {
		for(Block[] row: player.blocks) {
			for(Block b: row) {
				b.colPos+=cols;
			}
		}
		player.pivCol+=cols;
	}

	public boolean collideWall() {
		for(Block[] row: player.blocks) {
			for(Block b: row) {
				if(b.colPos < 0) {
					System.out.println("Collide left");
					return true;
				}
				else if (b.colPos >= Arena.COLS) {
					System.out.println("Collide right");
					return true;
				}
			}
		}
		return false;
	}

	public boolean collideFloor() {
		for(Block[] row: player.blocks) {
			for(Block b: row) {
				if(b.rowPos >= Arena.ROWS) {
					System.out.println("Collide Floor");
					return true;
				}
			}
		}
		return false;
	}

	public boolean collideBlock() {
		for(Block[] row: player.blocks) {
			for(Block b: row) {
				for (Block arenaBlock: arena.blocks) {
					if(b.rowPos == arenaBlock.rowPos && b.colPos == arenaBlock.colPos) {
						return true;
					}
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
			moveLaterally(1);
			if(collideWall() || collideBlock()) {
				moveLaterally(-1);
			}
		}
		else if(k==KeyEvent.VK_LEFT) {
			moveLaterally(-1);
			if(collideWall() || collideBlock()) {
				moveLaterally(1);
			}
		}

		else if(k==KeyEvent.VK_DOWN) {
			fall(1);
		}

		else if(k==KeyEvent.VK_UP) {
			instantFall();
		}
		else if(k==KeyEvent.VK_SPACE) {
			rotate(true);
			// attempt to wall kick when colliding after rotation
			if(collideWall() || collideBlock()) {
				if(!canKick()) {
					//rotate the other way
					rotate(false);
				}
			}
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

