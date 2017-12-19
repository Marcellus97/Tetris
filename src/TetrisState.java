import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.sun.glass.events.KeyEvent;

public class TetrisState extends GameState{

	private int blocksWidth = GamePanel.PANEL_WIDTH/10;
	private int blocksHeight = GamePanel.PANEL_HEIGHT/8;
	private int ticks;
	
	private ArrayList<Block> blocks;
	private Block fallingBlock;

	public TetrisState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		blocks = new ArrayList<>();
		newBlock();
		ticks = 0;
	}

	@Override
	public void tick() {
		System.out.println(++ticks);
		fallingBlock.fall();

		if(fallingBlock.getY()+fallingBlock.getWidth() >= GamePanel.PANEL_HEIGHT) {
			setBlock();
			newBlock();
			//System.out.println("x: "+fallingBlock.x+", y: "+fallingBlock.y+", width: "+fallingBlock.width+", height: "+fallingBlock.height);
		}

	}

	private void newBlock() {
		fallingBlock = new Block(GamePanel.PANEL_WIDTH/2, -blocksHeight, blocksWidth, blocksHeight);
	}
	
	private void setBlock() {
		fallingBlock.stop();
		fallingBlock.y = GamePanel.PANEL_HEIGHT-fallingBlock.height;
		blocks.add(fallingBlock);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);

		//draw a grid
		for(int i =0; i<GamePanel.PANEL_WIDTH; i=i+(GamePanel.PANEL_WIDTH/10)){ 
			g.drawLine(i, 0, i, GamePanel.PANEL_HEIGHT);
		}
		for(int i =0; i<GamePanel.PANEL_HEIGHT; i=i+(GamePanel.PANEL_HEIGHT/8)){
			g.drawLine(0, i, GamePanel.PANEL_WIDTH, i);
		}

		for(Block b:blocks) { //draw all the still blocks
			b.draw(g);
		}
		
		
		fallingBlock.draw(g);
	}

	@Override
	public void keyPressed(int k) {
		if(k==KeyEvent.VK_RIGHT) {
			if(fallingBlock.x +fallingBlock.width < GamePanel.PANEL_WIDTH) { //hits right wall
				fallingBlock.moveRight();

			}
		}
		else if(k==KeyEvent.VK_LEFT) { //hits left wall
			if(fallingBlock.x > 0) {
				fallingBlock.moveLeft();
			}
		}
		else if(k==KeyEvent.VK_DOWN) {
			setBlock();
			newBlock();
		}
		else if(k==KeyEvent.VK_ESCAPE) {
			gsm.states.push(new MainMenuState(gsm));
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub

	}

}
