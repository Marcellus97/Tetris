import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tetris {

	private static final int FRAME_X=600;
	private static final int FRAME_Y=200;
	
	private static JFrame frame;
	private static GamePanel gamePanel;
	private static JPanel scorePanel;
	
	
	public static void main(String[] args) {		
		intializeFrame();

	}

	private static void intializeFrame(){
		frame = new JFrame("Tetris");
		gamePanel = new GamePanel();
		scorePanel = new JPanel();
		
		frame.setLocation(FRAME_X, FRAME_Y);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(gamePanel,BorderLayout.CENTER);
//		frame.add(scorePanel, BorderLayout.NORTH);
//		
//		scorePanel.setBackground(Color.pink);
//		scorePanel.add(new Label("Score: 0"));

		frame.pack();
	}
	
}
