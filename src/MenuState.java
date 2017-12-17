import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class MenuState extends GameState{

	private String[] options = {"Start","Help","Quit"};
	private int currentSelection = 0;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {} //notn used

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		int fontSize = 80;
		int x;
		int y;

		g.setColor(new Color(155,75,55));
		g.fillRect(0, 0, GamePanel.PANEL_WIDTH, GamePanel.PANEL_HEIGHT);
		
		for (int i = 0; i<options.length; i++) {
			if(i == currentSelection) {
				g.setColor(Color.GREEN);
			}else {
				g.setColor(Color.BLACK);
			}

			x = (GamePanel.PANEL_WIDTH / 2 ) - 50;
			y = (GamePanel.PANEL_HEIGHT / (options.length +1) * (i+1));
			g.setFont(new Font("Georgia", Font.BOLD, fontSize));
			g.drawString(options[i], x, y); //spaces them out evenly
		}

	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_UP) {
			if(currentSelection == 0) {
				currentSelection = options.length-1;
			}
			else {
				currentSelection--;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			if(currentSelection == options.length-1) {
				currentSelection = 0;
			}
			else {
				currentSelection++;
			}
		}
		if(k == KeyEvent.VK_ENTER) {
			if(currentSelection == 0) {
				// play
			}else if(false) {
				// help
			}
			else if(currentSelection == options.length-1) {
				//quit
				System.exit(0);
			}
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub

	}

}
