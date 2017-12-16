import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {

	private ArrayList<Block> blocks;
//	private Timer timer;
//	private TimerListener timerListener;
	private KeyBoardInputListener keyBoardInputListener;

	public GamePanel() {
		super();
		blocks = new ArrayList<>();

	}

	public void generateBlock() {
		blocks.add(new Block((getWidth()/2) - 25,0,50,50));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawLine(0, 0, 500, 500);

		for(Block b : blocks) {
			g.setColor(b.getColor());
			g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}
	}

//	class TimerListener implements ActionListener{
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			for(Block b: blocks) {
//				b.move();
//			}
//		}
//
//	}

	class KeyBoardInputListener implements KeyListener{


		@Override
		public void keyPressed(KeyEvent arg0) {


		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}


	}
}
