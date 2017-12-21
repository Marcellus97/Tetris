
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class Block extends Rectangle {
	private Color blockColor;
	private int[][] shape = {
			{0 , 0 , 0 , 0},
			{0 , 0 , 0 , 0},
			{0 , 0 , 0 , 0},
			{0 , 0 , 0 , 0} };
	
	public Block(int x, int y, int width, int height){
		super();
		this.setBounds(x,y,width,height);
		blockColor=rndColor();

	}

	public Color getColor() {
		return blockColor;
	}

	public void moveRight() {
		x+=width;
	}
	public void moveLeft() {
		x-=width;
	}
	public void draw(Graphics g) {
		g.setColor(blockColor);
		g.fillRect(x, y, width, height);
	}
	public void setColor(Color newColor) {
		blockColor=newColor;
	}
	// Generates random color
	private Color rndColor() {
		return new Color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255));
		//return new Color((int)(Math.random() * 0x1000000));
	}
	
}
