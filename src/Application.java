import hevs.graphics.FunGraphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Application {

	public enum StateMachine {
		START, WAIT_INPUT, MOVE, END, STOP
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StateMachine state = StateMachine.START;
		Grid gameGrid = new Grid(5);
		FunGraphics funGraphics;
		// KeyEvent myKeyboard = new KeyEvent(a);

		// Inits the graphic window
		funGraphics = new FunGraphics(640, 480);
		
		funGraphics.setKeyManager(new KeyAdapter() {

			// Will be called when a key has been pressed
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_UP) {
					System.out.println("UP");
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					System.out.println("DOWN");
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					System.out.println("RIGHT");
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					System.out.println("LEFT");
				}
			}
		});

		while (gameGrid.is2048() == true) {
			switch (state) {
			case START: {
				gameGrid.createBloc();
				gameGrid.createBloc();
				state = StateMachine.WAIT_INPUT;
			}
				break;
			case WAIT_INPUT: {
				
			}
				break;
			case MOVE: {

			}
				break;
			case END: {

			}
				break;
			case STOP: {

			}
				break;
			default: {
				throw new IllegalArgumentException("Unexpected value");
			}
			}
		}
	}
	
	
}
