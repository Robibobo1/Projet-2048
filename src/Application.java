import hevs.graphics.FunGraphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Application {

	public enum StateMachine {
		START, WAIT_INPUT, MOVE, END, STOP
	}
	
	int direction = -1;
	boolean keyChange;

	Application() {
		StateMachine state = StateMachine.START;
		Grid gameGrid = new Grid(5);
		FunGraphics funGraphics;
		funGraphics = new FunGraphics(640, 480);

		funGraphics.setKeyManager(new KeyAdapter() {
			// Will be called when a key has been pressed
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) direction = 0;
				if (e.getKeyCode() == KeyEvent.VK_DOWN) direction = 1;
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) direction = 2;
				if (e.getKeyCode() == KeyEvent.VK_LEFT) direction = 3;
				else direction = -1;
				keyChange = true;
			}
		});

		while (true) {
			switch (state) {
			case START: {
				gameGrid.createBloc();
				gameGrid.createBloc();
				state = StateMachine.WAIT_INPUT;
			}
				break;
			case WAIT_INPUT: {
				if (keyChange == true)
				{
					keyChange = false;
					state = StateMachine.MOVE;
				}
			}
				break;
			case MOVE: {
				gameGrid.moveBlocs(direction);
				gameGrid.createBloc();
				System.out.println(gameGrid.drawGrid());
				
				state = (gameGrid.is2048()) ? StateMachine.END : StateMachine.WAIT_INPUT;
			}
				break;
			case END: {
				state = (gameGrid.replay()) ? StateMachine.START : StateMachine.STOP;
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Application game = new Application();

	}
}
