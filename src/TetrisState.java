import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.sun.glass.events.KeyEvent;

public class TetrisState extends GameState{

	private int fallTicks;
	private Player player;
	private Arena arena;

	public TetrisState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		arena = new Arena();
		player = new Player(arena, new TPiece(Color.RED));
		System.out.println(arena.matrix.length);
	}

	@Override
	public void tick() {
		fallTicks++;
		if(fallTicks > GamePanel.FPS) {
			fall();
			fallTicks=0;
		}

	}
	public void fall() {
			player.moveDown();
			if(player.collision()) {
				arena.merge(player.piece);
				player = new Player(arena, new TPiece(Color.RED));
			}
		}
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, GamePanel.PANEL_WIDTH, GamePanel.PANEL_HEIGHT);
		player.draw(g);
		arena.draw(g);
	}

	@Override
	public void keyPressed(int k) {
		if(k==KeyEvent.VK_RIGHT) {
			player.moveRight();
			if(player.collision()) {
				player.moveLeft();
			}
		}

		else if(k==KeyEvent.VK_LEFT) { //hits left wall
			player.moveLeft();
			if(player.collision()) {
				player.moveRight();
			}
		}

		else if(k==KeyEvent.VK_DOWN) {
			player.instantFall();
			arena.merge(player.piece);
			player = new Player(arena, new TPiece(Color.RED));
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
