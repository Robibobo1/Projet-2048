import java.util.Random;


public class Grid {
	
	int size; // taille de la grille
	int blocTab[][]; // tableau contenant la grille
	boolean gameIsFull = false; // flag indiquant si la grille est pleine
	boolean EZMode = false; // permet d'activer le mode easy (utile pour les tests)

	// constructeur de la classe grid
	Grid(int size, boolean EZMode) {
		// modification de la taille et du mode de fonctionnement
		this.EZMode = EZMode;
		this.size = size;
		
		// attribution de la taille du tableau
		blocTab = new int[size][size];

		// on rempli le tableau de cases vides (valeur -1)
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				blocTab[j][i] = -1;
			}
		}

	}

	// cette fonction deplace les blocs dans la grille selon la valeur de la variable direction
	// 0 -> Up
	// 1 -> Down
	// 2 -> Right
	// 3 -> Left
	public void moveBlocs(int direction) {
		int x = 0, y = 0;// cases du tableau
		int empty = 0;// compte les cases vides
		int bloc = 0;// récupère le bloc désiré dans la grille
		int previous_bloc = -1; // bloc précédent

		// boucles permettant de scanner la grille
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				// choix des valeurs de x et y selon direction
				if (direction == 2 || direction == 1) {
					x = size - 1 - i;
					y = size - 1 - j;
				}
				if (direction == 3 || direction == 0) {
					x = i;
					y = j;
				}

				// inversion de X et Y dans les cases du tableau selon direction
				// on recupere la valeur de la case souhaitee dans la variable bloc
				if (direction == 2 || direction == 3)
					bloc = blocTab[y][x];
				if (direction == 1 || direction == 0)
					bloc = blocTab[x][y];

				if (bloc != -1) // si la case n'est pas vide
				{
					int idx = 1; // gain applique au bloc

					if (bloc == previous_bloc) // si le bloc précédent est le même que l'actuel
					{
						empty++;// on bouge d'une case en plus
						idx = 2;// on double la valeur du gain
						previous_bloc = -1; // reinitialissation de previous bloc
					} 
					else
						previous_bloc = bloc; // sauvegarde du bloc actuel

					if (empty != 0) // si on peut au moins bouger d'une case
					{
						if (direction == 3 || direction == 0) // on inverse la variable empty selon la direction
							empty *= -1;

						// deplacement des blocs selon le sens a l'aide de empty et idx
						if (direction == 2 || direction == 3) {
							blocTab[y + empty][x] = blocTab[y][x] * idx;
							blocTab[y][x] = -1;
						}

						if (direction == 1 || direction == 0) {
							blocTab[x][y + empty] = blocTab[x][y] * idx;
							blocTab[x][y] = -1;
						}

						empty = Math.abs(empty); // on remet empty en positif
					}
				} else
					empty++; // si la case est vide, on augmente empty
			}
			empty = 0; // reset de empty à chaque changement de lignes
			previous_bloc = -1; // reset de la variable previous bloc 
		}
	}

	// cree un nouveau bloc d'une valeur position random dans la grille
	public void createBloc() {
		Random r = new Random();
		// valeurs X et Y random
		int randomX;
		int randomY;

		isFull(); // on verifie si la grille est pleine
		if (gameIsFull) // on quitte la fonction si la grille est pleine
		return;
		
		do {			
			// generation des valeurs random dans la plage size
			randomX = r.nextInt(size);
			randomY = r.nextInt(size);
		} while (blocTab[randomX][randomY] != -1); // si la case est deja pleine, on regenere de nouvelles valeurs random 

		blocTab[randomX][randomY] = (EZMode) ? 256 : 2; // on cree le nouveau bloc (si on est en mode easy, il vaut 256 sinon 2)
	}

	// cette fonction verifie si la grille est pleine
	private void isFull() {
		// boucles permettant de lire les cases de la grille
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (blocTab[j][i] == -1) { // si la case est vide
					gameIsFull = false; // la grille n'est pas pleine
					return; // on quitte la fonction
				}
			}
		}
		gameIsFull = true; // si aucun bloc n'est vide, la grille est pleine
	}

	
	
	// cette fonction sert a afficher la grille dans la console
	public String toString() {
		String out = ""; // variable retournee
		
		// boucles permettant la lecture des cases de la grille
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				out += (blocTab[j][i] == -1) ? " " + blocTab[j][i] : "  " + blocTab[j][i];
			}
			out += '\n'; // ajout d'un retour a la ligne
		}
		return out;
	}

	// cette fonction verifie si on a gagne (renvoie true si c'est le cas)
	public boolean is2048() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (blocTab[j][i] == 2048) // si une case vaut 2048
					return true; // on renvoie true
			}
		}
		return false; // si aucune des cases vaut 2048, on renvoie false
	}

	// verifie si on a perdu (renvoie true si c'est le cas)
	public boolean hasLost() {
		if (gameIsFull == false) // si la grille n'est pas pleine
			return false; // on n'a pas encore perdu

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				
				// on verifie dans toutes les direction si au moins deux blocs identiques sont a cotes
				// on retourne false si c'est le cas
				if (j != 0 && blocTab[j - 1][i] == blocTab[j][i]) {
					return false;
				}
				if (j != size - 1 && blocTab[j + 1][i] == blocTab[j][i]) {
					return false;
				}
				if (i != 0 && blocTab[j][i - 1] == blocTab[j][i]) {
					return false;
				}
				if (i != size - 1 && blocTab[j][i + 1] == blocTab[j][i]) {
					return false;
				}
			}
		}
		return true; // sinon cela veut dire que l'on a perdu
	}

}
