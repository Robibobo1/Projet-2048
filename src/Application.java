import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Application {

	boolean EZMode = false;

	public enum StateMachine { // diff�rents �tats de notre machine d'�tat
		START, WAIT_INPUT, MOVE, END_WIN, END_LOOSE, STOP
	}

	int direction = -1; // variable contenant la direction du d�placement � effectuer
	boolean keyChange; // vaut true lorsque l'utilisateur appuie sur une touche

	Application() {
		StateMachine state = StateMachine.START; // on commence dans l'�tat START
		Grid gameGrid = null; // cr�ation d'une grille
		Windows appWindows = new Windows(400, 400); // cr�ation d'une fen�tre

		appWindows.funGraphics.setKeyManager(new KeyAdapter() {
			// Will be called when a key has been pressed
			public void keyPressed(KeyEvent e) { // lorsque une touche est press�e
				
				
				direction = -1; // r�initialisation de la variable direction
				// on modifie la variable direction si une fl�che est press�e
				if (e.getKeyCode() == KeyEvent.VK_UP)
					direction = 0;
				if (e.getKeyCode() == KeyEvent.VK_DOWN)
					direction = 1;
				if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					direction = 2;
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					direction = 3;
				
				if(direction != -1) // si on a bel et bien appuy� une fl�che
				keyChange = true; // on indique un changement
			}
		});

		while (true) { // boucle infinie
			switch (state) { // Machine d'�tat
			case START: {
				gameGrid = new Grid(appWindows.start(), EZMode); // attribution des valeurs de gameGrid
				appWindows.insertGameGrid(gameGrid); // insertion de notre grille dans l'objet appWindows
				appWindows.drawGrid(); // dessin de la grille dans la fen�tre cr��e
				
				//cr�ation de deux blocs pour commencer le jeu
				gameGrid.createBloc();
				gameGrid.createBloc();
				
				// changement d'�tat 
				state = StateMachine.WAIT_INPUT;
			}
				break;
			case WAIT_INPUT: { // on attend que l'utilisateur presse une fl�che
				if (keyChange == true) { // si l'utilisateur touche une fl�che
					keyChange = false;
					state = StateMachine.MOVE; // changement d'�tat
				}
			}
				break;
			case MOVE: {
				gameGrid.moveBlocs(direction); // on d�place les blocs selon la direction choisie par l'utilisateur
				gameGrid.createBloc(); // cr�ation d'un nouveau bloc
				appWindows.drawGrid(); // rafraichissement de l'affichage de la grille
				System.out.println(gameGrid); // affichage de la grille dans la console
				
				// on v�rifie si le joueur a gagn� ou perdu
				// si ce n'est pas le cas, on retourne dans l'�tat wait_input
				state = (gameGrid.is2048()) ? StateMachine.END_WIN : StateMachine.WAIT_INPUT;
				state = (gameGrid.hasLost()) ? StateMachine.END_LOOSE : state;
			}
				break;
			case END_WIN: { // on demande au joueur s'il veut rejouer
				state = (appWindows.win()) ? StateMachine.START : StateMachine.STOP;
			}
				break;
			case END_LOOSE: {// on demande au joueur s'il veut rejouer
				state = (appWindows.lose()) ? StateMachine.START : StateMachine.STOP;
			}
			case STOP: { // lorsque l'utilisateur ne veut plus jouer, on reste dans cet �tat
			}
				break;
			default: { // affichage d'un message d'erreur
				throw new IllegalArgumentException("Unexpected value");
			}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application game = new Application(); // cr�ation de l'objet game

	}
}
