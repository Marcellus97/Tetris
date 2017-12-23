import java.awt.Color;
import java.awt.Graphics;

public class Arena  {

	int rows;
	int cols;
	int blocksWidth;
	int blocksHeight;
	Block[][] blocks;
	
	public Arena(int rows, int cols) {
		this.rows=rows;
		this.cols=cols;
		blocks = new Block[rows][cols];
		
		blocksWidth = GamePanel.PANEL_WIDTH/rows;
		blocksHeight = GamePanel.PANEL_HEIGHT/cols;
	}
	
	public void draw(Graphics g) {
		for(int i =0; i<GamePanel.PANEL_WIDTH; i=i+(blocksWidth)){ 
			g.drawLine(i, 0, i, GamePanel.PANEL_HEIGHT);
		}
		for(int i =0; i<GamePanel.PANEL_HEIGHT; i=i+(blocksHeight)){
			g.drawLine(0, i, GamePanel.PANEL_WIDTH, i);
		}
		
		for (Block[] bs: blocks) {
			for(Block b: bs) {
				b.draw(g);
			}
		}
	}
	
	public void merge(int row, int col, Color color) {
		
	}
}
