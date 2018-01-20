import java.awt.Color;
import java.awt.Graphics;
import com.sun.glass.events.KeyEvent;

public class TetrisState extends GameState{
	private int ticks;
	//private Tetromino player;
	private Player p;
	private Arena arena;
	private int score = 0;
	
	public TetrisState(GameStateManager gsm) {
		super(gsm);
		arena = new Arena();
		init();
	}

	@Override
	public void init() {
		p = new Player();
		
		if (collideBlock()) {
			// Lose //
			p = new Player();
			arena = new Arena();
		}
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
		p.moveVertically(+rows);

		if(collideFloor() || collideBlock()) {
			p.moveVertically(-rows);
			arena.merge(p);
			System.out.println(score+= arena.sweep());
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


	public boolean canWallKick() {
		//try offsetting left and right until offset<longestRow
		int offset =0;

		//lateral
		while (offset < p.tetromino.longestRow()) {
			offset = (offset >=0) ? (-offset) -1: -offset; //0, -1, 1, -2, 2...

			p.moveLaterally(offset);

			if(!collideWall() && !collideBlock()) {
				return true;
			}else {
				p.moveLaterally(-offset);
			}
		}
		return false;
	}

	public boolean canFloorKick() {
		if (p.shape == Shape.Line) {
			return false;
		}

		p.moveVertically(-1);

		if(!collideWall() && !collideFloor() && !collideBlock()) {
			return true;
		}else {
			p.moveVertically(2);
			if(!collideBlock() && !collideWall() && !collideFloor()) {
				return true;
			}
		}

		return false;
	}

	public boolean collideWall() {
		for(Block[] row: p.tetromino.blocks) {
			for(Block b: row) {
				if(b.colPos < 0) {
					//System.out.println("Collide left");
					return true;
				}
				else if (b.colPos >= Arena.COLS) {
					//System.out.println("Collide right");
					return true;
				}
			}
		}
		return false;
	}

	public boolean collideFloor() {
		for(Block[] row: p.tetromino.blocks) {
			for(Block b: row) {
				if(b.rowPos >= Arena.ROWS || b.rowPos < 0) {
					//System.out.println("Collide Floor");
					return true;
				}
			}
		}
		return false;
	}

	public boolean collideBlock() {
		for(Block[] row: p.tetromino.blocks) {
			for(Block b: row) {
				if(arena.blocks[b.rowPos][b.colPos] != null) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void draw(Graphics g) {
		drawBackground(g);
		p.draw(g);
		arena.draw(g);
	}

	private void drawBackground(Graphics g) {
		g.setColor(new Color(211, 202, 186));
		g.fillRect(0, 0, GamePanel.PANEL_WIDTH, GamePanel.PANEL_HEIGHT);
		
	}

	@Override
	public void keyPressed(int k) {
		if(k==KeyEvent.VK_RIGHT) {
			p.moveLaterally(1);
			if(collideWall() || collideBlock()) {
				p.moveLaterally(-1);
			}
		}
		else if(k==KeyEvent.VK_LEFT) {
			p.moveLaterally(-1);
			if(collideWall() || collideBlock()) {
				p.moveLaterally(1);
			}
		}

		else if(k==KeyEvent.VK_DOWN) {
			fall(1);
		}

		else if(k==KeyEvent.VK_UP) {
			instantFall();
		}
		else if(k==KeyEvent.VK_SPACE) {
			p.rotate(true);
			// attempt to wall kick when colliding after rotation
			if(collideWall()) {
				if(!canWallKick()) {
					//rotate the other way
					p.rotate(false);
				}
			}
			else if(collideFloor()) {
				if(!canFloorKick()) {
					p.rotate(false);
				}
			}
			else if(collideBlock()) {
				if (!canWallKick() && !canFloorKick()) {
					p.rotate(false);
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

