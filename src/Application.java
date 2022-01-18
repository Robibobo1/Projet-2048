import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Application {

	boolean EZMode = false;

	public enum StateMachine {
		START, WAIT_INPUT, MOVE, END_WIN, END_LOOSE, STOP
	}

	int direction = -1;
	boolean keyChange;

	Application() {
		StateMachine state = StateMachine.START;
		Grid gameGrid = null;
		Windows appWindows = new Windows(400, 400);

		appWindows.funGraphics.setKeyManager(new KeyAdapter() {
			// Will be called when a key has been pressed
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP)
					direction = 0;
				if (e.getKeyCode() == KeyEvent.VK_DOWN)
					direction = 1;
				if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					direction = 2;
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					direction = 3;
				keyChange = true;
			}
		});

		while (true) {
			switch (state) {
			case START: {
				gameGrid = new Grid(appWindows.start(), EZMode);
				appWindows.insertGameGrid(gameGrid);
				appWindows.drawGrid();
				gameGrid.createBloc();
				gameGrid.createBloc();
				state = StateMachine.WAIT_INPUT;
			}
				break;
			case WAIT_INPUT: {
				if (keyChange == true) {
					keyChange = false;
					state = StateMachine.MOVE;
				}
			}
				break;
			case MOVE: {
				gameGrid.moveBlocs(direction);
				gameGrid.createBloc();
				appWindows.drawGrid();
				System.out.println(gameGrid.drawGrid());

				state = (gameGrid.is2048()) ? StateMachine.END_WIN : StateMachine.WAIT_INPUT;
				state = (gameGrid.hasLost()) ? StateMachine.END_LOOSE : state;
			}
				break;
			case END_WIN: {
				state = (appWindows.win() == 'y') ? StateMachine.START : StateMachine.STOP;
			}
				break;
			case END_LOOSE: {
				state = (appWindows.lose() == 'y') ? StateMachine.START : StateMachine.STOP;
			}
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
