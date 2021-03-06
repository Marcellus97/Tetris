import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener{

	public static final int PANEL_WIDTH=Block.SIZE * Arena.COLS;
	public static final int PANEL_HEIGHT=Block.SIZE * Arena.ROWS;

	private Thread thread;
	private boolean isRunning = false;
	static int FPS = 60;
	private long targetTime = 1000/FPS;

	private GameStateManager gsm;

	public GamePanel() {
		initializePanel();
		gsm = new GameStateManager();
		start();

	}

	private void initializePanel() {
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
	}


	public void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {

		long start, elapsed, wait;

		while(isRunning) {

			start = System.nanoTime();

			tick();
			repaint();

			elapsed = System.nanoTime() - start;
			wait = targetTime - (long) (elapsed / 1e6);

			if(wait <= 0) { //attempt to sleep
				wait = 5;
			}

			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	private void tick() {
		gsm.tick();
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

		gsm.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent k) {
		gsm.keyPressed(k.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent k) {
		gsm.keyReleased(k.getKeyCode());

	}

	@Override
	public void keyTyped(KeyEvent k) {}

}
