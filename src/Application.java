import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Application {

	boolean EZMode = false;

	public enum StateMachine { // différents états de notre machine d'état
		START, WAIT_INPUT, MOVE, END_WIN, END_LOOSE, STOP
	}

	int direction = -1; // variable contenant la direction du déplacement à effectuer
	boolean keyChange; // vaut true lorsque l'utilisateur appuie sur une touche

	Application() {
		StateMachine state = StateMachine.START; // on commence dans l'état START
		Grid gameGrid = null; // création d'une grille
		Windows appWindows = new Windows(400, 400); // création d'une fenêtre

		appWindows.funGraphics.setKeyManager(new KeyAdapter() {
			// Will be called when a key has been pressed
			public void keyPressed(KeyEvent e) { // lorsque une touche est pressée
				
				
				direction = -1; // réinitialisation de la variable direction
				// on modifie la variable direction si une flèche est pressée
				if (e.getKeyCode() == KeyEvent.VK_UP)
					direction = 0;
				if (e.getKeyCode() == KeyEvent.VK_DOWN)
					direction = 1;
				if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					direction = 2;
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					direction = 3;
				
				if(direction != -1) // si on a bel et bien appuyé une flèche
				keyChange = true; // on indique un changement
			}
		});

		while (true) { // boucle infinie
			switch (state) { // Machine d'état
			case START: {
				gameGrid = new Grid(appWindows.start(), EZMode); // attribution des valeurs de gameGrid
				appWindows.insertGameGrid(gameGrid); // insertion de notre grille dans l'objet appWindows
				appWindows.drawGrid(); // dessin de la grille dans la fenêtre créée
				
				//création de deux blocs pour commencer le jeu
				gameGrid.createBloc();
				gameGrid.createBloc();
				
				// changement d'état 
				state = StateMachine.WAIT_INPUT;
			}
				break;
			case WAIT_INPUT: { // on attend que l'utilisateur presse une flèche
				if (keyChange == true) { // si l'utilisateur touche une flèche
					keyChange = false;
					state = StateMachine.MOVE; // changement d'état
				}
			}
				break;
			case MOVE: {
				gameGrid.moveBlocs(direction); // on déplace les blocs selon la direction choisie par l'utilisateur
				gameGrid.createBloc(); // création d'un nouveau bloc
				appWindows.drawGrid(); // rafraichissement de l'affichage de la grille
				System.out.println(gameGrid); // affichage de la grille dans la console
				
				// on vérifie si le joueur a gagné ou perdu
				// si ce n'est pas le cas, on retourne dans l'état wait_input
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
			case STOP: { // lorsque l'utilisateur ne veut plus jouer, on reste dans cet état
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
		Application game = new Application(); // création de l'objet game

	}
}
