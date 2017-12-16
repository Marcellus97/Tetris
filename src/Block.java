
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Block extends JComponent {
	private Color blockColor;
	private int velocity;
	
	public Block(int x, int y, int width, int height){
		super();
		this.setBounds(x,y,width,height);
		this.setPreferredSize( new Dimension( getWidth(), getHeight() ) );
		blockColor=rndColor();
		velocity= 5;
	}

	public Color getColor() {
		return blockColor;
	}
	public int getVelocity() {
		return velocity;
	}
	public void move() {
		setBounds(getX(), getY()+velocity, getWidth(), getHeight());
	}
	public void stop() {
		velocity=0;
	}
	// Generates random color
	private Color rndColor() {
		return new Color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255));
		//return new Color((int)(Math.random() * 0x1000000));
	}
	
}
