import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {

	private JFrame jFrame;
	private static final int FRAME_WIDTH=1000;
	private static final int FRAME_HEIGHT=800;
	private static final int FRAME_X=600;
	private static final int FRAME_Y=200;

	private JPanel scorePanel;
	private GamePanel gamePanel;
	private Rectangle r;


	public Window() {

		initializeWindow();

		r = new Rectangle();
		r.setBounds(50, 50, FRAME_WIDTH/2, 100);


	}

	private void initializeWindow() {
		// JFrame
		jFrame = new JFrame();
		jFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		jFrame.setLocation(FRAME_X, FRAME_Y);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Main game panel
		gamePanel = new GamePanel();
		gamePanel.setBounds(FRAME_X, FRAME_Y, 1000, 700);
		gamePanel.setBackground(Color.black);
		jFrame.add(gamePanel,BorderLayout.CENTER);

		// Score panel
		scorePanel = new JPanel();
		scorePanel.setBackground(Color.pink);
		scorePanel.add(new Label("Score: 0"));
		jFrame.add(scorePanel, BorderLayout.NORTH);
	}

	public void addBlock(Block b) {
		gamePanel.add(b);
	}

	public GamePanel getGamePanel() {
		return this.gamePanel;
	}

}
