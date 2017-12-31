import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.sun.glass.events.KeyEvent;

public class TetrisState extends GameState{

	private int fallTicks;
	private Block player;


	public TetrisState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		player = new Block(0,0);
	}

	@Override
	public void tick() {
		fallTicks++;
		if(fallTicks > GamePanel.FPS) {
			fall();
			fallTicks=0;
		}

	}

	private void fall() {
		
	}

	@Override
	public void draw(Graphics g) {
		player.draw(g);
	}

	@Override
	public void keyPressed(int k) {
		if(k==KeyEvent.VK_RIGHT) {
			player.col++;
		}

		else if(k==KeyEvent.VK_LEFT) { //hits left wall
			player.col--;
		}

		else if(k==KeyEvent.VK_DOWN) {

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
